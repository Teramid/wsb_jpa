package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import com.jpacourse.service.PatientService;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {
    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
        PatientEntity patient = PatientService.findPatientById(patientId);
    @Override
    public PatientEntity findPatientbyId(long id) {
        return findOne(id);
    }
}
