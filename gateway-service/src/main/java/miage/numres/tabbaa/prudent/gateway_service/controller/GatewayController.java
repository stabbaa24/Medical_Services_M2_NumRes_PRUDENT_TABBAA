package miage.numres.tabbaa.prudent.gateway_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import miage.numres.tabbaa.prudent.gateway_service.model.ErrorResponse;
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
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "patientServiceFallback")
    public ResponseEntity<?> getAllPatients() {
        log.info("Requête de récupération de tous les patients");
        return restTemplate.getForEntity(PATIENT_BASE_URL, Object.class);
    }

    @GetMapping("/patients/{id}")
    @Operation(summary = "Récupérer un patient par son ID")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "patientServiceFallback")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        log.info("Requête de récupération du patient avec l'ID: {}", id);
        return restTemplate.getForEntity(PATIENT_BASE_URL + "/{id}", Object.class, id);
    }

    // Practitioner Service Endpoints
    @GetMapping("/practitioners")
    @Operation(summary = "Récupérer tous les praticiens")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "practitionerServiceFallback")
    public ResponseEntity<?> getAllPractitioners() {
        log.info("Requête de récupération de tous les praticiens");
        return restTemplate.getForEntity(PRACTITIONER_BASE_URL, Object.class);
    }

    @GetMapping("/practitioners/{id}")
    @Operation(summary = "Récupérer un praticien par son ID")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "practitionerServiceFallback")
    public ResponseEntity<?> getPractitionerById(@PathVariable Long id) {
        log.info("Requête de récupération du praticien avec l'ID: {}", id);
        return restTemplate.getForEntity(PRACTITIONER_BASE_URL + "/{id}", Object.class, id);
    }

    // Fallback Methods
    public ResponseEntity<?> patientServiceFallback(Exception e) {
        log.error("Circuit Breaker activé : Service Patient indisponible", e);
        return ResponseEntity.status(503)
                .body(new ErrorResponse(
                        "SERVICE_UNAVAILABLE",
                        "Le service Patient est temporairement indisponible. Veuillez réessayer plus tard.",
                        e.getMessage()));
    }

    public ResponseEntity<?> practitionerServiceFallback(Exception e) {
        log.error("Circuit Breaker activé : Service Praticien indisponible", e);
        return ResponseEntity.status(503)
                .body(new ErrorResponse(
                        "SERVICE_UNAVAILABLE",
                        "Le service Praticien est temporairement indisponible. Veuillez réessayer plus tard.",
                        e.getMessage()));
    }
}