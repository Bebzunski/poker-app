package org.bebzunski.calculator;

import java.time.LocalTime;

import org.bebzunski.calculator.output.WinningOddsOutput;
import org.bebzunski.calculator.services.PokerCalculator;
import org.bebzunski.calculator.services.TimeLogger;

import static org.bebzunski.calculator.enums.Face.ACE;
import static org.bebzunski.calculator.enums.Face.FIVE;
import static org.bebzunski.calculator.enums.Face.JACK;
import static org.bebzunski.calculator.enums.Face.KING;
import static org.bebzunski.calculator.enums.Face.SIX;
import static org.bebzunski.calculator.enums.Face.TEN;
import static org.bebzunski.calculator.enums.Face.THREE;
import static org.bebzunski.calculator.enums.Face.TWO;

public class Demo {

	public static void main(String[] args) {
		PokerCalculator calculator = PokerCalculator.holdem();
		calculator.addPlayerCards(ACE.d(), JACK.d());
		calculator.addPlayerCards(THREE.h(), THREE.c());

		LocalTime onStart = TimeLogger.onStart();
		WinningOddsOutput odds = calculator.checkWinningOdds();
		TimeLogger.logSeconds("checkWinningOdds", onStart);
		System.out.println(odds.wonByPlayer());

		//		LocalTime onStart = TimeLogger.onStart();
		//		HandOddsOutput odds = calculator.checkHandOdds();
		//		TimeLogger.logMilliseconds("checkHandOdds", onStart);
		//		System.out.println(odds.handOdds());

	}
}

