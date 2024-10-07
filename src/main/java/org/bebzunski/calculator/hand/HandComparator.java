package org.bebzunski.calculator.hand;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bebzunski.calculator.enums.Face;
import org.bebzunski.calculator.hand.output.Hand;
import org.bebzunski.calculator.hand.output.HandType;

import static org.bebzunski.calculator.hand.output.HandType.FLUSH;
import static org.bebzunski.calculator.hand.output.HandType.FULL_HOUSE;
import static org.bebzunski.calculator.hand.output.HandType.HIGH_CARD;
import static org.bebzunski.calculator.hand.output.HandType.ONE_PAIR;
import static org.bebzunski.calculator.hand.output.HandType.QUADS;
import static org.bebzunski.calculator.hand.output.HandType.STRAIGHT;
import static org.bebzunski.calculator.hand.output.HandType.STRAIGHT_FLUSH;
import static org.bebzunski.calculator.hand.output.HandType.THREE_OF_KIND;
import static org.bebzunski.calculator.hand.output.HandType.TWO_PAIR;

public class HandComparator implements Comparator<Hand> {

	private final Map<HandType, Integer> handTypesRanking;

	public static HandComparator fullDeck() {
		return new HandComparator(List.of(
				STRAIGHT_FLUSH,
				QUADS,
				FULL_HOUSE,
				FLUSH,
				STRAIGHT,
				THREE_OF_KIND,
				TWO_PAIR,
				ONE_PAIR,
				HIGH_CARD
		));
	}

	public static HandComparator shortDeck() {
		return new HandComparator(List.of(
				STRAIGHT_FLUSH,
				QUADS,
				FLUSH,
				FULL_HOUSE,
				STRAIGHT,
				THREE_OF_KIND,
				TWO_PAIR,
				ONE_PAIR,
				HIGH_CARD
		));
	}

	private HandComparator(List<HandType> handTypesFromHighest) {
		this.handTypesRanking = handTypesFromHighest.stream()
				.collect(Collectors.toMap(Function.identity(), handTypesFromHighest::indexOf));
	}

	@Override
	public int compare(Hand o1, Hand o2) {
		if (o1.type() != o2.type()) {
			return getRank(o1.type()) < getRank(o2.type()) ? 1 : -1;
		}

		for (int i = 0; i < o1.faces().size(); i++) {
			Face face1 = o1.faces().get(i);
			Face face2 = o2.faces().get(i);
			int result = face1.compareTo(face2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}

	private int getRank(HandType type) {
		return Optional.ofNullable(handTypesRanking.get(type))
				.orElseThrow(() -> new IllegalStateException("Hand type not defined in ranking: %s".formatted(type)));
	}
}
