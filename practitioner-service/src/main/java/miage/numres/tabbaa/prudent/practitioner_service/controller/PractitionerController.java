package miage.numres.tabbaa.prudent.practitioner_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import miage.numres.tabbaa.prudent.practitioner_service.model.Practitioner;
import miage.numres.tabbaa.prudent.practitioner_service.services.PractitionerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/practitioners")
@RequiredArgsConstructor
@Tag(name = "Practitioner Controller", description = "API de gestion des praticiens")
public class PractitionerController {
    private final PractitionerService practitionerService;

    @GetMapping
    @Operation(summary = "Récupérer tous les praticiens")
    public ResponseEntity<List<Practitioner>> getAllPractitioners() {
        return ResponseEntity.ok(practitionerService.getAllPractitioners());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un praticien par son ID")
    public ResponseEntity<Practitioner> getPractitionerById(@PathVariable Long id) {
        return ResponseEntity.ok(practitionerService.getPractitionerById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau praticien")
    public ResponseEntity<Practitioner> createPractitioner(@RequestBody Practitioner practitioner) {
        return ResponseEntity.ok(practitionerService.createPractitioner(practitioner));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un praticien")
    public ResponseEntity<Practitioner> updatePractitioner(@PathVariable Long id, @RequestBody Practitioner practitioner) {
        return ResponseEntity.ok(practitionerService.updatePractitioner(id, practitioner));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un praticien")
    public ResponseEntity<Void> deletePractitioner(@PathVariable Long id) {
        practitionerService.deletePractitioner(id);
        return ResponseEntity.noContent().build();
    }
}