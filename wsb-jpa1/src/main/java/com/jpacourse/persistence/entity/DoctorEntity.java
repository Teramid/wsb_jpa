package com.jpacourse.persistence.entity;

import com.jpacourse.persistence.enums.Specialization;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String telephoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String doctorNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    // ZWIĄZEK DWUKIERUNKOWY DOKTOR-WIZYTA
    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<VisitEntity> visits;

    // ZWIĄZEK JEDNOKIERUNKOWA DOKTOR-ADRES
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    // GETTER I SETTER
    public Collection<VisitEntity> getVisits() {
        return visits;
    }

    public void addVisit(VisitEntity visit) {
        visits.add(visit);

        if (visit.getDoctor() != this) {
            visit.setDoctor(this);
        }
    }

    public void removeVisit(VisitEntity visit) {
        visits.remove(visit);

        if (visit.getDoctor() == this) {
            visit.setDoctor(null);
        }
    }

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

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

}
