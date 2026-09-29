param(
    [Parameter(Mandatory = $true)]
    [string]$Version
)

$ErrorActionPreference = "Stop"

# ============================================================
# Configuration
# ============================================================

$ProjectRoot = Split-Path -Parent $PSScriptRoot
$TerraformDir = Join-Path $ProjectRoot "terraform"
$DeploymentFile = Join-Path $TerraformDir "deployment.tf"

$ImageName = "pgddemo"
$AcrName = "acrpgddemo2026"
$AcrLoginServer = "acrpgddemo2026.azurecr.io"
$AksDeployment = "pgddemo"

$FullImageName = "$AcrLoginServer/$ImageName`:$Version"

# ============================================================
# Functions
# ============================================================

function Write-Step {
    param(
        [string]$Message
    )

    Write-Host ""
    Write-Host "============================================================" -ForegroundColor Cyan
    Write-Host $Message -ForegroundColor Cyan
    Write-Host "============================================================" -ForegroundColor Cyan
}

function Test-Command {
    param(
        [string]$CommandName
    )

    if (-not (Get-Command $CommandName -ErrorAction SilentlyContinue)) {
        throw "Comando '$CommandName' non trovato nel PATH."
    }
}

# ============================================================
# Validation
# ============================================================

Write-Step "PGDDemo Deployment - versione $Version"

Test-Command "mvn"
Test-Command "docker"
Test-Command "az"
Test-Command "terraform"
Test-Command "kubectl"

if (-not (Test-Path $ProjectRoot)) {
    throw "Project root non trovato: $ProjectRoot"
}

if (-not (Test-Path $TerraformDir)) {
    throw "Directory Terraform non trovata: $TerraformDir"
}

if (-not (Test-Path $DeploymentFile)) {
    throw "File deployment.tf non trovato: $DeploymentFile"
}

Write-Host ""
Write-Host "Project root : $ProjectRoot"
Write-Host "Terraform    : $TerraformDir"
Write-Host "Image        : $FullImageName"

# ============================================================
# STEP 1 - Maven build
# ============================================================

Write-Step "1/8 - Maven build"

Set-Location $ProjectRoot

mvn clean package -DskipTests

if ($LASTEXITCODE -ne 0) {
    throw "Maven build fallita."
}

Write-Host "Maven build completata." -ForegroundColor Green

# ============================================================
# STEP 2 - Docker build
# ============================================================

Write-Step "2/8 - Docker build"

docker build -t "$ImageName`:$Version" .

if ($LASTEXITCODE -ne 0) {
    throw "Docker build fallita."
}

Write-Host "Docker image creata: $ImageName`:$Version" -ForegroundColor Green

# ============================================================
# STEP 3 - Docker tag
# ============================================================

Write-Step "3/8 - Docker tag"

docker tag "$ImageName`:$Version" $FullImageName

if ($LASTEXITCODE -ne 0) {
    throw "Docker tag fallita."
}

Write-Host "Tag applicato: $FullImageName" -ForegroundColor Green

# ============================================================
# STEP 4 - Azure Container Registry login
# ============================================================

Write-Step "4/8 - Login Azure Container Registry"

az acr login --name $AcrName

if ($LASTEXITCODE -ne 0) {
    throw "Login ACR fallito."
}

Write-Host "Login ACR completato." -ForegroundColor Green

# ============================================================
# STEP 5 - Push image
# ============================================================

Write-Step "5/8 - Push Docker image su ACR"

docker push $FullImageName

if ($LASTEXITCODE -ne 0) {
    throw "Docker push fallito."
}

Write-Host "Image pubblicata: $FullImageName" -ForegroundColor Green

# ============================================================
# STEP 6 - Update Terraform deployment.tf
# ============================================================

Write-Step "6/8 - Aggiornamento deployment.tf"

$content = Get-Content $DeploymentFile -Raw

$pattern = 'image\s*=\s*"acrpgddemo2026\.azurecr\.io/pgddemo:[^"]+"'
$replacement = 'image = "' + $FullImageName + '"'

if ($content -notmatch $pattern) {
    throw "Non è stata trovata nel deployment.tf la configurazione image di PGDDemo."
}

$newContent = [regex]::Replace(
    $content,
    $pattern,
    $replacement
)

if ($newContent -eq $content) {
    Write-Host "deployment.tf contiene già l'immagine $FullImageName." -ForegroundColor Yellow
}
else {
    Set-Content -Path $DeploymentFile -Value $newContent -Encoding UTF8

    Write-Host "deployment.tf aggiornato:" -ForegroundColor Green
    Write-Host "  $FullImageName"
}

# ============================================================
# STEP 7 - Terraform plan
# ============================================================

Write-Step "7/8 - Terraform plan"

Set-Location $TerraformDir

terraform plan

if ($LASTEXITCODE -ne 0) {
    throw "Terraform plan fallito."
}

Write-Host ""
Write-Host "Terraform plan completato." -ForegroundColor Green

# ============================================================
# Confirmation
# ============================================================

Write-Host ""
Write-Host "============================================================" -ForegroundColor Yellow
Write-Host "ATTENZIONE: il prossimo comando applicherà il deployment su AKS." -ForegroundColor Yellow
Write-Host "Image: $FullImageName" -ForegroundColor Yellow
Write-Host "============================================================" -ForegroundColor Yellow
Write-Host ""

$confirmation = Read-Host "Continuare con terraform apply? (y/n)"

if ($confirmation -notmatch '^(y|yes)$') {
    Write-Host ""
    Write-Host "Deployment annullato dall'utente." -ForegroundColor Yellow
    exit 0
}

# ============================================================
# STEP 8 - Terraform apply
# ============================================================

Write-Step "8/8 - Terraform apply"

terraform apply -auto-approve

if ($LASTEXITCODE -ne 0) {
    throw "Terraform apply fallito."
}

Write-Host ""
Write-Host "Terraform apply completato." -ForegroundColor Green

# ============================================================
# Kubernetes rollout
# ============================================================

Write-Step "Attesa rollout Kubernetes"

kubectl rollout status deployment/$AksDeployment --timeout=300s

if ($LASTEXITCODE -ne 0) {
    throw "Rollout Kubernetes fallito o timeout."
}

Write-Host "Rollout completato." -ForegroundColor Green

# ============================================================
# Final status
# ============================================================

Write-Step "Stato finale"

Write-Host ""
Write-Host "Deployment:"
kubectl get deployment $AksDeployment

Write-Host ""
Write-Host "Pods:"
kubectl get pods -l app=$AksDeployment

Write-Host ""
Write-Host "Image utilizzata:"
kubectl get deployment $AksDeployment `
    -o jsonpath="{.spec.template.spec.containers[0].image}"

Write-Host ""
Write-Host ""
Write-Host "============================================================" -ForegroundColor Green
Write-Host "DEPLOYMENT COMPLETATO" -ForegroundColor Green
Write-Host "Versione : $Version" -ForegroundColor Green
Write-Host "Image    : $FullImageName" -ForegroundColor Green
Write-Host "============================================================" -ForegroundColor Green