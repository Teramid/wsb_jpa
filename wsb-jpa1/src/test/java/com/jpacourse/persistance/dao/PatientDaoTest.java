package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.dao.DoctorDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldFindPatientById() {
        // given
        // when
        PatientEntity patientEntity = patientDao.findOne(1L);
        // then
        assertThat(patientEntity).isNotNull();
        assertThat(patientEntity.getFirstName()).isEqualTo("Adam");
    }

    @Test
    public void testShouldAddVisitToPatient() {
        // given
        LocalDateTime visitDate = LocalDateTime.now();
        assertThat(patientDao.findPatientbyId(1L)).isNotNull();
        assertThat(doctorDao.findDoctorById(1L)).isNotNull();
        int countVisitPatient = patientDao.findPatientbyId(1L).getVisits().size();
        int countVisitDoctor = doctorDao.findDoctorById(1L).getVisits().size();

        // when
        patientDao.addVisitToPatient(1L, 1L, visitDate, "test description");
        // then
        assertThat(patientDao.findPatientbyId(1L).getVisits().size()).isEqualTo(countVisitPatient + 1);
        assertThat(doctorDao.findDoctorById(1L).getVisits().size()).isEqualTo(countVisitDoctor + 1);
    }

}
