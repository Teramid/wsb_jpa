package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;


    @Test
    public void testShouldDeletePatient() {

        // Given

        //patientService.getPatient(1L).isNotNull();
        assertThat(patientService.findPatientById(1L))
            .as("Patient with ID 1 should exist")
            .isNotNull();
        //int visitCount = visitDao.findall().size();
        int doctorCount = doctorDao.findAll().size();

        // When
        patientService.deletePatientById(1L);

        //then
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

}
