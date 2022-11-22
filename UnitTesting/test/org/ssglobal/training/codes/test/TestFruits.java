package org.ssglobal.training.codes.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.ssglobal.training.codes.Fruits;

public class TestFruits {
	
	private Fruits fruits;
	
	@BeforeEach
	public void setup() {
		fruits = new Fruits();
	}
	
	@AfterEach
	public void teardown() {
		fruits = null;
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = {"Apple", "Mango", "Melon", "Lemon"})
	public void testAddFruits(String param) {
		boolean actualResult = fruits.addFruits(param);
		assertEquals(true, actualResult);
	}
	
	@RepeatedTest(10)
	public void testAddFruitsRainy() {
		boolean actualResult = fruits.addFruits("Apple", "Mango");
		assertNotEquals(true, actualResult);
	}
	
	@EnabledOnOs(OS.LINUX)
	@Order(1)
	@Test
	public void testGetStandNotNull() {
		assertNotNull(fruits.getStand());
	}
	
	
	@EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_19)
	@EnabledOnOs(OS.WINDOWS)
	@Order(2)
	@Test
	public void testGetStandContent() {
		List<String> expectedStand = Arrays.asList("Apple", "Mango");
		List<String> actualStand = fruits.getStand();
		assertIterableEquals(expectedStand, actualStand);
	}
	
	@RepeatedTest(10)
	@EnabledIf("toggleOnOff")
	@EnabledOnOs(OS.WINDOWS)
	@Order(3)
	public void testCountStand() {
		
		assertTimeout(Duration.ofMillis(50), () -> {
			int expectFruits = 10;
			int actualFruits = fruits.countFruits();
			assertTrue(actualFruits >= expectFruits);
		});
		
	}
	
	@EnabledIf("toggleOnOff")
	@EnabledOnOs(OS.WINDOWS)
	@Order(4)
	@Test
	public void testGetStandStream() {
		Stream<String> expectStrStream= Stream.of("Mango", "Apple");
		Stream<String> resultStrStream = fruits.getFruitSteam();
		assertLinesMatch(expectStrStream, resultStrStream);
	}
	
	// switch
	public static boolean toggleOnOff() {
		return true;
	}
	
	

}
