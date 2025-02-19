# Medical Services Project S. TABBAA & A. PRUDENT

## Description
Ce projet est une architecture de microservices permettant la gestion des services médicaux. Il inclut la gestion des patients, des praticiens et des rendez-vous, avec une passerelle API et un serveur Eureka pour la découverte des services.

## Architecture
Le projet est composé des microservices suivants :
- **Eureka Server** : Service de découverte permettant l'enregistrement des autres microservices. Intègre un module Admin pour récupérer les logs et autres informations.
- **Gateway Service** : Passerelle API permettant la redirection des requêtes. Intègre un watcher qui requête les microservices et gère la résilience.
- **Patient Service** : Microservice de gestion des patients.
- **Practitioner Service** : Microservice de gestion des praticiens.

Chaque microservice est développé en Spring Boot avec Maven.

### Ordre de lancement :
1. **Eureka Server**
2. **Patient Service**
3. **Practitionner Service**
4. **Gateway Service**

## Prérequis
Avant d'exécuter le projet, assurez-vous d'avoir installé :
- **Docker & Docker Compose**
- **Java 17**
- **Maven**

## Installation & Exécution
### 1. Cloner le projet
```sh
git clone <repo_url>
cd Medical_Services_Project
```

### 2. Construire et lancer les microservices avec Docker
```sh
docker-compose build
docker-compose up -d
```

### 3. Accéder aux services
- **Eureka Server** : [http://localhost:8761](http://localhost:8761)
- **Gateway Service** : [http://localhost:8080](http://localhost:8080)
- **Patient Service** : [http://localhost:8082](http://localhost:8081)
- **Practitioner Service** : [http://localhost:8083](http://localhost:8082)

## Configuration
Les configurations des microservices (ports, bases de données, etc.) se trouvent dans leurs fichiers `application.properties` ou `application.yml`.

## Développement
### Construire manuellement chaque microservice
```sh
cd <service-name>
mvn clean package -DskipTests
java -jar target/<service-name>.jar
```
