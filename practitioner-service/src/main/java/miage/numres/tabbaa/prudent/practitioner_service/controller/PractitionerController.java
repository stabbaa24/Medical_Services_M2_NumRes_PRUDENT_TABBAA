package miage.numres.tabbaa.prudent.practitioner_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import miage.numres.tabbaa.prudent.practitioner_service.model.Practitioner;
import miage.numres.tabbaa.prudent.practitioner_service.services.PractitionerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/practitioners")
@RequiredArgsConstructor
@Tag(name = "Practitioner Controller", description = "API de gestion des praticiens")
public class PractitionerController {
    private final PractitionerService practitionerService;

    @GetMapping
    @Operation(summary = "Récupérer tous les praticiens")
    @CircuitBreaker(name = "practitionerService", fallbackMethod = "getAllPractitionersFallback")
    @Retry(name = "practitionerService")
    public ResponseEntity<List<Practitioner>> getAllPractitioners() {
        return ResponseEntity.ok(practitionerService.getAllPractitioners());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un praticien par son ID")
    @CircuitBreaker(name = "practitionerService", fallbackMethod = "getPractitionerByIdFallback")
    @Retry(name = "practitionerService")
    public ResponseEntity<Practitioner> getPractitionerById(@PathVariable Long id) {
        return ResponseEntity.ok(practitionerService.getPractitionerById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau praticien")
    @CircuitBreaker(name = "practitionerService", fallbackMethod = "createPractitionerFallback")
    public ResponseEntity<Practitioner> createPractitioner(@RequestBody Practitioner practitioner) {
        return ResponseEntity.ok(practitionerService.createPractitioner(practitioner));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un praticien")
    @CircuitBreaker(name = "practitionerService", fallbackMethod = "updatePractitionerFallback")
    public ResponseEntity<Practitioner> updatePractitioner(@PathVariable Long id, @RequestBody Practitioner practitioner) {
        return ResponseEntity.ok(practitionerService.updatePractitioner(id, practitioner));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un praticien")
    @CircuitBreaker(name = "practitionerService", fallbackMethod = "deletePractitionerFallback")
    public ResponseEntity<Void> deletePractitioner(@PathVariable Long id) {
        practitionerService.deletePractitioner(id);
        return ResponseEntity.noContent().build();
    }

    // Méthodes de fallback
    public ResponseEntity<List<Practitioner>> getAllPractitionersFallback(Exception ex) {
        return ResponseEntity.ok(new ArrayList<>());
    }

    public ResponseEntity<Practitioner> getPractitionerByIdFallback(Long id, Exception ex) {
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Practitioner> createPractitionerFallback(Practitioner practitioner, Exception ex) {
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Practitioner> updatePractitionerFallback(Long id, Practitioner practitioner, Exception ex) {
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Void> deletePractitionerFallback(Long id, Exception ex) {
        return ResponseEntity.internalServerError().build();
    }
}