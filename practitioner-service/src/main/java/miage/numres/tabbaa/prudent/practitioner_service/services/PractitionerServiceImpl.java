package miage.numres.tabbaa.prudent.practitioner_service.service.impl;

import lombok.RequiredArgsConstructor;
import miage.numres.tabbaa.prudent.practitioner_service.exception.PractitionerAlreadyExistsException;
import miage.numres.tabbaa.prudent.practitioner_service.exception.PractitionerNotFoundException;
import miage.numres.tabbaa.prudent.practitioner_service.model.Practitioner;
import miage.numres.tabbaa.prudent.practitioner_service.repository.PractitionerRepository;
import miage.numres.tabbaa.prudent.practitioner_service.services.PractitionerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PractitionerServiceImpl implements PractitionerService {
    private final PractitionerRepository practitionerRepository;

    @Override
    public List<Practitioner> getAllPractitioners() {
        return practitionerRepository.findAll();
    }

    @Override
    public Practitioner getPractitionerById(Long id) {
        return practitionerRepository.findById(id)
                .orElseThrow(() -> new PractitionerNotFoundException("Praticien non trouvé avec l'ID : " + id));
    }

    @Override
    public Practitioner createPractitioner(Practitioner practitioner) {
        if (practitionerRepository.findByRppsNumber(practitioner.getRppsNumber()).isPresent()) {
            throw new PractitionerAlreadyExistsException("Un praticien existe déjà avec le numéro RPPS : " + practitioner.getRppsNumber());
        }
        return practitionerRepository.save(practitioner);
    }

    @Override
    public Practitioner updatePractitioner(Long id, Practitioner practitioner) {
        Practitioner existingPractitioner = getPractitionerById(id);

        // Vérifier si le nouveau numéro RPPS existe déjà pour un autre praticien
        practitionerRepository.findByRppsNumber(practitioner.getRppsNumber())
                .ifPresent(p -> {
                    if (!p.getId().equals(id)) {
                        throw new PractitionerAlreadyExistsException("Un praticien existe déjà avec le numéro RPPS : " + practitioner.getRppsNumber());
                    }
                });

        practitioner.setId(id);
        return practitionerRepository.save(practitioner);
    }

    @Override
    public void deletePractitioner(Long id) {
        if (!practitionerRepository.existsById(id)) {
            throw new PractitionerNotFoundException("Praticien non trouvé avec l'ID : " + id);
        }
        practitionerRepository.deleteById(id);
    }
}