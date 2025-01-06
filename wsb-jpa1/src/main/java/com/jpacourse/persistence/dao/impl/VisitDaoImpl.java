package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitDao {

    @Override
    public List<VisitEntity> findByPatientId(Long patientId) {
        return entityManager
                .createQuery("SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId", VisitEntity.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    @Override
    public void removeVisitsByPatientId(Long patientId) {
        entityManager
                .createQuery("DELETE FROM VisitEntity v WHERE v.patient.id = :patientId")
                .setParameter("patientId", patientId)
                .executeUpdate();
    }


}
