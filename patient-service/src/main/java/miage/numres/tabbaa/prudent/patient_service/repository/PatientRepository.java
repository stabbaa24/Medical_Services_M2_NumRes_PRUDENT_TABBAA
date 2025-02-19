package miage.numres.tabbaa.prudent.patient_service.repository;

import miage.numres.tabbaa.prudent.patient_service.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findBySocialSecurityNumber(String socialSecurityNumber);
    boolean existsBySocialSecurityNumber(String socialSecurityNumber);
}