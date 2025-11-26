package com.pms.monolith.dto;

import com.pms.monolith.entity.Prescription;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PrescriptionResponseDTO {

    private UUID id;
    private String creatorName;
    private String patientName;
    private Integer patientAge;
    private String patientGender;
    private String diagnosis;
    private String medicines;
    private Date prescriptionDate;
    private String nextVisitDate;

    public static PrescriptionResponseDTO fromEntity(Prescription p) {
        if (p == null) return null;

        return PrescriptionResponseDTO.builder()
                .id(p.getId())
                .creatorName(p.getCreator().getName())
                .patientName(p.getPatientName())
                .patientAge(p.getPatientAge())
                .patientGender(p.getPatientGender())
                .diagnosis(p.getDiagnosis())
                .medicines(p.getMedicines())
                .prescriptionDate(p.getPrescriptionDate())
                .nextVisitDate(p.getNextVisitDate() != null ? p.getNextVisitDate().toString() : null)
                .build();
    }
}
