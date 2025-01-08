package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description);

    PatientEntity findPatientbyId(long id);

    void deletePatientAndVisits(Long patientId);

    public List<PatientEntity> findPatientByLastname(String lastname);

    public List<PatientEntity> findPatientsWithVisitsAboveX(int x);

    public List<PatientEntity> findPatiensWithAllergicStatus(boolean isAllergic);

    public List<PatientEntity> findPatientsWithVisitsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

}
