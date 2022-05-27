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

resource "google_project_service" "cloud_build_api" {
  service = "cloudbuild.googleapis.com"

  disable_on_destroy = true
}

resource "google_cloudbuild_trigger" "funny-converter-be-image-trigger" {
  name = "funny-converter-be-image-trigger"
  description = "Trigger used to build image and push it to artifact registry"
  github {
    owner = "nico-iaco"
    name = "funny-converter-be"
    push {
      branch = "main"
    }
  }
  ignored_files = [
    "terraform/**",
    "README.md",
    ".github/**",
    ".gitignore"
  ]

  substitutions = {
    _REPOSITORY = google_artifact_registry_repository.funny-converter-ar.name
    _IMAGE = "funny-converter-be"
  }

  filename = "cloudbuild.yaml"
  depends_on = [google_project_service.cloud_build_api]
}

resource "google_artifact_registry_repository" "funny-converter-ar" {
  provider = google-beta

  location = local.region
  repository_id = "funny-converter-ar"
  description = "docker repository for funny converter project"
  format = "DOCKER"
  depends_on = [google_project_service.artifact_registry_api]
}