package kigali.clinic.rw.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kigali.clinic.rw.domain.Office;
import kigali.clinic.rw.service.OfficeService;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @PostMapping
    public ResponseEntity<Office> create(@RequestBody Office office) {
        return ResponseEntity.status(HttpStatus.CREATED).body(officeService.create(office));
    }

    // Original endpoint retained.
    @PostMapping("/save")
    public ResponseEntity<String> saveOffice(@RequestBody Office office) {
        return ResponseEntity.ok(officeService.saveOffice(office));
    }

    @GetMapping
    public ResponseEntity<List<Office>> getAll() {
        return ResponseEntity.ok(officeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Office> getById(@PathVariable UUID id) {
        return officeService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Office> update(@PathVariable UUID id, @RequestBody Office office) {
        return officeService.update(id, office)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return officeService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
