package miage.numres.tabbaa.prudent.patient_service.services;

import miage.numres.tabbaa.prudent.patient_service.model.Patient;
import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient createPatient(Patient patient);
    Patient updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
}