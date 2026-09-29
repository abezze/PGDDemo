data "azurerm_resource_group" "existing" {
  name = var.resource_group_name
}

data "azurerm_container_registry" "pgddemo" {
  name                = "acrpgddemo2026"
  resource_group_name = "rg-pgddemo"
}
