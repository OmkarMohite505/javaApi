package com.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "roleName")
public class Role extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private RoleEnum roleName;
	
	// Getter for roleName
	public RoleEnum getRoleName() {
	    return roleName;
	}

	// Setter for roleName
	public void setRoleName(RoleEnum roleName) {
	    this.roleName = roleName;
	}
}
