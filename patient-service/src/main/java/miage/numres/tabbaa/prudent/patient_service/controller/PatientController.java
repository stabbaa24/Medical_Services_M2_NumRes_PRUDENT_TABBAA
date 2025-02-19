package miage.numres.tabbaa.prudent.patient_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import miage.numres.tabbaa.prudent.patient_service.model.Patient;
import miage.numres.tabbaa.prudent.patient_service.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@Tag(name = "Patient Controller", description = "API de gestion des patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Récupérer tous les patients")
    @CircuitBreaker(name = "patientService", fallbackMethod = "getAllPatientsFallback")
    @Retry(name = "patientService")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un patient par son ID")
    @CircuitBreaker(name = "patientService", fallbackMethod = "getPatientByIdFallback")
    @Retry(name = "patientService")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau patient")
    @CircuitBreaker(name = "patientService", fallbackMethod = "createPatientFallback")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un patient")
    @CircuitBreaker(name = "patientService", fallbackMethod = "updatePatientFallback")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.updatePatient(id, patient));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un patient")
    @CircuitBreaker(name = "patientService", fallbackMethod = "deletePatientFallback")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    // Méthodes de fallback
    public ResponseEntity<List<Patient>> getAllPatientsFallback(Exception ex) {
        // Retourner une liste vide ou des données en cache
        return ResponseEntity.ok(new ArrayList<>());
    }

    public ResponseEntity<Patient> getPatientByIdFallback(Long id, Exception ex) {
        // Retourner un patient par défaut ou null
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Patient> createPatientFallback(Patient patient, Exception ex) {
        // Logique de fallback pour la création
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Patient> updatePatientFallback(Long id, Patient patient, Exception ex) {
        // Logique de fallback pour la mise à jour
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Void> deletePatientFallback(Long id, Exception ex) {
        // Logique de fallback pour la suppression
        return ResponseEntity.internalServerError().build();
    }
}