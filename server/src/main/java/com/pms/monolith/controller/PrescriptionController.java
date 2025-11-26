package com.pms.monolith.controller;

import com.pms.monolith.dto.PrescriptionRequestDTO;
import com.pms.monolith.dto.PrescriptionResponseDTO;
import com.pms.monolith.entity.Prescription;
import com.pms.monolith.entity.User;
import com.pms.monolith.service.PrescriptionService;
import com.pms.monolith.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public PrescriptionResponseDTO createPrescription(
            @Valid @RequestBody PrescriptionRequestDTO dto,
            @RequestParam UUID creatorId
    ) {
        User creator = userService.getUserById(creatorId);

        Prescription prescription = new Prescription();
        prescription.setCreator(creator);
        prescription.setPrescriptionDate(dto.getPrescriptionDate());
        prescription.setPatientName(dto.getPatientName());
        prescription.setPatientAge(dto.getPatientAge());
        prescription.setPatientGender(dto.getPatientGender());
        prescription.setDiagnosis(dto.getDiagnosis());
        prescription.setMedicines(dto.getMedicines());
        prescription.setNextVisitDate(dto.getNextVisitDate());

        Prescription saved = prescriptionService.createPrescription(prescription);

        return PrescriptionResponseDTO.fromEntity(saved);
    }

    @GetMapping("/{id}")
    public PrescriptionResponseDTO getPrescription(@PathVariable Long id) {
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        return PrescriptionResponseDTO.fromEntity(prescription);
    }

    @GetMapping("/by-user/{userId}")
    public List<PrescriptionResponseDTO> getByUser(@PathVariable UUID userId) {
        User creator = userService.getUserById(userId);
        List<Prescription> list = prescriptionService.getPrescriptionsByUser(creator);

        return list.stream()
                .map(PrescriptionResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-date")
    public List<PrescriptionResponseDTO> getByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        List<Prescription> list = prescriptionService.getPrescriptionsByDateRange(startDate, endDate);

        return list.stream()
                .map(PrescriptionResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<PrescriptionResponseDTO> getByUserAndDateRange(
            @RequestParam UUID userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        User creator = userService.getUserById(userId);
        List<Prescription> list = prescriptionService.getPrescriptionsByUserAndDateRange(creator, startDate, endDate);

        return list.stream()
                .map(PrescriptionResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public PrescriptionResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody PrescriptionRequestDTO dto
    ) {
        Prescription updatedPrescription = new Prescription();
        updatedPrescription.setPatientName(dto.getPatientName());
        updatedPrescription.setPatientAge(dto.getPatientAge());
        updatedPrescription.setPatientGender(dto.getPatientGender());
        updatedPrescription.setDiagnosis(dto.getDiagnosis());
        updatedPrescription.setMedicines(dto.getMedicines());
        updatedPrescription.setNextVisitDate(dto.getNextVisitDate());

        Prescription saved = prescriptionService.updatePrescription(id, updatedPrescription);

        return PrescriptionResponseDTO.fromEntity(saved);
    }

    @DeleteMapping("/{id}")
    public String deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return "Prescription deleted successfully";
    }
}
