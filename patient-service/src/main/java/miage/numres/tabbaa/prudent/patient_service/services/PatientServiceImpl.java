package miage.numres.tabbaa.prudent.patient_service.services;

import lombok.RequiredArgsConstructor;
import miage.numres.tabbaa.prudent.patient_service.exception.PatientNotFoundException;
import miage.numres.tabbaa.prudent.patient_service.model.Patient;
import miage.numres.tabbaa.prudent.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient non trouvé avec l'ID: " + id));
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient non trouvé avec l'ID: " + id);
        }
        patient.setId(id);
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient non trouvé avec l'ID: " + id);
        }
        patientRepository.deleteById(id);
    }
}