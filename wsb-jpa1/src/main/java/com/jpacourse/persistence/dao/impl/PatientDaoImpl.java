package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

import com.jpacourse.persistence.entity.DoctorEntity;



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
            visit.setPatient(patient);
            visit.setDoctor(doctor);
            visitDao.save(visit);
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
        if (patient != null) {
            entityManager.remove(patient);

        }
    }

    @Override
    public List<PatientEntity> findPatientByLastname(String lastname)
    {
        return entityManager
                .createQuery("SELECT pat FROM PatientEntity pat WHERE pat.lastName = :lastn", PatientEntity.class)
                .setParameter("lastn", lastname)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithVisitsAboveX(int x) {
        return entityManager
                .createQuery("SELECT pat FROM PatientEntity pat WHERE SIZE(pat.visits) > :x", PatientEntity.class)
                .setParameter("x", x)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatiensWithAllergicStatus(boolean allergic_state) {
        return entityManager
                .createQuery("SELECT pat FROM PatientEntity pat WHERE pat.isAllergic IN (:allergic_state)",
                        PatientEntity.class)
                .setParameter("allergic_state", allergic_state)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithVisitsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return entityManager
                .createQuery(
                        "SELECT pat FROM PatientEntity pat JOIN pat.visits visit WHERE visit.time BETWEEN :dateStart and :dateEnd",
                        PatientEntity.class)
                .setParameter("dateStart", startDate)
                .setParameter("dateEnd", endDate)
                .getResultList();
    }

}
