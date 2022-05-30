terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
      version = "4.20.0"
    }
    google-beta = {
      version = "4.20.0"
    }
  }
}


locals {
  project_id = "funny-converter"
  region     = "us-central1"
}

provider "google" {
  project = local.project_id
  region = local.region
  # Configuration options
}

provider "google-beta" {
  project = local.project_id
  region = local.region
}


resource "google_project_service" "artifact_registry_api" {
  service = "artifactregistry.googleapis.com"

  disable_on_destroy = true
}



resource "google_artifact_registry_repository" "funny-converter-ar" {
  provider = google-beta

  location = local.region
  repository_id = "funny-converter-ar"
  description = "docker repository for funny converter project"
  format = "DOCKER"
  depends_on = [google_project_service.artifact_registry_api]
}