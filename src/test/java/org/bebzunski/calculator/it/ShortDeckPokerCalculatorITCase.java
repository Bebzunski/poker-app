package org.bebzunski.calculator.it;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.bebzunski.calculator.output.WinningOddsOutput;
import org.bebzunski.calculator.pojo.PlayerCards;
import org.bebzunski.calculator.services.PokerCalculator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bebzunski.calculator.enums.Face.ACE;
import static org.bebzunski.calculator.enums.Face.JACK;
import static org.bebzunski.calculator.enums.Face.KING;
import static org.bebzunski.calculator.enums.Face.SEVEN;
import static org.bebzunski.calculator.enums.Face.SIX;
import static org.bebzunski.calculator.enums.Face.TEN;

class ShortDeckPokerCalculatorITCase {

	@Test
	void checkWinningOdds_test1() {
		// given
		PokerCalculator calculator = PokerCalculator.shortDeckHoldem();
		calculator.addPlayerCards(ACE.s(), KING.s());
		calculator.addPlayerCards(SEVEN.d(), SEVEN.h());
		calculator.flop(SEVEN.s(), TEN.s(), JACK.s());

		// when
		WinningOddsOutput odds = calculator.checkWinningOdds();

		// then
		assertThat(odds).isNotNull();
		Map<PlayerCards, BigDecimal> wonByPlayer = odds.wonByPlayer();
		assertThat(wonByPlayer).hasSize(2);

		PlayerCards player1 = new PlayerCards(Set.of(ACE.s(), KING.s()));
		BigDecimal odds1 = wonByPlayer.get(player1);
		AssertionHelper.assertOdds(odds1, "93.00");

		PlayerCards player2 = new PlayerCards(Set.of(SEVEN.d(), SEVEN.h()));
		BigDecimal odds2 = wonByPlayer.get(player2);
		AssertionHelper.assertOdds(odds2, "7.00");
	}

	@Test
	void checkWinningOdds_test2() {
		// given
		PokerCalculator calculator = PokerCalculator.shortDeckHoldem();
		calculator.addPlayerCards(ACE.s(), KING.s());
		calculator.addPlayerCards(SIX.d(), SIX.h());
		calculator.flop(SIX.s(), TEN.s(), JACK.h());

		// when
		WinningOddsOutput odds = calculator.checkWinningOdds();

		// then
		assertThat(odds).isNotNull();
		Map<PlayerCards, BigDecimal> wonByPlayer = odds.wonByPlayer();
		assertThat(wonByPlayer).hasSize(2);

		PlayerCards player1 = new PlayerCards(Set.of(ACE.s(), KING.s()));
		BigDecimal odds1 = wonByPlayer.get(player1);
		AssertionHelper.assertOdds(odds1, "42.00");

		PlayerCards player2 = new PlayerCards(Set.of(SIX.d(), SIX.h()));
		BigDecimal odds2 = wonByPlayer.get(player2);
		AssertionHelper.assertOdds(odds2, "58.00");
	}

	@Test
	void checkWinningOdds_test3() {
		// given
		PokerCalculator calculator = PokerCalculator.shortDeckHoldem();
		calculator.addPlayerCards(ACE.s(), KING.s());
		calculator.addPlayerCards(ACE.d(), KING.d());
		calculator.flop(SIX.h(), TEN.h(), JACK.h());

		// when
		WinningOddsOutput odds = calculator.checkWinningOdds();

		// then
		assertThat(odds).isNotNull();
		Map<PlayerCards, BigDecimal> wonByPlayer = odds.wonByPlayer();
		assertThat(wonByPlayer).hasSize(2);

		PlayerCards player1 = new PlayerCards(Set.of(ACE.s(), KING.s()));
		BigDecimal odds1 = wonByPlayer.get(player1);
		AssertionHelper.assertOdds(odds1, "50.00");

		PlayerCards player2 = new PlayerCards(Set.of(ACE.d(), KING.d()));
		BigDecimal odds2 = wonByPlayer.get(player2);
		AssertionHelper.assertOdds(odds2, "50.00");
	}

	@Test
	void checkWinningOdds_test4() {
		// given
		PokerCalculator calculator = PokerCalculator.shortDeckHoldem();
		calculator.addPlayerCards(ACE.s(), KING.s());
		calculator.addPlayerCards(ACE.d(), KING.d());

		// when
		WinningOddsOutput odds = calculator.checkWinningOdds();

		// then
		assertThat(odds).isNotNull();
		Map<PlayerCards, BigDecimal> wonByPlayer = odds.wonByPlayer();
		assertThat(wonByPlayer).hasSize(2);

		PlayerCards player1 = new PlayerCards(Set.of(ACE.s(), KING.s()));
		BigDecimal odds1 = wonByPlayer.get(player1);
		AssertionHelper.assertOdds(odds1, "50.00");

		PlayerCards player2 = new PlayerCards(Set.of(ACE.d(), KING.d()));
		BigDecimal odds2 = wonByPlayer.get(player2);
		AssertionHelper.assertOdds(odds2, "50.00");
	}
}