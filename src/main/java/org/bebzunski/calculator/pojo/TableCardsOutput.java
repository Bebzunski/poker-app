package org.bebzunski.calculator.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record TableCardsOutput(List<Card> flop, Card turn, Card river) {

	public List<Card> fetchAllStreets() {
		List<Card> communityCards = new ArrayList<>(flop);
		Optional.ofNullable(turn).ifPresent(communityCards::add);
		Optional.ofNullable(river).ifPresent(communityCards::add);
		return communityCards;
	}
}
