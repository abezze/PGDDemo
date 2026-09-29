resource "kubernetes_config_map" "pgddemo" {
  metadata {
    name = "pgddemo-config"
  }

  data = {
    application = "PGDDemo"
    environment = "terraform"
  }
}