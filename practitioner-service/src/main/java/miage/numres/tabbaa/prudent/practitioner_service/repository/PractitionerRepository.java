package miage.numres.tabbaa.prudent.practitioner_service.repository;

import miage.numres.tabbaa.prudent.practitioner_service.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {
    Optional<Practitioner> findByRppsNumber(String rppsNumber);
}