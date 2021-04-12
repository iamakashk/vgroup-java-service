package com.vgroup.assesment.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.vgroup.assesment.constants.CommonConstants;
import com.vgroup.assesment.model.User;
import com.vgroup.assesment.model.UserInputJSONModel;
import com.vgroup.assesment.service.TestService;

@CrossOrigin()
@RestController
@RequestMapping("/vgroup/v1/user")
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	public TestService service;

	/**
	 * @return User object with hardcoded string. This tells caller that it has been authenticated.
	 */
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public User validateLogin() {
		logger.info(CommonConstants.METHOD_PROCESSING_START + " TestController.validateLogin() ");
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		logger.info(CommonConstants.METHOD_PROCESSING_END + " TestController.validateLogin() ");
		logger.info(CommonConstants.EXEC_TIME + " TestController.validateLogin() " + CommonConstants.SPACE_WITH_COLON
				+ (endTime - startTime));

		return new User("User successfully authenticated");
	}

	/**
	 * @param json Gets 3 input strings from UI or source
	 * @return true if permutations are created successfully. or false if error occurred. 
	 */
	@PostMapping
	@RequestMapping({ "/createPermutation" })
	public ResponseEntity<Boolean> createPermutations(@RequestBody String json) {
		logger.info(CommonConstants.METHOD_PROCESSING_START + " TestController.createPermutations() ");
		long startTime = System.currentTimeMillis();

		HttpHeaders responseHeaders = new HttpHeaders();
		UserInputJSONModel jsonModel = null;
		Gson gson = null;
		boolean isCreated = false;
		List<String> userInputList = null;
		try {
			gson = new Gson();
			jsonModel = gson.fromJson(json, UserInputJSONModel.class);
			userInputList = new ArrayList<String>();
			userInputList.add(jsonModel.getInputOne());
			userInputList.add(jsonModel.getInputTwo());
			userInputList.add(jsonModel.getInputThree());

			isCreated = service.createPermutations(userInputList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		logger.info(CommonConstants.GENERATED_RESPONSE + " TestController.createPermutations() :" + "isCreated: "+ isCreated);
		logger.info(CommonConstants.METHOD_PROCESSING_END + " TestController.createPermutations() ");

		logger.info(CommonConstants.EXEC_TIME + " TestController.createPermutations() "
				+ CommonConstants.SPACE_WITH_COLON + (endTime - startTime));

		return new ResponseEntity<>(isCreated, responseHeaders, HttpStatus.OK);
	}
}