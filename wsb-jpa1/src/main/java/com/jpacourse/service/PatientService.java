package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;

public interface PatientService {
    public PatientTO findPatientById(final Long id);

    void deletePatientById(final Long id);

    void savePatient(final PatientTO patientTO);

    void updatePatient(final PatientTO patientTO);


}
