package com.jpacourse.mapper;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

public final class PatientMapper {
    public static PatientTO mapToTO(final PatientEntity patientEntity)
    {
        if (patientEntity == null) {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setIsAllergic(patientEntity.getIsAllergic());

        if (patientEntity.getAddress() != null) {
            patientTO.setAddress(AddressMapper.mapToTO(patientEntity.getAddress()));
        }

        if (patientEntity.getVisits() != null) {
            patientTO.setVisits(patientEntity.getVisits().stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList()));
        }

        return patientTO;
    }

    public static PatientEntity mapToEntity(final PatientTO patientTO)
    {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setIsAllergic(patientTO.getIsAllergic());

        return patientEntity;

    }
}