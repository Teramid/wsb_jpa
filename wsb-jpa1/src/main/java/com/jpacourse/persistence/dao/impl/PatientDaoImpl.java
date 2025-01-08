package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import com.jpacourse.service.PatientService;
import com.jpacourse.persistence.entity.DoctorEntity;

import com.jpacourse.persistence.dao.VisitDao;



@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @Autowired
    private VisitDao visitDao;

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
        PatientEntity patient = findPatientbyId(patientId);
        if (patient != null){
            VisitEntity visit = new VisitEntity();
            DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
            visit.setTime(visitDate);
            visit.setDescription(description);
            patient.addVisit(visit);
            doctor.addVisit(visit);
            entityManager.merge(patient);

        }
    }
    @Override
    public PatientEntity findPatientbyId(long id) {
        return entityManager.find(PatientEntity.class, id);
    }


    @Override
    public void deletePatientAndVisits(Long patientId) {
        PatientEntity patient = findPatientbyId(patientId);
        if (patient != null){
            entityManager.remove(patient);

        }
    }


}
