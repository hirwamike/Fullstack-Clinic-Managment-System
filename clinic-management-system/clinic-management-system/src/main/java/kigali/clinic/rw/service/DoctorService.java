package kigali.clinic.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import kigali.clinic.rw.domain.Doctor;
import kigali.clinic.rw.repository.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getById(UUID id) {
        return doctorRepository.findById(id);
    }

    public Optional<Doctor> update(UUID id, Doctor details) {
        return doctorRepository.findById(id).map(doctor -> {
            doctor.setFirstName(details.getFirstName());
            doctor.setLastName(details.getLastName());
            doctor.setDateOfBirth(details.getDateOfBirth());
            doctor.setOffice(details.getOffice());
            doctor.setSpecializations(details.getSpecializations());
            return doctorRepository.save(doctor);
        });
    }

    public boolean delete(UUID id) {
        if (!doctorRepository.existsById(id)) {
            return false;
        }
        doctorRepository.deleteById(id);
        return true;
    }
}
