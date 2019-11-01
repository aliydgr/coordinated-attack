package me.ali.ds.hw4;

import org.junit.Test;

import me.ali.ds.hw4.CoordinatedAttack.Status;

import static org.junit.Assert.*;

import java.util.Random;

public class AppTest {

	private static Random random = new Random();

	/**
	 * if all opposite, don't attack
	 */
	@Test
	public void testOppositeValidity() {
		for (int j = 0; j < 100; j++) {
    		CoordinatedAttack ca = new CoordinatedAttack(random.nextInt(100) + 2, random.nextInt(100) + 5, 0);
			ca.run(random.nextDouble());
			assertEquals(Status.right_opposite, ca.getStatus());
		}
	}
		
	/**
	 * if all agree and all message received, attack
	 */
	@Test
	public void testAgreeValidity() {
		for (int j = 0; j < 100; j++) {
    		CoordinatedAttack ca = new CoordinatedAttack(random.nextInt(100) + 2, random.nextInt(100) + 5, 1);
			ca.run(0);
			assertEquals(Status.right_agree, ca.getStatus());
		}
	}
}
