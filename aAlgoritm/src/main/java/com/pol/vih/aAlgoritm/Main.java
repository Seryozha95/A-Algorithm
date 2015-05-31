package com.pol.vih.aAlgoritm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @seryozha
 *
 */
public class Main {

	private static Scanner input;
	private static Constants constants = new Constants();
	private static Data data;

	public static void main(String[] args) {
		try {
			data = Data.getInstance();
			Utilities util = new Utilities();
			input = new Scanner(System.in);
			data.programsWorkingTimes = new ArrayList();
			System.out.println("\t\t'A' Algorithm\n");
			System.out.print("\nEnter Processors' count...․   ");
			int procesorsCount = input.nextInt();
			for (int i = 0; i < procesorsCount; i++) {
				System.out.print("\tEnter " + (i + 1)
						+ " Processor's working time... ");
				data.processorsWorkingTimes.add(input.nextInt());
			}
			System.out.print("Enter Programs' count...․   ");
			int programsCount = input.nextInt();
			for (int i = 0; i < programsCount; i++) {
				System.out.print("\n\tEnter " + (i + 1)
						+ " Program's working time... ");
				data.programsWorkingTimes.add(input.nextInt());
			}
			data.createProcessorJson();
			Collections.sort(data.programsWorkingTimes);
			while (data.programsWorkingTimes.size() != 0) {
				util.setReaimderAndProcessprsList();
				System.out.println(data.processorsJson);
				util.defineProcessor();
				System.out.println(data.processorsJson);
				System.out.println("times " + data.programsWorkingTimes);
			}
			System.out.println("\n\t\t****RESULT****\n");
			for (int i = 0; i < data.aAlgoritmResult.size(); i++) {
				System.out.println(data.aAlgoritmResult.get(i));
			}
		} catch (InputMismatchException ex) {
		}
	}
}
