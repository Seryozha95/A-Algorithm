package com.pol.vih.aAlgoritm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	private Utilities util = new Utilities();
	private static Data data = Data.getInstance();
	private static List<Integer> programs = Arrays.asList(26, 50, 12, 6, 10);
	private static List<Integer> processors = Arrays.asList(57, 75, 34);
	private static String setReaimderAndProcessprsListResult = "[{\"time\":57,\"programs\":[50],\"remainder\":7},"
			+ "{\"time\":75,\"programs\":[50],\"remainder\":25},{\"time\":34,\"programs\":[],\"remainder\":34}]";

	private static String result = "PROCESSOR : 57 PROGRAMS :  [50,6] ";
	private static String updatedTime = "[10, 12, 26]";

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		for (int i = 0; i < programs.size(); i++) {
			data.programsWorkingTimes.add(programs.get(i));
		}
		for (int i = 0; i < processors.size(); i++) {
			data.processorsWorkingTimes.add(processors.get(i));
		}
		Collections.sort(data.programsWorkingTimes);
		data.createProcessorJson();
		return new TestSuite(AppTest.class);

	}

	public void testSetReaimderAndProcessprs() {
		util.setReaimderAndProcessprsList();
		assertEquals(data.processorsJson.toString(),
				setReaimderAndProcessprsListResult);
	}

	public void testdefine() {
		util.defineProcessor();
		assertEquals(data.programsWorkingTimes.toString(), updatedTime);
	}

}
