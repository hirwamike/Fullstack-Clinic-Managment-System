package kigali.clinic.rw.controller;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kigali.clinic.rw.domain.*;
import kigali.clinic.rw.repository.*;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private final AppointmentRepository appointments;
    private final PatientRepository patients;
    private final DoctorRepository doctors;
    public AppointmentController(AppointmentRepository a, PatientRepository p, DoctorRepository d) { appointments=a; patients=p; doctors=d; }

    public record AppointmentRequest(Date appointmentDate, String reason, AppointmentStatus status, UUID patientId, UUID doctorId) {}
    public record AppointmentView(UUID id, Date appointmentDate, String reason, AppointmentStatus status, UUID patientId, String patientName, UUID doctorId, String doctorName) {}
    private AppointmentView view(Appointment a) {
        return new AppointmentView(a.getId(), a.getAppointmentDate(), a.getReason(), a.getStatus(),
            a.getPatient()==null?null:a.getPatient().getId(), a.getPatient()==null?"":a.getPatient().getFirstName()+" "+a.getPatient().getLastName(),
            a.getDoctor()==null?null:a.getDoctor().getId(), a.getDoctor()==null?"":a.getDoctor().getFirstName()+" "+a.getDoctor().getLastName());
    }
    @GetMapping public List<AppointmentView> all() { return appointments.findAllByOrderByAppointmentDateDesc().stream().map(this::view).toList(); }
    @PostMapping public ResponseEntity<?> create(@RequestBody AppointmentRequest r) {
        var p=patients.findById(r.patientId()); var d=doctors.findById(r.doctorId());
        if(p.isEmpty()||d.isEmpty()) return ResponseEntity.badRequest().body("Select an existing patient and doctor.");
        Appointment a=new Appointment(); a.setAppointmentDate(r.appointmentDate()); a.setReason(r.reason()); a.setStatus(r.status()==null?AppointmentStatus.SCHEDULED:r.status()); a.setPatient(p.get()); a.setDoctor(d.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(view(appointments.save(a)));
    }
    @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable UUID id,@RequestBody AppointmentRequest r) {
        var existing=appointments.findById(id); var p=patients.findById(r.patientId()); var d=doctors.findById(r.doctorId());
        if(existing.isEmpty()) return ResponseEntity.notFound().build(); if(p.isEmpty()||d.isEmpty()) return ResponseEntity.badRequest().body("Select an existing patient and doctor.");
        Appointment a=existing.get(); a.setAppointmentDate(r.appointmentDate()); a.setReason(r.reason()); a.setStatus(r.status()==null?AppointmentStatus.SCHEDULED:r.status()); a.setPatient(p.get()); a.setDoctor(d.get()); return ResponseEntity.ok(view(appointments.save(a)));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable UUID id) { if(!appointments.existsById(id)) return ResponseEntity.notFound().build(); appointments.deleteById(id); return ResponseEntity.noContent().build(); }
}
