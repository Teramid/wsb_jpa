package com.jpacourse.persistence.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

// import antlr.collections.List;

@Entity
@Table(name = "patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String telephoneNumber;

    private String email;

    @Column(nullable = false)
    private String patientNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isAllergic;

    // ZWIĄZEK DWUKIERUNKOWY PACJENT-WIZYTY
    @OneToMany(mappedBy = "patient", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Collection<VisitEntity> visits = new ArrayList<>();

    // GETTER I SETTER
    public Collection<VisitEntity> getVisits() {
        return visits;
    }

    public void addVisit(VisitEntity visit) {
        visits.add(visit);

        if (visit.getPatient() != this) {
            visit.setPatient(this);
        }
    }

    public void removeVisit(VisitEntity visit) {
        visits.remove(visit);

        if (visit.getPatient() == this) {
            visit.setPatient(null);
        }
    }

    // ZWIĄZEK JEDNOKIERUNKOWY PACJENT-ADRES
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    // GETTERY I SETTERY DO RELACJI PACJENT-ADRES
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getIsAllergic() {
        return isAllergic;
    }

    public void setIsAllergic(Boolean isAllergic) {
        this.isAllergic = isAllergic;
    }


}
