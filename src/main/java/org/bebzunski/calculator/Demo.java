package org.bebzunski.calculator;

import java.time.LocalTime;

import static org.bebzunski.calculator.enums.Face.ACE;
import static org.bebzunski.calculator.enums.Face.QUEEN;
import static org.bebzunski.calculator.pojo.Card.clubOf;
import static org.bebzunski.calculator.pojo.Card.diamondOf;
import static org.bebzunski.calculator.pojo.Card.heartOf;
import static org.bebzunski.calculator.pojo.Card.spadeOf;

public class Demo {

	public static void main(String[] args) {
		PokerCalculator calculator = PokerCalculator.holdem();
		calculator.addPlayerCards(diamondOf(QUEEN), heartOf(QUEEN));
		calculator.addPlayerCards(diamondOf(ACE), heartOf(ACE));
		calculator.addDeadCards(spadeOf(ACE), clubOf(ACE));

		LocalTime onStart = TimeLogger.onStart();
		WinningOddsOutput odds = calculator.checkWinningOdds();
		TimeLogger.logMilliseconds("checkWinningOdds", onStart);
		System.out.println(odds.wonByPlayer());

		//		LocalTime onStart = TimeLogger.onStart();
		//		HandOddsOutput odds = calculator.checkHandOdds();
		//		TimeLogger.logMilliseconds("checkHandOdds", onStart);
		//		System.out.println(odds.handOdds());

	}
}

