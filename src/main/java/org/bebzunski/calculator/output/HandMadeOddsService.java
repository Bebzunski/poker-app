package org.bebzunski.calculator.output;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.bebzunski.calculator.hand.BestHandService;
import org.bebzunski.calculator.services.PlayersComponent;
import org.bebzunski.calculator.hand.output.HandType;
import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.hand.output.Hand;
import org.bebzunski.calculator.pojo.TableCardsIteration;

import lombok.RequiredArgsConstructor;

import static org.bebzunski.calculator.constants.Constant.ONE_HUNDRED;

@RequiredArgsConstructor
public class HandMadeOddsService {

	private final BestHandService bestHandService;

	public HandOddsOutput build(PlayersComponent playersComponent, List<TableCardsIteration> tableCardsIteration) {
		Set<Card> twoCards = playersComponent.getFirst().cards();
		BigDecimal nrOfSimulations = new BigDecimal(tableCardsIteration.size());

		Map<HandType, Long> occurrenceByHand = tableCardsIteration.stream().map(TableCardsIteration::fetchTableCards)
				.map(communityCards -> bestHandService.findFrom(twoCards, communityCards))
				.collect(Collectors.groupingBy(Hand::type, Collectors.counting()));

		List<HandOdds> handOdds = occurrenceByHand.entrySet().stream()
				.map(entry -> new HandOdds(entry.getKey(), calculateOdds(entry.getValue(), nrOfSimulations)))
				.sorted(Comparator.comparing(HandOdds::odds, Comparator.reverseOrder())).toList();

		return new HandOddsOutput(handOdds);
	}

	private BigDecimal calculateOdds(Long handOccurrence, BigDecimal nrOfSimulations) {
		return new BigDecimal(handOccurrence).multiply(ONE_HUNDRED).divide(nrOfSimulations, 2, RoundingMode.HALF_UP);
	}
}
