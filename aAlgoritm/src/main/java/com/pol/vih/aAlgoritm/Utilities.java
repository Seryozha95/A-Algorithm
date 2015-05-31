package com.pol.vih.aAlgoritm;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utilities {

	private Constants constants = new Constants();
	private Data data = Data.getInstance();
	private JSONArray defineProgList = null;

	/**
	 * Function summarizes processor's reaminder value and which programs can
	 * work on same processor
	 */
	public void setReaimderAndProcessprsList() {
		for (int i = 0; i < this.data.processorsJson.length(); i++) {
			int summa = 0;
			List progList = new ArrayList();
			JSONObject procInfo = (JSONObject) this.data.processorsJson.get(i);
			for (int j = this.data.programsWorkingTimes.size() - 1; j >= 0; j--) {
				summa = summa + this.data.programsWorkingTimes.get(j);
				if (summa <= (Integer) procInfo.get(constants.time)) {
					procInfo.put(constants.remainder,
							((Integer) procInfo.get(constants.time) - summa));
					progList.add(this.data.programsWorkingTimes.get(j));
				}
				procInfo.put(constants.programs, progList);
			}
		}
	}

	/**
	 * Function defines which processor has min reaminder value and add it in to
	 * result list
	 */
	public void defineProcessor() {
		int minReaminder = 878;
		int workingTime = 0;
		this.defineProgList = null;
		int length = this.data.processorsJson.length();
		for (int i = 0; i < length; i++) {
			JSONObject defProc = (JSONObject) this.data.processorsJson.get(0);
			JSONArray a = (JSONArray) defProc.get(constants.programs);
			if ((minReaminder >= (Integer) defProc.get(constants.remainder))
					&& (0 != a.length())) {
				defineProgList = (JSONArray) defProc.get(constants.programs);
				minReaminder = (Integer) defProc.get(constants.remainder);
				workingTime = (Integer) defProc.get(constants.time);
			}
			this.data.processorsJson.remove(0);
		}
		this.updateData(defineProgList);
		this.setInsteadOfReaminder(minReaminder, workingTime);
	}

	private void updateData(JSONArray remProg) {
		this.data.createProcessorJson();
		for (int i = remProg.length() - 1; i >= 0; i--) {
			this.data.programsWorkingTimes
					.remove(this.data.programsWorkingTimes.size() - (i + 1));
		}
	}

	private void setInsteadOfReaminder(int minReaminger, int workingTime) {
		for (int i = this.data.programsWorkingTimes.size() - 1; i >=0; i--) {
			if (this.data.programsWorkingTimes.get(i) <= minReaminger) {
				this.defineProgList.put(this.data.programsWorkingTimes.get(i));
				minReaminger = minReaminger
						- this.data.programsWorkingTimes.get(i);
				this.data.programsWorkingTimes.remove(i);

			}
		}
		String result = "PROCESSOR : " + Integer.toString(workingTime)
				+ " PROGRAMS :  " + defineProgList + "\n";
		this.data.aAlgoritmResult.add(result);
	}
}
