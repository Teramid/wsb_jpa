package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import com.jpacourse.service.PatientService;
import com.jpacourse.persistence.entity.DoctorEntity;


@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {
    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
        PatientEntity patient = findPatientbyId(patientId);
        if (patient != null){
            VisitEntity visit = new VisitEntity();
            visit.setDoctor(entityManager.find(DoctorEntity.class, doctorId));
            visit.setTime(visitDate);
            visit.setDescription(description);
            patient.addVisit(visit);

            entityManager.merge(patient);

        }
    }
    @Override
    public PatientEntity findPatientbyId(long id) {
        return findOne(id);
    }
    
}
