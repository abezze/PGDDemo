variable "resource_group_name" {
  description = "Nome del Resource Group Azure"
  type        = string
  default     = "rg-pgddemo-terraform"
}

variable "location" {
  description = "Azure region"
  type        = string
  default     = "Italy North"
}

variable "acr_name" {
  description = "Nome dell'Azure Container Registry"
  type        = string
  default     = "acrpgddemoterraform"
}

variable "dashscope_api_key" {
  description = "DashScope API key"
  type        = string
  sensitive   = true
}