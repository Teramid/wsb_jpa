package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;

public interface PatientService {
    public PatientTO findPatientById(final Long id);
}
