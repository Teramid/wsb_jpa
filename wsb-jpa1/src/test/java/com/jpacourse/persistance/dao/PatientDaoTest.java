package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.dao.DoctorDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.jpacourse.persistence.enums.Specialization;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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
        PatientEntity patient = patientDao.findPatientbyId(1L);
        DoctorEntity doctor = doctorDao.findDoctorById(1L);

        assertThat(patient).isNotNull();
        assertThat(doctor).isNotNull();

        int countVisitPatient = patient.getVisits().size();
        int countVisitDoctor = doctor.getVisits().size();

        // when
        patientDao.addVisitToPatient(1L, 1L, visitDate, "test description");
        // then
        assertThat(patient.getVisits().size()).isEqualTo(countVisitPatient + 1);
        assertThat(doctor.getVisits().size()).isEqualTo(countVisitDoctor + 1);
    }

    @Test
    public void testShouldFindPatientsByLastname() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Adam");
        patient.setLastName("Test");
        patient.setTelephoneNumber("123456789");
        patient.setEmail("A.Kowalski@email.com");
        patient.setPatientNumber("123456");
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setIsAllergic(false);
        patientDao.save(patient);

        PatientEntity patient2 = new PatientEntity();
        patient2.setFirstName("Krzysztof");
        patient2.setLastName("Test");
        patient2.setTelephoneNumber("987654322");
        patient2.setEmail("KT@email.com");
        patient2.setPatientNumber("654321");
        patient2.setDateOfBirth(LocalDate.of(1991, 1, 1));
        patient2.setIsAllergic(true);
        patientDao.save(patient2);

        // when
        List<PatientEntity> patients = patientDao.findPatientByLastname("Test");
        // then
        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(2);
    }

    @Test
    public void testShouldFindPatientWithVisitsAboveX() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Adam");
        patient.setLastName("Test");
        patient.setTelephoneNumber("123456789");
        patient.setEmail("A.Kowalski@email.com");
        patient.setPatientNumber("123456");
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setIsAllergic(false);
        patientDao.save(patient);
        long patientId = patient.getId();

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Jan");
        doctor.setLastName("Kowalski");
        doctor.setTelephoneNumber("123456789");
        doctor.setEmail("doctor@email.com");
        doctor.setDoctorNumber("123456");
        doctor.setSpecialization(Specialization.DERMATOLOGIST);
        doctorDao.save(doctor);
        long doctorId = doctor.getId();

        //add 10 visits to patient
        for (int i = 1; i < 11; i++) {
            patientDao.addVisitToPatient(patientId, doctorId, LocalDateTime.of(2025, 1, i, 11, 12, i),
                    "test description");
        }

        // when
        List<PatientEntity> patients = patientDao.findPatientsWithVisitsAboveX(7);

        // then
        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(1);
    }

    @Test
    public void testShouldFindPatientsWithAllergicStatus() {
        // given
        // when
        List<PatientEntity> patients = patientDao.findPatiensWithAllergicStatus(true);

        // then
        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(3);
    }


    @Test
    public void testShouldFindPatientsWithVisitsBetweenDates() {
        // given
        patientDao.addVisitToPatient(1L, 1L, LocalDateTime.of(2027, 5, 4, 11, 12, 1), "test description");
        patientDao.addVisitToPatient(2L, 1L, LocalDateTime.of(2027, 5, 25, 11, 12, 1), "test description");
        patientDao.addVisitToPatient(2L, 1L, LocalDateTime.of(2027, 5, 22, 11, 12, 1), "test description");
        patientDao.addVisitToPatient(3L, 1L, LocalDateTime.of(2027, 5, 27, 11, 12, 1), "test description");
        // when
        List<PatientEntity> patients = patientDao.findPatientsWithVisitsBetweenDates(LocalDateTime.of(2027, 5, 3, 11, 12, 1),
                LocalDateTime.of(2027, 5, 26, 11, 12, 1));

        // then
        assertThat(patients).isNotNull();
        assertThat(patients.size()).isEqualTo(3);
    }





}



    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
//
    //@Column(nullable = false)
    //private String firstName;
//
    //@Column(nullable = false)
    //private String lastName;
//
    //@Column(nullable = false)
    //private String telephoneNumber;
//
    //private String email;
//
    //@Column(nullable = false)
    //private String patientNumber;
//
    //@Column(nullable = false)
    //private LocalDate dateOfBirth;
//
    //@Column(nullable = false, columnDefinition = "boolean default false")
    //private Boolean isAllergic;