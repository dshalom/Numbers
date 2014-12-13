package com.example.davidshalom.numbers;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;


@Config(emulateSdk = 18)
@RunWith(RobolectricGradleTestRunner.class)

public class RandomNumbersTest extends TestCase {

	private RandomNumbers sut;
	private final int size = 80;

	@Test
	public void testGetRandomNumbers() throws Exception {

		ArrayList<Integer> numbers = sut.getRandomNumbers(size);
		assert (numbers.size() == size);

	}
}