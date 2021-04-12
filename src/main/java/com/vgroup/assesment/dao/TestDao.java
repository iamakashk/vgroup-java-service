package com.vgroup.assesment.dao;

import java.util.HashMap;
import java.util.List;

import com.vgroup.assesment.model.Combination;
import com.vgroup.assesment.model.UserInput;
import com.vgroup.assesment.model.UserInputJSONModel;

public interface TestDao {

	public boolean saveCombinations(List<String> userInputList, HashMap<String, List<String>> map);
	public List<Combination> getCombinationById(Integer id);
	public List<UserInput> getAllInputs();
}
