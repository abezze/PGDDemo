resource "kubernetes_deployment" "pgddemo" {
  metadata {
    name      = "pgddemo"
    namespace = "default"
  }
  lifecycle {
    ignore_changes = [
      spec[0].template[0].metadata[0].annotations
    ]
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "pgddemo"
      }
    }

    template {
      metadata {
        labels = {
          app = "pgddemo"
        }
      }

      spec {
        automount_service_account_token = false
        enable_service_links            = false
        container {
          name  = "pgddemo"
          image = "acrpgddemo2026.azurecr.io/pgddemo:4.0"

          port {
            container_port = 9070
          }

          env {
            name  = "GIT_REPOSITORY_PATH"
            value = "/app/repository"
          }

          env {
            name  = "SPRING_PROFILES_ACTIVE"
            value = "azure"
          }

          env {
            name = "AI_DASHSCOPE_API_KEY"

            value_from {
              secret_key_ref {
                name = "pgddemo-secrets"
                key  = "AI_DASHSCOPE_API_KEY"
              }
            }
          }

          env {
            name  = "GIT_HOST"
            value = "github.com"
          }

          env {
            name  = "GIT_REPOSITORY"
            value = "abezze/PGDDemo.git"
          }

          env {
            name  = "GIT_BRANCH"
            value = "master"
          }

          env {
            name = "GIT_USERNAME"

            value_from {
              secret_key_ref {
                name = "pgddemo-git"
                key  = "username"
              }
            }
          }

          env {
            name = "GIT_TOKEN"

            value_from {
              secret_key_ref {
                name = "pgddemo-git"
                key  = "token"
              }
            }
          }

          volume_mount {
            name       = "repository"
            mount_path = "/app/repository"
          }
        }

        volume {
          name = "repository"

          empty_dir {}
        }
      }
    }
  }
}