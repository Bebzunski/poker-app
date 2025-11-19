package org.bebzunski.poker.it;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.bebzunski.poker.output.WinningOddsOutput;
import org.bebzunski.poker.pojo.PlayerCards;
import org.bebzunski.poker.services.PokerCalculator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bebzunski.poker.enums.Face.ACE;
import static org.bebzunski.poker.enums.Face.EIGHT;
import static org.bebzunski.poker.enums.Face.FIVE;
import static org.bebzunski.poker.enums.Face.JACK;
import static org.bebzunski.poker.enums.Face.KING;
import static org.bebzunski.poker.enums.Face.NINE;
import static org.bebzunski.poker.enums.Face.QUEEN;
import static org.bebzunski.poker.enums.Face.SEVEN;
import static org.bebzunski.poker.enums.Face.SIX;
import static org.bebzunski.poker.enums.Face.TEN;
import static org.bebzunski.poker.enums.Face.TWO;
import static org.bebzunski.poker.pojo.Card.clubOf;
import static org.bebzunski.poker.pojo.Card.diamondOf;
import static org.bebzunski.poker.pojo.Card.heartOf;
import static org.bebzunski.poker.pojo.Card.spadeOf;

class OmahaPokerCalculatorITCase {

	@Test
	void checkWinningOdds_test1() {
		// given
		PokerCalculator calculator = PokerCalculator.omaha();
		calculator.addPlayerCards(spadeOf(ACE), diamondOf(ACE), spadeOf(KING), diamondOf(KING));
		calculator.addPlayerCards(clubOf(JACK), heartOf(JACK), diamondOf(SEVEN), spadeOf(TWO));
		calculator.calculateToRiver();

		// when
		WinningOddsOutput odds = calculator.checkWinningOdds();

		// then
		assertThat(odds).isNotNull();
		Map<PlayerCards, BigDecimal> wonByPlayer = odds.wonByPlayer();
		assertThat(wonByPlayer).hasSize(2);

		PlayerCards player1 = new PlayerCards(Set.of(spadeOf(ACE), diamondOf(ACE), spadeOf(KING), diamondOf(KING)));
		BigDecimal odds1 = wonByPlayer.get(player1);
		AssertionHelper.assertOdds(odds1, "79.78");

		PlayerCards player2 = new PlayerCards(Set.of(clubOf(JACK), heartOf(JACK), diamondOf(SEVEN), spadeOf(TWO)));
		BigDecimal odds2 = wonByPlayer.get(player2);
		AssertionHelper.assertOdds(odds2, "20.22");
	}

	@Test
	void checkWinningOdds_test2() {
		// given
		PokerCalculator calculator = PokerCalculator.omaha();
		calculator.addPlayerCards(diamondOf(JACK), diamondOf(TEN), spadeOf(FIVE), spadeOf(SIX));
		calculator.addPlayerCards(clubOf(ACE), heartOf(ACE), clubOf(TWO), heartOf(TWO));
		calculator.flop(diamondOf(EIGHT), diamondOf(NINE), clubOf(SIX));
		calculator.calculateToRiver();

		// when
		WinningOddsOutput odds = calculator.checkWinningOdds();

		// then
		assertThat(odds).isNotNull();
		Map<PlayerCards, BigDecimal> wonByPlayer = odds.wonByPlayer();
		assertThat(wonByPlayer).hasSize(2);

		PlayerCards player1 = new PlayerCards(Set.of(diamondOf(JACK), diamondOf(TEN), spadeOf(FIVE), spadeOf(SIX)));
		BigDecimal odds1 = wonByPlayer.get(player1);
		AssertionHelper.assertOdds(odds1, "69.39");

		PlayerCards player2 = new PlayerCards(Set.of(clubOf(ACE), heartOf(ACE), clubOf(TWO), heartOf(TWO)));
		BigDecimal odds2 = wonByPlayer.get(player2);
		AssertionHelper.assertOdds(odds2, "30.61");
	}

	@Test
	void checkWinningOdds_test3() {
		// given
		PokerCalculator calculator = PokerCalculator.omaha();
		calculator.addPlayerCards(diamondOf(JACK), diamondOf(TEN), spadeOf(FIVE), spadeOf(SIX));
		calculator.addPlayerCards(clubOf(ACE), heartOf(ACE), clubOf(TWO), heartOf(TWO));
		calculator.flop(diamondOf(EIGHT), diamondOf(NINE), clubOf(SIX));
		calculator.turn(spadeOf(NINE));

		// when
		WinningOddsOutput odds = calculator.checkWinningOdds();

		// then
		assertThat(odds).isNotNull();
		Map<PlayerCards, BigDecimal> wonByPlayer = odds.wonByPlayer();
		assertThat(wonByPlayer).hasSize(2);

		PlayerCards player1 = new PlayerCards(Set.of(diamondOf(JACK), diamondOf(TEN), spadeOf(FIVE), spadeOf(SIX)));
		BigDecimal odds1 = wonByPlayer.get(player1);
		AssertionHelper.assertOdds(odds1, "35.00");

		PlayerCards player2 = new PlayerCards(Set.of(clubOf(ACE), heartOf(ACE), clubOf(TWO), heartOf(TWO)));
		BigDecimal odds2 = wonByPlayer.get(player2);
		AssertionHelper.assertOdds(odds2, "65.00");
	}

	@Test
	void checkWinningOdds_test4() {
		// given
		PokerCalculator calculator = PokerCalculator.omaha();
		calculator.addPlayerCards(diamondOf(JACK), heartOf(TEN), spadeOf(FIVE), spadeOf(SIX));
		calculator.addPlayerCards(clubOf(ACE), heartOf(ACE), clubOf(TWO), heartOf(TWO));
		calculator.flop(diamondOf(EIGHT), diamondOf(NINE), clubOf(SIX));
		calculator.turn(spadeOf(ACE));
		calculator.addDeadCards(diamondOf(QUEEN), spadeOf(QUEEN), diamondOf(SEVEN), spadeOf(SEVEN));

		// when
		WinningOddsOutput odds = calculator.checkWinningOdds();

		// then
		assertThat(odds).isNotNull();
		Map<PlayerCards, BigDecimal> wonByPlayer = odds.wonByPlayer();
		assertThat(wonByPlayer).hasSize(2);

		PlayerCards player1 = new PlayerCards(Set.of(diamondOf(JACK), heartOf(TEN), spadeOf(FIVE), spadeOf(SIX)));
		BigDecimal odds1 = wonByPlayer.get(player1);
		AssertionHelper.assertOdds(odds1, "11.11");

		PlayerCards player2 = new PlayerCards(Set.of(clubOf(ACE), heartOf(ACE), clubOf(TWO), heartOf(TWO)));
		BigDecimal odds2 = wonByPlayer.get(player2);
		AssertionHelper.assertOdds(odds2, "88.89");
	}

	@Test
	void checkWinningOdds_test5() {
		// given
		PokerCalculator calculator = PokerCalculator.omaha();
		calculator.addPlayerCards(diamondOf(ACE), diamondOf(KING), heartOf(QUEEN), heartOf(JACK));
		calculator.addPlayerCards(spadeOf(ACE), spadeOf(KING), clubOf(QUEEN), clubOf(JACK));
		calculator.calculateToFlop();

		// when
		WinningOddsOutput odds = calculator.checkWinningOdds();

		// then
		assertThat(odds).isNotNull();
		Map<PlayerCards, BigDecimal> wonByPlayer = odds.wonByPlayer();
		assertThat(wonByPlayer).hasSize(2);

		PlayerCards player1 = new PlayerCards(Set.of(diamondOf(ACE), diamondOf(KING), heartOf(QUEEN), heartOf(JACK)));
		BigDecimal odds1 = wonByPlayer.get(player1);
		AssertionHelper.assertOdds(odds1, "50.00", "5.00");

		PlayerCards player2 = new PlayerCards(Set.of(spadeOf(ACE), spadeOf(KING), clubOf(QUEEN), clubOf(JACK)));
		BigDecimal odds2 = wonByPlayer.get(player2);
		AssertionHelper.assertOdds(odds2, "50.00", "5.00");
	}
}