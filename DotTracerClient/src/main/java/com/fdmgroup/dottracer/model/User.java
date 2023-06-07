package com.fdmgroup.dottracer.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dottracer_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String userId;

	@NotBlank(message = "Email is required.")
	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	@NotBlank(message = "Password is required.")
	@Size(min = 6, max = 50, message = "Password must be between 6 to 50 characters long.")
	private String password;

	@PrePersist
	private void init() {
		this.userId = UUID.randomUUID().toString().replaceAll("-", "");
	}

}
