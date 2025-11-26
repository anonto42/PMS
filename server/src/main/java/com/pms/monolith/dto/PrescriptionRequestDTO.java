package com.pms.monolith.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.util.Date;

@Getter
@Setter
@Builder
public class PrescriptionRequestDTO {

    @NotNull(message = "Prescription date is required")
    private Date prescriptionDate;

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @NotNull(message = "Patient age is required")
    @Min(value = 1) @Max(value = 120)
    private Integer patientAge;

    @NotBlank(message = "Patient gender is required")
    private String patientGender;

    @NotBlank(message = "You must give the report of diagnosis")
    private String diagnosis;

    @NotNull(message = "You must provide medicines")
    private String medicines;

    @NotNull(message = "You must add a next visit date")
    private Date nextVisitDate;
}
