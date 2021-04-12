package com.vgroup.assesment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "userInputs")
@Table(name = "user_inputs")
public class UserInput implements Serializable {

	@Override
	public String toString() {
		return "UserInput [id=" + id + ", inputValue=" + inputValue + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1163774210306954368L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@Id
	private Integer id;
	
	@Column(name = "input_value")
	private String inputValue;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
}
