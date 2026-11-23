package kigali.clinic.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import kigali.clinic.rw.domain.Office;
import kigali.clinic.rw.repository.OfficeRepository;

@Service
public class OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public Office create(Office office) {
        return officeRepository.save(office);
    }

    public List<Office> getAll() {
        return officeRepository.findAll();
    }

    public Optional<Office> getById(UUID id) {
        return officeRepository.findById(id);
    }

    public Optional<Office> update(UUID id, Office details) {
        return officeRepository.findById(id).map(office -> {
            office.setName(details.getName());
            office.setOfficeNumber(details.getOfficeNumber());
            return officeRepository.save(office);
        });
    }

    public boolean delete(UUID id) {
        if (!officeRepository.existsById(id)) {
            return false;
        }
        officeRepository.deleteById(id);
        return true;
    }

    // Kept for compatibility with the original project endpoint.
    public String saveOffice(Office office) {
        create(office);
        return "Office is saved successfully";
    }
}
