package com.fdmgroup.dottracer.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Parcel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private Long senderId;

	@GeneratedValue
	private Long packageNumber;

	@NotBlank
	private Status status = Status.SUBMITTED;
	private List<ParcelHistory> history = new ArrayList<>();

	public void addParcelHistoryEntry(ParcelHistory parcelHistory) {
		this.history.add(parcelHistory);
	}
}
