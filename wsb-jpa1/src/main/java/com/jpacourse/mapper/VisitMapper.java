package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.stream.Collectors;

public class VisitMapper {
    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorName(visitEntity.getDoctor().getFirstName());
        visitTO.setDoctroSurname(visitEntity.getDoctor().getLastName());

        if (visitEntity.getMedicalTreatments() != null) {
            visitTO.setTreatmentTypes(visitEntity.getMedicalTreatments().stream()
                .map(treatment -> treatment.getType())
                .collect(Collectors.toList()));
        }

        return visitTO;
    }

}
