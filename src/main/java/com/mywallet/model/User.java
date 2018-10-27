package com.mywallet.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "app_user")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 30, message = "Size cannot be greater than 30 Characters")
	@NotBlank(message = "Username can not be blank.")
	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@NotNull
	@Size(max = 100, message = "Size cannot be greater than 100 Characters")
	@NotBlank(message = "Password can not be blank.")
	@Column(name = "password", nullable = false)
	private String password;

	@NotNull
	@Size(max = 30, message = "Size cannot be greater than 30 Characters")
	@NotBlank(message = "First name can not be blank.")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotNull
	@Size(max = 30, message = "Size cannot be greater than 30 Characters")
	@NotBlank(message = "Last name can not be blank.")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@NotNull
	@Email(message = "Invalid email format.")
	@Size(max = 100, message = "Size cannot be greater than 100 Characters")
	@NotBlank(message = "Email can not be blank.")
	@Column(name = "email", nullable = false)
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "app_user_user_profile", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_profile_id") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserExpense> userExpenses;

	public List<UserExpense> getUserExpenses() {
		return userExpenses;
	}

	public void setUserExpenses(List<UserExpense> userExpenses) {
		this.userExpenses = userExpenses;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
