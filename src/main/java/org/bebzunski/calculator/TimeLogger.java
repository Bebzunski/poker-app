package org.bebzunski.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;

public class TimeLogger {

	public static void logSeconds(String text, LocalTime before) {
		int seconds = getSeconds(before);
		System.out.println(text + seconds + " s");
	}

	public static void logMilliseconds(String methodName, LocalTime before) {
		BigDecimal milli = getMilliSeconds(before);
		System.out.println(methodName + ": " + milli + " ms");
	}

	public static LocalTime onStart() {
		return LocalTime.now();
	}

	private static int getSeconds(LocalTime before) {
		LocalTime after = LocalTime.now();
		return (after.getMinute() * 60 + after.getSecond()) - (before.getMinute() * 60 + before.getSecond());
	}

	private static BigDecimal getMilliSeconds(LocalTime before) {
		LocalTime after = LocalTime.now();
		int nano = 1000000000 * (after.getSecond() - before.getSecond()) + after.getNano() - before.getNano();
		return new BigDecimal(nano).divide(new BigDecimal(1000000), 0, RoundingMode.HALF_UP);
	}
}
