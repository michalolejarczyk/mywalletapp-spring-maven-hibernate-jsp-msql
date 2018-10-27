package com.mywallet.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "app_user_expenses")
public class UserExpense implements Serializable {

	public UserExpense() {
	}

	public UserExpense(User user, String purpose, BigDecimal expense, Date expenseDate) {
		this.user = user;
		this.purpose = purpose;
		this.expense = expense;
		this.expenseDate = expenseDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Size(max = 255, message = "Size cannot be greater than 255 Characters")
	@NotBlank(message = "Purpose can not be blank.")
	@Column(name = "purpose", nullable = false)
	private String purpose;

	@NotNull(message = "Expense can not be null.")
	@Column(name = "expense", precision = 10, scale = 2, nullable = false)
	private BigDecimal expense;

	@NotNull(message = "Date can not be null.")
	@Column(name = "exp_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date expenseDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public BigDecimal getExpense() {
		return expense;
	}

	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

}
