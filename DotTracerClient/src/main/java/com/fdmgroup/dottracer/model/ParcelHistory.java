package com.fdmgroup.dottracer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ParcelHistory {
	@ManyToOne
	@JoinColumn(name = "parcel_id", nullable = false)
	private Parcel parcel;

	@Id
	Long id;

	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
	}
}
