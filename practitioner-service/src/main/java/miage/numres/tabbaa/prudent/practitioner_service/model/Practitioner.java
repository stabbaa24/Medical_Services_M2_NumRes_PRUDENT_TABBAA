package miage.numres.tabbaa.prudent.practitioner_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "practitioners")
@Schema(description = "Modèle représentant un praticien")
public class Practitioner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du praticien")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Numéro RPPS du praticien")
    private String rppsNumber;

    @Column(nullable = false)
    @Schema(description = "Prénom du praticien")
    private String firstName;

    @Column(nullable = false)
    @Schema(description = "Nom du praticien")
    private String lastName;

    @Column(nullable = false)
    @Schema(description = "Spécialité du praticien")
    private String speciality;

    @Schema(description = "Adresse email")
    private String email;

    @Schema(description = "Numéro de téléphone")
    private String phoneNumber;

    @Column(nullable = false)
    @Schema(description = "Adresse du cabinet")
    private String officeAddress;

    @Schema(description = "Disponibilité du praticien")
    private boolean available;
}