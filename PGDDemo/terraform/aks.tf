data "azurerm_kubernetes_cluster" "pgddemo" {
  name                = "aks-pgddemo"
  resource_group_name = "rg-pgddemo"
}

resource "azurerm_role_assignment" "aks_acr_pull" {
  scope                = data.azurerm_container_registry.pgddemo.id
  role_definition_name = "AcrPull"
  principal_id         = data.azurerm_kubernetes_cluster.pgddemo.identity[0].principal_id
}
