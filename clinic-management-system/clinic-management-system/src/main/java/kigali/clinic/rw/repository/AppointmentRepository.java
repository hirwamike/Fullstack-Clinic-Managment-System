package kigali.clinic.rw.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import kigali.clinic.rw.domain.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findAllByOrderByAppointmentDateDesc();
}
