package com.vgroup.assesment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Combinations")
@Table(name = "input_combinations")
public class Combination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7806098943934914317L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comb_id")
	@Id
	private Integer combId;
	
	@Column(name = "input_combination")
	private String strCombination;
	
	@Column(name = "input_id")
	private Integer inputId;
		
	public Integer getCombId() {
		return combId;
	}
	public void setCombId(Integer combId) {
		this.combId = combId;
	}
	public String getStrCombination() {
		return strCombination;
	}
	public void setStrCombination(String strCombination) {
		this.strCombination = strCombination;
	}
	public Integer getInputId() {
		return inputId;
	}
	public void setInputId(Integer inputId) {
		this.inputId = inputId;
	}
	@Override
	public String toString() {
		return "Combination [combId=" + combId + ", strCombination=" + strCombination + ", inputId=" + inputId + "]";
	}
}
