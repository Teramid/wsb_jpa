package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao pPatientDao) {
        patientDao = pPatientDao;
    }

    @Override
    @Transactional(readOnly = true)
    public PatientTO findPatientById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public void deletePatientById(Long id) {
        patientDao.deletePatientAndVisits(id);
    }

    @Override
    public void savePatient(PatientTO patientTO) {
        PatientEntity entity = PatientMapper.mapToEntity(patientTO);
        patientDao.save(entity);
    }


    @Override
    public void updatePatient(PatientTO patientTO) {
        PatientEntity entity = PatientMapper.mapToEntity(patientTO);
        patientDao.update(entity);
    }

}