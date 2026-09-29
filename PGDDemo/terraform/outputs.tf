output "resource_group_id" {
  description = "ID del Resource Group"
  value       = azurerm_resource_group.pgddemo.id
}

output "resource_group_name" {
  description = "Nome del Resource Group"
  value       = azurerm_resource_group.pgddemo.name
}

output "resource_group_location" {
  description = "Location del Resource Group"
  value       = azurerm_resource_group.pgddemo.location
}

output "acr_login_server" {
  description = "Login server dell'Azure Container Registry"
  value       = azurerm_container_registry.pgddemo.login_server
}

output "existing_resource_group_id" {
  description = "ID del Resource Group letto tramite data source"
  value       = data.azurerm_resource_group.existing.id
}

output "aks_name" {
  description = "Nome del cluster AKS esistente"
  value       = data.azurerm_kubernetes_cluster.pgddemo.name
}

output "aks_location" {
  description = "Location del cluster AKS esistente"
  value       = data.azurerm_kubernetes_cluster.pgddemo.location
}

output "aks_kubernetes_version" {
  description = "Versione Kubernetes del cluster AKS esistente"
  value       = data.azurerm_kubernetes_cluster.pgddemo.kubernetes_version
}

output "aks_resource_group" {
  description = "Resource Group del cluster AKS esistente"
  value       = data.azurerm_kubernetes_cluster.pgddemo.resource_group_name
}

output "existing_acr_login_server" {
  description = "Login server dell'ACR reale del PGDDemo"
  value       = data.azurerm_container_registry.pgddemo.login_server
}

output "aks_principal_id" {
  description = "Principal ID della Managed Identity dell'AKS"
  value       = data.azurerm_kubernetes_cluster.pgddemo.identity[0].principal_id
}

output "existing_acr_id" {
  description = "ID dell'ACR reale del PGDDemo"
  value       = data.azurerm_container_registry.pgddemo.id
}

output "aks_dns_prefix" {
  value = data.azurerm_kubernetes_cluster.pgddemo.dns_prefix
}

output "aks_node_resource_group" {
  value = data.azurerm_kubernetes_cluster.pgddemo.node_resource_group
}