package com.fdmgroup.dottracer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parcel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parcel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "sender_id")
	private String senderId;

	@Column(name = "packageNum")
	private String parcelNumber;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL)
	private List<ParcelHistory> history = new ArrayList<>();

	@PrePersist
	private void init() {
		this.parcelNumber = UUID.randomUUID().toString().replaceAll("-", "");
		this.status = Status.SUBMITTED;
	}
}
