package org.bebzunski.poker.output;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.math3.fraction.Fraction;
import org.bebzunski.poker.hand.BestHandService;
import org.bebzunski.poker.hand.output.Hand;
import org.bebzunski.poker.pojo.Card;
import org.bebzunski.poker.pojo.PlayerCards;
import org.bebzunski.poker.pojo.BoardRunout;

import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.toMap;
import static org.bebzunski.poker.constants.Constant.ONE_HUNDRED;

@RequiredArgsConstructor
public class WinningHandOddsService {

	private final BestHandService bestHandService;

	public WinningOddsOutput build(List<PlayerCards> playersCards, List<BoardRunout> iterations) {
		Map<PlayerCards, Fraction> pointsByPlayer = new HashMap<>();
		playersCards.forEach(pc -> pointsByPlayer.put(pc, Fraction.ZERO));

		iterations.forEach(i -> {
			List<PlayerCards> playersWithBestHand = findIterationWinners(playersCards, i);
			Fraction points = new Fraction(1, playersWithBestHand.size());
			playersWithBestHand.forEach(player -> pointsByPlayer.compute(player, (k, v) -> points.add(v)));
		});

		BigDecimal sum = new BigDecimal(iterations.size());
		Map<PlayerCards, BigDecimal> wonGamesByPlayer = pointsByPlayer.entrySet().stream()
				.collect(toMap(Map.Entry::getKey, e -> mapToPercent(e.getValue(), sum)));

		return new WinningOddsOutput(wonGamesByPlayer);
	}

	private List<PlayerCards> findIterationWinners(List<PlayerCards> players, BoardRunout iteration) {
		List<Card> tableCards = iteration.fetchTableCards();
		Map<PlayerCards, Hand> handByPlayer = players.stream()
				.collect(toMap(Function.identity(), p -> bestHandService.findFrom(p.cards(), tableCards)));
		Hand bestHand = bestHandService.findFrom(handByPlayer.values());
		return handByPlayer.entrySet().stream().filter(e -> e.getValue().equals(bestHand)).map(Map.Entry::getKey).toList();
	}

	private BigDecimal mapToPercent(Fraction fraction, BigDecimal sum) {
		BigDecimal numerator = new BigDecimal(fraction.getNumerator()).multiply(ONE_HUNDRED);
		BigDecimal denominator = new BigDecimal(fraction.getDenominator()).multiply(sum);
		return numerator.divide(denominator, 2, RoundingMode.HALF_UP);
	}
}
