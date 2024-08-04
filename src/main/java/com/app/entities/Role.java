package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name = "roles")

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