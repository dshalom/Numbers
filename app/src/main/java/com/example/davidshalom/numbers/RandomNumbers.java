package com.example.davidshalom.numbers;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by davidshalom on 13/12/2014.
 */
public class RandomNumbers {

	public static ArrayList<Integer> getRandomNumbers(int size) {

		Random randomGenerator = new Random();

		ArrayList<Integer> randomNumbers = new ArrayList<Integer>(size);

		for (int i = 0; i < size; ++i) {
			randomNumbers.add(randomGenerator.nextInt(1000000));
		}

		return randomNumbers;
	}
}
