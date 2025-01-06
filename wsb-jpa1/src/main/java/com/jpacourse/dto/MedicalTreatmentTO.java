package com.jpacourse.dto;

import javax.persistence.Enumerated;

import com.jpacourse.persistence.enums.TreatmentType;

public class MedicalTreatmentTO {

    private Long id;
    private String description;
    private TreatmentType treatmentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TreatmentType getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(TreatmentType treatmentType) {
        this.treatmentType = treatmentType;
    }


}
