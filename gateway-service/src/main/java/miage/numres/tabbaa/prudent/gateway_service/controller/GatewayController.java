package miage.numres.tabbaa.prudent.gateway_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import miage.numres.tabbaa.prudent.gateway_service.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    // ========== Patient Service Endpoints ==========
    @GetMapping("/patients")
    @Operation(summary = "Récupérer tous les patients")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "getAllPatientsFallback")
    public ResponseEntity<?> getAllPatients() {
        log.info("Requête de récupération de tous les patients");
        return restTemplate.getForEntity(PATIENT_BASE_URL, Object.class);
    }

    @GetMapping("/patients/{id}")
    @Operation(summary = "Récupérer un patient par son ID")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "getPatientByIdFallback")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        log.info("Requête de récupération du patient avec l'ID: {}", id);
        return restTemplate.getForEntity(PATIENT_BASE_URL + "/{id}", Object.class, id);
    }

    @PostMapping("/patients")
    @Operation(summary = "Créer un nouveau patient")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "createPatientFallback")
    public ResponseEntity<?> createPatient(@RequestBody Object patientRequest) {
        log.info("Requête de création d'un nouveau patient");
        return restTemplate.postForEntity(PATIENT_BASE_URL, patientRequest, Object.class);
    }

    @PutMapping("/patients/{id}")
    @Operation(summary = "Mettre à jour un patient")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "updatePatientFallback")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody Object patientRequest) {
        log.info("Requête de mise à jour du patient avec l'ID: {}", id);
        return restTemplate.exchange(PATIENT_BASE_URL + "/{id}", HttpMethod.PUT,
                new HttpEntity<>(patientRequest), Object.class, id);
    }

    @DeleteMapping("/patients/{id}")
    @Operation(summary = "Supprimer un patient")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "deletePatientFallback")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        log.info("Requête de suppression du patient avec l'ID: {}", id);
        restTemplate.delete(PATIENT_BASE_URL + "/{id}", id);
        return ResponseEntity.ok().build();
    }

    // ========== Practitioner Service Endpoints ==========
    @GetMapping("/practitioners")
    @Operation(summary = "Récupérer tous les praticiens")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "getAllPractitionersFallback")
    public ResponseEntity<?> getAllPractitioners() {
        log.info("Requête de récupération de tous les praticiens");
        return restTemplate.getForEntity(PRACTITIONER_BASE_URL, Object.class);
    }

    @GetMapping("/practitioners/{id}")
    @Operation(summary = "Récupérer un praticien par son ID")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "getPractitionerByIdFallback")
    public ResponseEntity<?> getPractitionerById(@PathVariable Long id) {
        log.info("Requête de récupération du praticien avec l'ID: {}", id);
        return restTemplate.getForEntity(PRACTITIONER_BASE_URL + "/{id}", Object.class, id);
    }

    @PostMapping("/practitioners")
    @Operation(summary = "Créer un nouveau praticien")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "createPractitionerFallback")
    public ResponseEntity<?> createPractitioner(@RequestBody Object practitionerRequest) {
        log.info("Requête de création d'un nouveau praticien");
        return restTemplate.postForEntity(PRACTITIONER_BASE_URL, practitionerRequest, Object.class);
    }

    @PutMapping("/practitioners/{id}")
    @Operation(summary = "Mettre à jour un praticien")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "updatePractitionerFallback")
    public ResponseEntity<?> updatePractitioner(@PathVariable Long id, @RequestBody Object practitionerRequest) {
        log.info("Requête de mise à jour du praticien avec l'ID: {}", id);
        return restTemplate.exchange(PRACTITIONER_BASE_URL + "/{id}", HttpMethod.PUT,
                new HttpEntity<>(practitionerRequest), Object.class, id);
    }

    @DeleteMapping("/practitioners/{id}")
    @Operation(summary = "Supprimer un praticien")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "deletePractitionerFallback")
    public ResponseEntity<?> deletePractitioner(@PathVariable Long id) {
        log.info("Requête de suppression du praticien avec l'ID: {}", id);
        restTemplate.delete(PRACTITIONER_BASE_URL + "/{id}", id);
        return ResponseEntity.ok().build();
    }

    // ========== Patient Service Fallback Methods ==========
    public ResponseEntity<?> getAllPatientsFallback(Exception e) {
        return ResponseEntity.status(503).body(new ErrorResponse(
                "PATIENT_SERVICE_UNAVAILABLE",
                "Le service de liste des patients est temporairement indisponible",
                e.getMessage()));
    }

    public ResponseEntity<?> getPatientByIdFallback(Long id, Exception e) {
        return ResponseEntity.status(503).body(new ErrorResponse(
                "PATIENT_SERVICE_UNAVAILABLE",
                "Le service de récupération du patient est temporairement indisponible",
                e.getMessage()));
    }

    public ResponseEntity<?> createPatientFallback(Object patientRequest, Exception e) {
        return ResponseEntity.status(503).body(new ErrorResponse(
                "PATIENT_SERVICE_UNAVAILABLE",
                "Le service de création de patient est temporairement indisponible",
                e.getMessage()));
    }

    public ResponseEntity<?> updatePatientFallback(Long id, Object patientRequest, Exception e) {
        return ResponseEntity.status(503).body(new ErrorResponse(
                "PATIENT_SERVICE_UNAVAILABLE",
                "Le service de mise à jour du patient est temporairement indisponible",
                e.getMessage()));
    }

    public ResponseEntity<?> deletePatientFallback(Long id, Exception e) {
        return ResponseEntity.status(503).body(new ErrorResponse(
                "PATIENT_SERVICE_UNAVAILABLE",
                "Le service de suppression du patient est temporairement indisponible",
                e.getMessage()));
    }

    // ========== Practitioner Service Fallback Methods ==========
    public ResponseEntity<?> getAllPractitionersFallback(Exception e) {
        return ResponseEntity.status(503).body(new ErrorResponse(
                "PRACTITIONER_SERVICE_UNAVAILABLE",
                "Le service de liste des praticiens est temporairement indisponible",
                e.getMessage()));
    }

    public ResponseEntity<?> getPractitionerByIdFallback(Long id, Exception e) {
        return ResponseEntity.status(503).body(new ErrorResponse(
                "PRACTITIONER_SERVICE_UNAVAILABLE",
                "Le service de récupération du praticien est temporairement indisponible",
                e.getMessage()));
    }

    public ResponseEntity<?> createPractitionerFallback(Object practitionerRequest, Exception e) {
        return ResponseEntity.status(503).body(new ErrorResponse(
                "PRACTITIONER_SERVICE_UNAVAILABLE",
                "Le service de création de praticien est temporairement indisponible",
                e.getMessage()));
    }

    public ResponseEntity<?> updatePractitionerFallback(Long id, Object practitionerRequest, Exception e) {
        return ResponseEntity.status(503).body(new ErrorResponse(
                "PRACTITIONER_SERVICE_UNAVAILABLE",
                "Le service de mise à jour du praticien est temporairement indisponible",
                e.getMessage()));
    }

    public ResponseEntity<?> deletePractitionerFallback(Long id, Exception e) {
        return ResponseEntity.status(503).body(new ErrorResponse(
                "PRACTITIONER_SERVICE_UNAVAILABLE",
                "Le service de suppression du praticien est temporairement indisponible",
                e.getMessage()));
    }
}