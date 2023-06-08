package com.fdmgroup.dottracer.model;

import java.util.concurrent.ThreadLocalRandom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@PrePersist
	private void init() {
		this.parcelNumber = String.format("%016d", ThreadLocalRandom.current().nextLong(1_000_000_000_000_000L));
		this.status = Status.SUBMITTED;
	}
}
