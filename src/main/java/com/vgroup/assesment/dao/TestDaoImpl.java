package com.vgroup.assesment.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.vgroup.assesment.ProjectDetails;
import com.vgroup.assesment.constants.CommonConstants;
import com.vgroup.assesment.helper.HibernateHelper;
import com.vgroup.assesment.model.Combination;
import com.vgroup.assesment.model.UserInput;

@Repository
public class TestDaoImpl implements TestDao {
	private static Logger logger = LoggerFactory.getLogger(TestDaoImpl.class);

	@Autowired
	public ProjectDetails bean;

	/**
	 * saves combination for a given string and return true if saved successfully.
	 * false if error occurred .
	 */
	@Override
	public boolean saveCombinations(List<String> userInputList, HashMap<String, List<String>> map) {
		logger.info(CommonConstants.METHOD_PROCESSING_START + "TestDaoImpl.saveCombinations()");
		long startTime = System.currentTimeMillis();

		Session session = null;
		Criteria cr = null;
		List results = null;
		UserInput user = null;
		Combination combination = null;
		Transaction tx = null;
		boolean hasCreated = false;

		try {

			session = HibernateHelper.getSessionFactory(bean.getDbUrl()).openSession();
			tx = session.beginTransaction();
			for (int index = 0; index < userInputList.size(); index++) {
				user = new UserInput();
				user.setInputValue(userInputList.get(index));
				session.save(user);

				System.out.println("saves user id: " + user.getId());

				List<String> combinationList = map.get(user.getInputValue());

				for (int i = 0; i < combinationList.size(); i++) {
					combination = new Combination();
					combination.setInputId(user.getId());
					combination.setStrCombination(combinationList.get(i));
					session.save(combination);
					// System.out.println(combination.toString());
				}
			}

			tx.commit();
			hasCreated = true;
		} catch (Exception e) {
			hasCreated = false;
			tx.rollback();
			e.printStackTrace();

		}
		long endTime = System.currentTimeMillis();
		logger.info(CommonConstants.GENERATED_RESPONSE + " TestDaoImpl.saveCombinations(): " + " hasCreated: "
				+ hasCreated);
		logger.info(CommonConstants.EXEC_TIME + " TestDaoImpl.saveCombinations() " + CommonConstants.SPACE_WITH_COLON
				+ (endTime - startTime));
		logger.info(CommonConstants.METHOD_PROCESSING_END + " TestDaoImpl.saveCombinations() ");
		return hasCreated;

	}

	/**
	 * Returns all the combinations of selected string on UI based on ID of that
	 * string.
	 */
	@Override
	public List<Combination> getCombinationById(Integer id) {

		logger.info(CommonConstants.METHOD_PROCESSING_START + "TestDaoImpl.getCombinationById()");
		long startTime = System.currentTimeMillis();

		Session session = null;
		Criteria cr = null;
		List results = null;
		UserInput user = null;
		Combination combination = null;
		Transaction tx = null;

		try {
			session = HibernateHelper.getSessionFactory(bean.getDbUrl()).openSession();
			cr = session.createCriteria(Combination.class);
			cr.add(Restrictions.eq("inputId", id));
			results = cr.list();
		} catch (Exception e) {

			e.printStackTrace();
			// tx.rollback();
		}

		long endTime = System.currentTimeMillis();
		logger.info(CommonConstants.GENERATED_RESPONSE + " TestDaoImpl.getCombinationById(): " + results.toString());
		logger.info(CommonConstants.EXEC_TIME + " TestDaoImpl.getCombinationById() " + CommonConstants.SPACE_WITH_COLON
				+ (endTime - startTime));
		logger.info(CommonConstants.METHOD_PROCESSING_END + " TestDaoImpl.getCombinationById() ");
		return results;
	}

	@Override
	public List<UserInput> getAllInputs() {

		logger.info(CommonConstants.METHOD_PROCESSING_START + "TestDaoImpl.getAllInputs()");
		long startTime = System.currentTimeMillis();

		Session session = null;
		Criteria cr = null;
		List results = null;
		try {
			session = HibernateHelper.getSessionFactory(bean.getDbUrl()).openSession();
			results = session.createCriteria(UserInput.class).list();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		logger.info(CommonConstants.GENERATED_RESPONSE + " TestDaoImpl.getAllInputs(): " + results.toString());
		logger.info(CommonConstants.EXEC_TIME + " TestDaoImpl.getAllInputs() " + CommonConstants.SPACE_WITH_COLON
				+ (endTime - startTime));
		logger.info(CommonConstants.METHOD_PROCESSING_END + " TestDaoImpl.getAllInputs() ");
		return results;
	}

}
