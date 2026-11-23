package kigali.clinic.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import kigali.clinic.rw.domain.Patient;
import kigali.clinic.rw.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getById(UUID id) {
        return patientRepository.findById(id);
    }

    public Optional<Patient> update(UUID id, Patient details) {
        return patientRepository.findById(id).map(patient -> {
            patient.setFirstName(details.getFirstName());
            patient.setLastName(details.getLastName());
            patient.setDateOfBirth(details.getDateOfBirth());
            return patientRepository.save(patient);
        });
    }

    public boolean delete(UUID id) {
        if (!patientRepository.existsById(id)) {
            return false;
        }
        patientRepository.deleteById(id);
        return true;
    }
}
