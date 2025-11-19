package org.bebzunski.poker.hand.fivecard;

import java.util.Collection;
import java.util.Set;

import org.bebzunski.poker.enums.Color;
import org.bebzunski.poker.enums.Face;
import org.bebzunski.poker.pojo.Card;
import org.bebzunski.poker.services.CardService;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.springframework.util.CollectionUtils.isEmpty;

@Getter
@EqualsAndHashCode
public final class FiveCards {

	public static final int FIVE_CARDS = 5;
	private final Set<Card> cards;

	public FiveCards(Collection<Card> inputCards1, Collection<Card> inputCards2) {
		this(CardService.merge(inputCards1, inputCards2));
	}

	public FiveCards(Collection<Card> inputCards) {
		Set<Card> cards = isEmpty(inputCards) ? Set.of() : inputCards.stream().collect(toUnmodifiableSet());
		if (cards.size() != FIVE_CARDS) {
			throw new IllegalArgumentException("Cards must be exactly %d, but is %d.".formatted(FIVE_CARDS, cards.size()));
		}
		this.cards = cards;
	}

	public Set<Face> fetchFaces() {
		return cards.stream().map(Card::face).collect(toSet());
	}

	public Set<Color> fetchColors() {
		return cards.stream().map(Card::color).collect(toSet());
	}
}
