package com.jpacourse.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	/* ZWIĄZEK DWUKIERUNKOWY LECZENIE-WIZYTA
	* Rodzicem jest MedicalTreatmentEntity
	* Dzieckiem jest VisitEntity
	* Relacja dwukierunkowa
	*/
	@OneToOne(mappedBy = "visit")
	private MedicalTreatmentEntity medicalTreatment;

	//GETTERY I SETTERY RELACJI WIZYTA-LECZENIE
    public MedicalTreatmentEntity getMedicalTreatment() {
        return medicalTreatment;
    }

    public void setMedicalTreatment(MedicalTreatmentEntity medicalTreatment) {
        this.medicalTreatment = medicalTreatment;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
