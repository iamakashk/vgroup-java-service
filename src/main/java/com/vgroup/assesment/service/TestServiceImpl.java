package com.vgroup.assesment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vgroup.assesment.ProjectDetails;
import com.vgroup.assesment.constants.CommonConstants;
import com.vgroup.assesment.controller.AdminController;
import com.vgroup.assesment.dao.TestDao;
import com.vgroup.assesment.helper.HibernateHelper;
import com.vgroup.assesment.helper.PermutationHelper;
import com.vgroup.assesment.model.Combination;
import com.vgroup.assesment.model.UserInput;
import com.vgroup.assesment.model.UserInputJSONModel;

@Service
public class TestServiceImpl implements TestService {
	private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
    @Autowired
    public ProjectDetails bean;
        
	@Autowired
	private TestDao testDao;
	@Override
	public List<UserInput> getAllInputs() {
		
		logger.info(CommonConstants.METHOD_PROCESSING_START + "TestServiceImpl.getAllInputs()");
		long startTime = System.currentTimeMillis();

		List results = null;
		try {
			
			results = testDao.getAllInputs();

		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		logger.info(CommonConstants.GENERATED_RESPONSE + " TestServiceImpl.getAllInputs(): " + results.toString());
		logger.info(CommonConstants.EXEC_TIME + " TestServiceImpl.getAllInputs() "
				+ CommonConstants.SPACE_WITH_COLON + (endTime - startTime));
		logger.info(CommonConstants.METHOD_PROCESSING_END + " TestServiceImpl.getAllInputs() ");
		return results;
	}

	@Override
	public boolean createPermutations(List<String> userInputList) {
		logger.info(CommonConstants.METHOD_PROCESSING_START + "TestServiceImpl.createPermutations()");
		long startTime = System.currentTimeMillis();
		boolean	hasPermutationCreated = false;
		PermutationHelper helper = null;
		HashMap<String, List<String>> map ; 
		try {
			map = new HashMap<String, List<String>>();
			
			helper = new PermutationHelper();
			
			for(int i = 0 ; i < userInputList.size(); i++) {
				List<String> list = helper.generatePermutation(userInputList.get(i));
				map.put(userInputList.get(i), list);
			}
			
			/*
			 * List<String> list = helper.generatePermutation(inputModel.getInputOne());
			 * map.put(inputModel.getInputOne(), list); List<String> list1 =
			 * helper.generatePermutation(inputModel.getInputTwo());
			 * map.put(inputModel.getInputTwo(), list1); List<String> list2 =
			 * helper.generatePermutation(inputModel.getInputThree());
			 * map.put(inputModel.getInputThree(), list2);
			 */
			
			/*
			 * System.out.println(list.toString()); System.out.println(list1.toString());
			 * System.out.println(list2.toString());
			 */
			hasPermutationCreated = testDao.saveCombinations(userInputList, map);
			
//			hasPermutationCreated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		logger.info(CommonConstants.GENERATED_RESPONSE + " TestServiceImpl.createPermutations(): " + " hasPermutationCreated: "+ hasPermutationCreated);
		logger.info(CommonConstants.EXEC_TIME + " TestServiceImpl.createPermutations() "
				+ CommonConstants.SPACE_WITH_COLON + (endTime - startTime));
		logger.info(CommonConstants.METHOD_PROCESSING_END + " TestServiceImpl.createPermutations() ");
		
		return hasPermutationCreated;
	}

	@Override
	public List<Combination> getCombinationById(Integer id) {
		logger.info(CommonConstants.METHOD_PROCESSING_START + "TestServiceImpl.getCombinationById()");
		long startTime = System.currentTimeMillis();
		List<Combination> serviceCombList = null;
		try {
			serviceCombList = new ArrayList<Combination>();
			serviceCombList = testDao.getCombinationById(id);
		} catch (Exception e) {

		}
		
		long endTime = System.currentTimeMillis();
		logger.info(CommonConstants.GENERATED_RESPONSE + " TestServiceImpl.getCombinationById(): " + serviceCombList.toString());
		logger.info(CommonConstants.EXEC_TIME + " TestServiceImpl.getCombinationById() "
				+ CommonConstants.SPACE_WITH_COLON + (endTime - startTime));
		logger.info(CommonConstants.METHOD_PROCESSING_END + " TestServiceImpl.getCombinationById() ");
		return serviceCombList;
	}

	
	
}
