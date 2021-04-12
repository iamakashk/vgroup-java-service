package com.vgroup.assesment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vgroup.assesment.constants.CommonConstants;
import com.vgroup.assesment.model.Combination;
import com.vgroup.assesment.model.UserInput;
import com.vgroup.assesment.service.TestService;

@CrossOrigin()
@RestController
@RequestMapping("/vgroup/v1/admin")
public class AdminController {
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);

	class Local {
	};

	@Autowired
	public TestService service;

	/**
	 * @return All input entered by user till now
	 */
	@GetMapping
	@RequestMapping({ "/getAllInputs" })
	public ResponseEntity<List<UserInput>> getAllInputs() {
		logger.info(CommonConstants.METHOD_PROCESSING_START + "AdminController.getAllInputs()");
		long startTime = System.currentTimeMillis();
		HttpStatus httpStatus = HttpStatus.OK;
		HttpHeaders responseHeaders = new HttpHeaders();
		List<UserInput> userInputList = null;
		try {
			userInputList = service.getAllInputs();
		} catch (Exception e) {
			e.printStackTrace();
			// httpStatus = HttpStatus.
		}
		long endTime = System.currentTimeMillis();
		logger.info(CommonConstants.GENERATED_RESPONSE + " AdminController.getAllInputs(): " + userInputList.toString());
		logger.info(CommonConstants.METHOD_PROCESSING_END + " AdminController.getAllInputs() ");
		logger.info(CommonConstants.EXEC_TIME + " AdminController.getAllInputs() " + CommonConstants.SPACE_WITH_COLON
				+ (endTime - startTime));
		return new ResponseEntity<List<UserInput>>(userInputList, responseHeaders, httpStatus);
	}

	/**
	 * @param inputId
	 * @return returns permutations for single input ID.
	 */
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/getPermutation" })
	public ResponseEntity<List<Combination>> getPermutations(@RequestParam Integer inputId) {
		logger.info(CommonConstants.METHOD_PROCESSING_START + " AdminController.getPermutations() ");
		long startTime = System.currentTimeMillis();

		HttpHeaders responseHeaders = new HttpHeaders();
		List<Combination> list = null;
		try {
			list = service.getCombinationById(Integer.valueOf(inputId));
		} catch (Exception e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		logger.info(CommonConstants.GENERATED_RESPONSE + " AdminController.getPermutations(): " + list.toString());
		logger.info(CommonConstants.METHOD_PROCESSING_END + " AdminController.getPermutations() ");

		logger.info(CommonConstants.EXEC_TIME + " AdminController.getPermutations() " + CommonConstants.SPACE_WITH_COLON
				+ (endTime - startTime));

		return new ResponseEntity<List<Combination>>(list, responseHeaders, HttpStatus.OK);
	}
}