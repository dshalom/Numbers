package com.example.davidshalom.numbers; /**
 * Created by davidshalom on 13/12/2014.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@Config(emulateSdk = 18)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {

	@Test
	public void shouldFail() {
		assertTrue(true);
	}
}