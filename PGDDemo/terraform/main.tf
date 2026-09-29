resource "azurerm_resource_group" "pgddemo" {
  name     = var.resource_group_name
  location = var.location
}

resource "azurerm_container_registry" "pgddemo" {
  name                = var.acr_name
  resource_group_name = azurerm_resource_group.pgddemo.name
  location            = azurerm_resource_group.pgddemo.location
  sku                 = "Basic"
  admin_enabled       = false
}