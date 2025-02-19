# PatientService Microservice

## Overview

Le microservice **patient-service** est responsable de la gestion des opérations liées aux patients dans le système de gestion du cabinet médical. Il fournit des fonctionnalités CRUD (Create, Read, Update, Delete) pour les données des patients et s'intègre avec d'autres microservices via Eureka pour la découverte de services. Le service est construit à l'aide de Spring Boot et comprend une documentation Swagger pour faciliter l'exploration de l'API.

## Routes HTTP disponibles

| **HTTP Method**  | **URL**              | **Description**                          |
|------------------|----------------------|------------------------------------------|
| GET              | `/api/patients`      | Récupérer tous les patients.             |
| GET              | `/api/patients/{id}` | Récupérer tous les patients par ID.      |
| POST             | `/api/patients`      | Creer un nouveau patient.                |
| PUT              | `/api/patients/{id}` | Mettre à joutr un patient existant.      |
| DELETE           | `/api/patients/{id}` | Supprimer un patient par ID.             |

---

## Example JSON Patient

Voici un exemple d'un objet patient

```json
{
  "socialSecurityNumber": Integer,
  "firstName": String,
  "lastName": String,
  "dateOfBirth": Date,
  "email": String,
  "phoneNumber": Integer,
  "address": String,
  "bloodGroup": String,
  "allergies": String
}
```

---

## Instructions pour l'exécution du service

### Étapes d'exécution
1. Etre sur que le **Serveur Eureka** tourne sur `http://localhost:8761`.
2. Buildez le projet :
   ```bash
   mvn clean install
   ```
3. Exécutez le service :
   ```bash
   mvn spring-boot:run
   ```
4. Le service sera accessible à l'adresse `http://localhost:8081`.
