package com.SpringMVCHibernateThymeleaf.users.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String username;
	private Boolean gender;
	private Boolean status;

	public UserEntity() {
		
	}
	
	public UserEntity(Integer id, String username, Boolean gender, Boolean status) {
//		super();
		this.id = id;
		this.username = username;
		this.gender = gender;
		this.status = status;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", gender=" + gender + ", status=" + status + "]";
	}
	
	

}
