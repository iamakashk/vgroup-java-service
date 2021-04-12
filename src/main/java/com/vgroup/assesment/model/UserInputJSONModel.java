package com.vgroup.assesment.model;

public class UserInputJSONModel {

	public String getInputOne() {
		return inputOne;
	}
	public void setInputOne(String inputOne) {
		this.inputOne = inputOne;
	}
	public String getInputTwo() {
		return inputTwo;
	}
	public void setInputTwo(String inputTwo) {
		this.inputTwo = inputTwo;
	}
	public String getInputThree() {
		return inputThree;
	}
	public void setInputThree(String inputThree) {
		this.inputThree = inputThree;
	}
	private String inputOne;
	private String inputTwo;
	@Override
	public String toString() {
		return "UserInputJSONModel [inputOne=" + inputOne + ", inputTwo=" + inputTwo + ", inputThree=" + inputThree
				+ "]";
	}
	private String inputThree;
}
