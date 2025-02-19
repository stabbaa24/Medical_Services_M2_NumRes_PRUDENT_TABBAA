package miage.numres.tabbaa.prudent.gateway_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@Slf4j
@Tag(name = "Gateway API", description = "API Gateway pour la gestion du cabinet médical")
public class GatewayController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PATIENT_SERVICE = "patientService";
    private static final String PRACTITIONER_SERVICE = "practitionerService";
    private static final String PATIENT_BASE_URL = "http://PATIENT-SERVICE/api/patients";
    private static final String PRACTITIONER_BASE_URL = "http://PRACTITIONER-SERVICE/api/practitioners";

    // Patient Service Endpoints
    @GetMapping("/patients")
    @Operation(summary = "Récupérer tous les patients")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "getAllPatientsFallback")
    public ResponseEntity<?> getAllPatients() {
        log.info("Requête de récupération de tous les patients");
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(PATIENT_BASE_URL, Object.class);
            log.info("Patients récupérés avec succès");
            return response;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des patients: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/patients/{id}")
    @Operation(summary = "Récupérer un patient par son ID")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "getPatientByIdFallback")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        log.info("Requête de récupération du patient avec l'ID: {}", id);
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(PATIENT_BASE_URL + "/{id}", Object.class, id);
            log.info("Patient {} récupéré avec succès", id);
            return response;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du patient {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @PostMapping("/patients")
    @Operation(summary = "Créer un nouveau patient")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "createPatientFallback")
    public ResponseEntity<?> createPatient(@RequestBody Object patientRequest) {
        log.info("Requête de création d'un nouveau patient");
        try {
            ResponseEntity<?> response = restTemplate.postForEntity(PATIENT_BASE_URL, patientRequest, Object.class);
            log.info("Patient créé avec succès");
            return response;
        } catch (Exception e) {
            log.error("Erreur lors de la création du patient: {}", e.getMessage());
            throw e;
        }
    }

    @PutMapping("/patients/{id}")
    @Operation(summary = "Mettre à jour un patient")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "updatePatientFallback")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody Object patientRequest) {
        log.info("Requête de mise à jour du patient avec l'ID: {}", id);
        try {
            restTemplate.put(PATIENT_BASE_URL + "/{id}", patientRequest, id);
            log.info("Patient {} mis à jour avec succès", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour du patient {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @DeleteMapping("/patients/{id}")
    @Operation(summary = "Supprimer un patient")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "deletePatientFallback")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        log.info("Requête de suppression du patient avec l'ID: {}", id);
        try {
            restTemplate.delete(PATIENT_BASE_URL + "/{id}", id);
            log.info("Patient {} supprimé avec succès", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du patient {}: {}", id, e.getMessage());
            throw e;
        }
    }

    // Practitioner Service Endpoints
    @GetMapping("/practitioners")
    @Operation(summary = "Récupérer tous les praticiens")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "getAllPractitionersFallback")
    public ResponseEntity<?> getAllPractitioners() {
        log.info("Requête de récupération de tous les praticiens");
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(PRACTITIONER_BASE_URL, Object.class);
            log.info("Praticiens récupérés avec succès");
            return response;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des praticiens: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/practitioners/{id}")
    @Operation(summary = "Récupérer un praticien par son ID")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "getPractitionerByIdFallback")
    public ResponseEntity<?> getPractitionerById(@PathVariable Long id) {
        log.info("Requête de récupération du praticien avec l'ID: {}", id);
        try {
            ResponseEntity<?> response = restTemplate.getForEntity(PRACTITIONER_BASE_URL + "/{id}", Object.class, id);
            log.info("Praticien {} récupéré avec succès", id);
            return response;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du praticien {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @PostMapping("/practitioners")
    @Operation(summary = "Créer un nouveau praticien")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "createPractitionerFallback")
    public ResponseEntity<?> createPractitioner(@RequestBody Object practitionerRequest) {
        log.info("Requête de création d'un nouveau praticien");
        try {
            ResponseEntity<?> response = restTemplate.postForEntity(PRACTITIONER_BASE_URL, practitionerRequest, Object.class);
            log.info("Praticien créé avec succès");
            return response;
        } catch (Exception e) {
            log.error("Erreur lors de la création du praticien: {}", e.getMessage());
            throw e;
        }
    }

    @PutMapping("/practitioners/{id}")
    @Operation(summary = "Mettre à jour un praticien")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "updatePractitionerFallback")
    public ResponseEntity<?> updatePractitioner(@PathVariable Long id, @RequestBody Object practitionerRequest) {
        log.info("Requête de mise à jour du praticien avec l'ID: {}", id);
        try {
            restTemplate.put(PRACTITIONER_BASE_URL + "/{id}", practitionerRequest, id);
            log.info("Praticien {} mis à jour avec succès", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour du praticien {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @DeleteMapping("/practitioners/{id}")
    @Operation(summary = "Supprimer un praticien")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "deletePractitionerFallback")
    public ResponseEntity<?> deletePractitioner(@PathVariable Long id) {
        log.info("Requête de suppression du praticien avec l'ID: {}", id);
        try {
            restTemplate.delete(PRACTITIONER_BASE_URL + "/{id}", id);
            log.info("Praticien {} supprimé avec succès", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du praticien {}: {}", id, e.getMessage());
            throw e;
        }
    }

    // Méthodes Fallback pour le Service Patient
    public ResponseEntity<?> getAllPatientsFallback(Exception e) {
        log.error("Fallback: Service Patient indisponible lors de la récupération de tous les patients");
        return ResponseEntity.status(503)
                .body("Le service Patient est temporairement indisponible. Veuillez réessayer plus tard.");
    }

    public ResponseEntity<?> getPatientByIdFallback(Long id, Exception e) {
        log.error("Fallback: Service Patient indisponible lors de la récupération du patient {}", id);
        return ResponseEntity.status(503)
                .body("Le service Patient est temporairement indisponible. Veuillez réessayer plus tard.");
    }

    public ResponseEntity<?> createPatientFallback(Object patientRequest, Exception e) {
        log.error("Fallback: Service Patient indisponible lors de la création d'un patient");
        return ResponseEntity.status(503)
                .body("Le service Patient est temporairement indisponible. Veuillez réessayer plus tard.");
    }

    public ResponseEntity<?> updatePatientFallback(Long id, Object patientRequest, Exception e) {
        log.error("Fallback: Service Patient indisponible lors de la mise à jour du patient {}", id);
        return ResponseEntity.status(503)
                .body("Le service Patient est temporairement indisponible. Veuillez réessayer plus tard.");
    }

    public ResponseEntity<?> deletePatientFallback(Long id, Exception e) {
        log.error("Fallback: Service Patient indisponible lors de la suppression du patient {}", id);
        return ResponseEntity.status(503)
                .body("Le service Patient est temporairement indisponible. Veuillez réessayer plus tard.");
    }

    // Méthodes Fallback pour le Service Praticien
    public ResponseEntity<?> getAllPractitionersFallback(Exception e) {
        log.error("Fallback: Service Praticien indisponible lors de la récupération de tous les praticiens");
        return ResponseEntity.status(503)
                .body("Le service Praticien est temporairement indisponible. Veuillez réessayer plus tard.");
    }

    public ResponseEntity<?> getPractitionerByIdFallback(Long id, Exception e) {
        log.error("Fallback: Service Praticien indisponible lors de la récupération du praticien {}", id);
        return ResponseEntity.status(503)
                .body("Le service Praticien est temporairement indisponible. Veuillez réessayer plus tard.");
    }

    public ResponseEntity<?> createPractitionerFallback(Object practitionerRequest, Exception e) {
        log.error("Fallback: Service Praticien indisponible lors de la création d'un praticien");
        return ResponseEntity.status(503)
                .body("Le service Praticien est temporairement indisponible. Veuillez réessayer plus tard.");
    }

    public ResponseEntity<?> updatePractitionerFallback(Long id, Object practitionerRequest, Exception e) {
        log.error("Fallback: Service Praticien indisponible lors de la mise à jour du praticien {}", id);
        return ResponseEntity.status(503)
                .body("Le service Praticien est temporairement indisponible. Veuillez réessayer plus tard.");
    }

    public ResponseEntity<?> deletePractitionerFallback(Long id, Exception e) {
        log.error("Fallback: Service Praticien indisponible lors de la suppression du praticien {}", id);
        return ResponseEntity.status(503)
                .body("Le service Praticien est temporairement indisponible. Veuillez réessayer plus tard.");
    }
}