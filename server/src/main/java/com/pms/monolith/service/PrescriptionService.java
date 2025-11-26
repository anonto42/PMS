package com.pms.monolith.service;

import com.pms.monolith.entity.Prescription;
import com.pms.monolith.entity.User;
import com.pms.monolith.repository.PrescriptionRepository;
import com.pms.monolith.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private UserRepository userRepository;

    public Prescription createPrescription(Prescription prescription) {

        prescription.setCreatedAt(new Date());
        prescription.setUpdatedAt(new Date());

        return prescriptionRepository.save(prescription);
    }

    public Prescription getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
    }

    public List<Prescription> getPrescriptionsByUser(User creator) {
        return prescriptionRepository.findByCreator(creator);
    }

    public List<Prescription> getPrescriptionsByDateRange(Date startDate, Date endDate) {
        return prescriptionRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public List<Prescription> getPrescriptionsByUserAndDateRange(User creator, Date startDate, Date endDate) {
        return prescriptionRepository.findByCreatorAndCreatedAtBetween(creator, startDate, endDate);
    }

    public Prescription updatePrescription(Long id, Prescription updatedPrescription) {
        Prescription existing = getPrescriptionById(id);

        existing.setPatientName(updatedPrescription.getPatientName());
        existing.setPatientAge(updatedPrescription.getPatientAge());
        existing.setPatientGender(updatedPrescription.getPatientGender());
        existing.setDiagnosis(updatedPrescription.getDiagnosis());
        existing.setMedicines(updatedPrescription.getMedicines());
        existing.setNextVisitDate(updatedPrescription.getNextVisitDate());
        existing.setUpdatedAt(new Date());

        return prescriptionRepository.save(existing);
    }

    public void deletePrescription(Long id) {
        if (!prescriptionRepository.existsById(id)) {
            throw new RuntimeException("Prescription not found");
        }
        prescriptionRepository.deleteById(id);
    }
}
