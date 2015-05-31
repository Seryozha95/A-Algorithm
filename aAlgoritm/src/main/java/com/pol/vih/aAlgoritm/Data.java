package com.pol.vih.aAlgoritm;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Data {

	private static Data data = new Data();
	private Constants constants = new Constants();
	public List<Integer> programsWorkingTimes = new ArrayList<Integer>();
	public List<String> aAlgoritmResult = new ArrayList<String>();
	public List<Integer> processorsWorkingTimes = new ArrayList<Integer>();
	public JSONArray processorsJson = new JSONArray();

	private Data() {
	}

	public static Data getInstance() {
		return data;
	}

	public void createProcessorJson() {
		for (int i = 0; i < this.processorsWorkingTimes.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.accumulate(constants.time, this.processorsWorkingTimes.get(i));
			obj.accumulate(constants.remainder,
					this.processorsWorkingTimes.get(i));
			this.processorsJson.put(obj);
		}
	}
}
