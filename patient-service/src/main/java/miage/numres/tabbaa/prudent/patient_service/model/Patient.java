package miage.numres.tabbaa.prudent.patient_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
@Schema(description = "Modèle représentant un patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du patient")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Numéro de sécurité sociale")
    private String socialSecurityNumber;

    @Column(nullable = false)
    @Schema(description = "Prénom du patient")
    private String firstName;

    @Column(nullable = false)
    @Schema(description = "Nom du patient")
    private String lastName;

    @Column(nullable = false)
    @Schema(description = "Date de naissance")
    private LocalDate dateOfBirth;

    @Schema(description = "Adresse email")
    private String email;

    @Schema(description = "Numéro de téléphone")
    private String phoneNumber;

    @Column(nullable = false)
    @Schema(description = "Adresse du patient")
    private String address;

    @Schema(description = "Groupe sanguin")
    private String bloodGroup;

    @Schema(description = "Allergies connues")
    private String allergies;
}