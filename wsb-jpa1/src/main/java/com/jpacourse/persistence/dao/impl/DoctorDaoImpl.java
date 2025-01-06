package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import org.springframework.stereotype.Repository;

@Repository

public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {

    public DoctorEntity findDoctorById(long id)
    {
        return entityManager.find(DoctorEntity.class, id);
    }


}
