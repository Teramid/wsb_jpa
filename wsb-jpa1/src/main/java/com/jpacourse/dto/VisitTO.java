package com.jpacourse.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import com.jpacourse.persistence.enums.TreatmentType;

public class VisitTO {
    private Long id;
    private String description;
    private LocalDateTime time;
    private String doctorName;
    private String doctroSurname;
    private Collection<TreatmentType> treatmentTypes;

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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctroSurname() {
        return doctroSurname;
    }

    public void setDoctroSurname(String doctroSurname) {
        this.doctroSurname = doctroSurname;
    }

    public Collection<TreatmentType> getTreatmentTypes() {
        return treatmentTypes;
    }

    public void setTreatmentTypes(Collection<TreatmentType> treatmentTypes) {
        this.treatmentTypes = treatmentTypes;
    }




}
