package org.bebzunski.calculator.constants;

import java.math.BigDecimal;
import java.util.List;

public final class Constant {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

	//@formatter:off
	public static final List<List<Integer>> FIVE_OUT_OF_SIX = List.of(
			List.of(0, 1, 2, 3, 4),
			List.of(0, 1, 2, 3, 5),
			List.of(0, 1, 2, 4, 5),
			List.of(0, 1, 3, 4, 5),
			List.of(0, 2, 3, 4, 5),
			List.of(1, 2, 3, 4, 5));

	public static final List<List<Integer>> FIVE_OUT_OF_SEVEN = List.of(
			List.of(0, 1, 2, 3, 4),
			List.of(0, 1, 2, 3, 5),
			List.of(0, 1, 2, 3, 6),
			List.of(0, 1, 2, 4, 5),
			List.of(0, 1, 2, 4, 6),
			List.of(0, 1, 2, 5, 6),
			List.of(0, 1, 3, 4, 5),
			List.of(0, 1, 3, 4, 6),
			List.of(0, 1, 3, 5, 6),
			List.of(0, 2, 3, 4, 5),
			List.of(0, 2, 3, 4, 6),
			List.of(0, 2, 3, 5, 6),
			List.of(1, 2, 3, 4, 5),
			List.of(1, 2, 3, 4, 6),
			List.of(1, 2, 3, 5, 6),
			List.of(0, 1, 4, 5, 6),
			List.of(0, 2, 4, 5, 6),
			List.of(1, 2, 4, 5, 6),
			List.of(0, 3, 4, 5, 6),
			List.of(1, 3, 4, 5, 6),
			List.of(2, 3, 4, 5, 6));

	public static final List<List<Integer>> TWO_OUT_OF_FOUR = List.of(
			List.of(0, 1),
			List.of(0, 2),
			List.of(0, 3),
			List.of(1, 2),
			List.of(1, 3),
			List.of(2, 3));

	public static final List<List<Integer>> THREE_OUT_OF_FOUR = List.of(
			List.of(0, 1, 2),
			List.of(0, 1, 3),
			List.of(0, 2, 3),
			List.of(1, 2, 3));

	public static final List<List<Integer>> THREE_OUT_OF_FIVE = List.of(
			List.of(0, 1, 2),
			List.of(0, 1, 3),
			List.of(0, 1, 4),
			List.of(0, 2, 3),
			List.of(0, 2, 4),
			List.of(0, 3, 4),
			List.of(1, 2, 3),
			List.of(1, 2, 4),
			List.of(1, 3, 4),
			List.of(2, 3, 4));
	//@formatter:on
}
