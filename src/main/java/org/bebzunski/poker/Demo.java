package org.bebzunski.poker;

import java.time.LocalTime;

import org.bebzunski.poker.output.WinningOddsOutput;
import org.bebzunski.poker.services.PokerCalculator;
import org.bebzunski.poker.services.TimeLogger;

import static org.bebzunski.poker.enums.Face.ACE;
import static org.bebzunski.poker.enums.Face.JACK;
import static org.bebzunski.poker.enums.Face.THREE;

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

