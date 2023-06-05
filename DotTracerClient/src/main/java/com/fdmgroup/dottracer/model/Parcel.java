package com.fdmgroup.dottracer.model;

import java.util.List;

public class Parcel {
	private Long id;
	private Long senderId;
	private Long packageNumber;
	private Status status;
	private List<ParcelHistory> history;
}
