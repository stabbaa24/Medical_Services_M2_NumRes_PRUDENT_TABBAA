# praticienService Microservice

## Overview

Le microservice **practitioner-service** est responsable de la gestion des opérations liées aux praticiens dans le système de gestion du cabinet médical. Il fournit des fonctionnalités CRUD (Create, Read, Update, Delete) pour les données des praticiens et s'intègre avec d'autres microservices via Eureka pour la découverte de services. Le service est construit à l'aide de Spring Boot et comprend une documentation Swagger pour faciliter l'exploration de l'API.

---
## Routes HTTP disponibles

| **HTTP Method**  | **URL**                    | **Description**                          |
|------------------|----------------------------|------------------------------------------|
| GET              | `/api/practitioners`       | Récupérer tous les praticiens.             |
| GET              | `/api/practitioners/{id}`  | Récupérer tous les praticiens par ID.      |
| POST             | `/api/practitioners`       | Creer un nouveau praticien.                |
| PUT              | `/api/practitioners/{id}`  | Mettre à joutr un praticien existant.      |
| DELETE           | `/api/practitioners/{id}`  | Supprimer un praticien par ID.             |

---

## Example JSON praticien

Voici un exemple d'un objet praticien

```json
{
  "rppsNumber": Integer,
  "firstName": String,
  "lastName": String,
  "speciality": Date,
  "email": String,
  "phoneNumber": Integer,
  "officeAddress": String,
  "available": Boolean
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
4. Le service sera accessible à l'adresse `http://localhost:8082`.
