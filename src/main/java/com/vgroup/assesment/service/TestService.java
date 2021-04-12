package com.vgroup.assesment.service;

import java.util.List;

import com.vgroup.assesment.model.Combination;
import com.vgroup.assesment.model.UserInput;
import com.vgroup.assesment.model.UserInputJSONModel;

public interface TestService {

	public List<UserInput> getAllInputs();
	public boolean createPermutations(List<String> userInputList);
	public List<Combination> getCombinationById(Integer id);
}
