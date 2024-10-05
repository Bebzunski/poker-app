package org.bebzunski.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.bebzunski.calculator.enums.HandType;
import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.pojo.Hand;
import org.bebzunski.calculator.pojo.HandOdds;
import org.bebzunski.calculator.pojo.HandOddsOutput;
import org.bebzunski.calculator.pojo.TableCardsOutput;

import static org.bebzunski.calculator.constants.Constant.ONE_HUNDRED;

public class HandOddsBuilder {

	public static HandOddsOutput build(Players players, List<TableCardsOutput> tableCardsOutputs) {
		Set<Card> twoCards = players.getFirst().cards();
		BigDecimal nrOfSimulations = new BigDecimal(tableCardsOutputs.size());

		Map<HandType, Long> occurrenceByHand = tableCardsOutputs.stream().map(TableCardsOutput::fetchAllStreets)
				.map(communityCards -> BestHandFinder.findBestHandFrom(twoCards, communityCards))
				.collect(Collectors.groupingBy(Hand::type, Collectors.counting()));

		List<HandOdds> handOdds = occurrenceByHand.entrySet().stream()
				.map(entry -> new HandOdds(entry.getKey(), calculateOdds(entry.getValue(), nrOfSimulations)))
				.sorted(Comparator.comparing(HandOdds::odds, Comparator.reverseOrder())).toList();

		return new HandOddsOutput(handOdds);
	}

	private static BigDecimal calculateOdds(Long handOccurrence, BigDecimal nrOfSimulations) {
		return new BigDecimal(handOccurrence).multiply(ONE_HUNDRED).divide(nrOfSimulations, 2, RoundingMode.HALF_UP);
	}
}
