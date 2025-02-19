package miage.numres.tabbaa.prudent.practitioner_service.services;

import miage.numres.tabbaa.prudent.practitioner_service.model.Practitioner;
import java.util.List;

public interface PractitionerService {
    List<Practitioner> getAllPractitioners();
    Practitioner getPractitionerById(Long id);
    Practitioner createPractitioner(Practitioner practitioner);
    Practitioner updatePractitioner(Long id, Practitioner practitioner);
    void deletePractitioner(Long id);
}