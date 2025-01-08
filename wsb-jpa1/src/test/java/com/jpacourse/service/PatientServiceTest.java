package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;


@SpringBootTest
@Transactional
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientDao patientDao;


    @Test
    public void testShouldDeletePatient() {

        // Given

        assertThat(patientService.findPatientById(1L))
            .as("Patient with ID 1 should exist")
            .isNotNull();
        int doctorCount = doctorDao.findAll().size();

        // When
        patientService.deletePatientById(1L);

        //then
        assertThat(patientService.findPatientById(1L)).isNull();
        assertThat(visitDao.findByPatientId(1L)).isEmpty(); //Sprawdzenie czy wizyty pacjenta zostały usunięte
        assertThat(doctorDao.findAll().size()).isEqualTo(doctorCount); //Sprawdzenie czy ilosc doktorow sie nie zmieniła

    }

    @Test
    public void testShouldFindPatientById() {

        //Given
        assertThat(true);
        //When
        PatientTO patient = patientService.findPatientById(1L);

        //Then
        assertThat(patient)
            .as("Patient with ID 1 should exist")
            .isNotNull();
        assertThat(patient.getId()).isEqualTo(1L);
        assertThat(patient.getDateOfBirth()).isEqualTo("1985-04-15");
        assertThat(patient.getEmail()).isEqualTo("adam.nowicki@example.com");
        assertThat(patient.getFirstName()).isEqualTo("Adam");
        assertThat(patient.getIsAllergic()).isFalse();
        assertThat(patient.getLastName()).isEqualTo("Nowicki");
        assertThat(patient.getPatientNumber()).isEqualTo("P001");
        assertThat(patient.getTelephoneNumber()).isEqualTo("600111222");

    }

    @Test
    public void testShouldFindVisitByPatientId() {

        //Given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Adam");
        patient.setLastName("Test");
        patient.setTelephoneNumber("123456789");
        patient.setEmail("A.Kowalski@email.com");
        patient.setPatientNumber("123456");
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setIsAllergic(false);
        patientDao.save(patient);

        patientDao.addVisitToPatient(patient.getId(), 1L, LocalDateTime.of(2025, 1, 5, 11, 12, 12), "test description1");
        patientDao.addVisitToPatient(patient.getId(), 2L, LocalDateTime.of(2026, 1, 5, 11, 12, 12), "test description2");

        //When

        List<VisitEntity> foundVisit = visitDao.findByPatientId(patient.getId());

        //Then

        assertThat(foundVisit).isNotNull();
        assertThat(foundVisit.size()).isEqualTo(2);
        assertThat(foundVisit.get(0).getPatient().getId()).isEqualTo(patient.getId());

    }

}

