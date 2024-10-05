package org.bebzunski.calculator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bebzunski.calculator.enums.Face;
import org.bebzunski.calculator.enums.HandType;
import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.pojo.Hand;

import static org.bebzunski.calculator.constants.Constant.FIVE_CARDS;
import static org.bebzunski.calculator.constants.Constant.LOW_STRAIGHT;
import static org.bebzunski.calculator.enums.HandType.FLUSH;
import static org.bebzunski.calculator.enums.HandType.HIGH_CARD;
import static org.bebzunski.calculator.enums.HandType.STRAIGHT;
import static org.bebzunski.calculator.enums.HandType.STRAIGHT_FLUSH;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class HandRecognizer {

	private static final Comparator<FaceNumber> COMPARATOR = Comparator.comparing(FaceNumber::number, Comparator.reverseOrder())
			.thenComparing(FaceNumber::face, Comparator.reverseOrder());

	public static Hand findHand(Collection<Card> givenCards) {
		Set<Card> cards = toNewHashSet(givenCards);
		if (cards.size() != FIVE_CARDS) {
			throw new IllegalArgumentException("Cards must be exactly %d, but was %d.".formatted(FIVE_CARDS, cards.size()));
		}
		return hasPair(cards) ? findPairKindHand(cards) : findNoPairKindHand(cards);
	}

	private static HashSet<Card> toNewHashSet(Collection<Card> givenCards) {
		return new HashSet<>(Optional.ofNullable(givenCards).orElse(List.of()));
	}

	private static boolean hasPair(Set<Card> cards) {
		return cards.size() > cards.stream().map(Card::face).distinct().count();
	}

	private static Hand findPairKindHand(Set<Card> cards) {
		List<FaceNumber> faceNumbers = mapToFaceNumbers(cards);
		HandType type = findHandType(faceNumbers);
		List<Face> faces = faceNumbers.stream().map(FaceNumber::face).toList();
		return new Hand(type, faces);
	}

	private static List<FaceNumber> mapToFaceNumbers(Set<Card> cards) {
		return cards.stream().collect(groupingBy(Card::face)).entrySet().stream()
				.map(entry -> new FaceNumber(entry.getKey(), entry.getValue().size())).sorted(COMPARATOR).toList();
	}

	private static HandType findHandType(List<FaceNumber> faceNumbers) {
		List<Integer> facesStructure = faceNumbers.stream().map(FaceNumber::number).toList();
		return Arrays.stream(HandType.values()).filter(hand -> hand.getFacesStructure().equals(facesStructure)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown face number: " + facesStructure));
	}

	private static Hand findNoPairKindHand(Set<Card> cards) {
		boolean isFlush = isFlush(cards);
		boolean isStraight = isStraight(cards);
		List<Face> faces = cards.stream().map(Card::face).sorted(Comparator.reverseOrder()).collect(toList());
		if (isFlush && isStraight) {
			return new Hand(STRAIGHT_FLUSH, aceBottomIfLowStraight(faces));
		}
		if (isFlush) {
			return new Hand(FLUSH, faces);
		}
		if (isStraight) {
			return new Hand(STRAIGHT, aceBottomIfLowStraight(faces));
		}
		return new Hand(HIGH_CARD, faces);
	}

	private static boolean isFlush(Set<Card> cards) {
		return cards.stream().map(Card::color).distinct().count() == 1;
	}

	private static boolean isStraight(Set<Card> cards) {
		Set<Face> faces = cards.stream().map(Card::face).collect(Collectors.toSet());
		if (faces.containsAll(LOW_STRAIGHT)) {
			return true;
		}
		List<Integer> ordinals = faces.stream().map(Enum::ordinal).sorted().toList();
		int lastIndex = FIVE_CARDS - 1;
		return ordinals.get(lastIndex) == ordinals.getFirst() + lastIndex;
	}

	private static List<Face> aceBottomIfLowStraight(List<Face> faces) {
		if (new HashSet<>(faces).containsAll(LOW_STRAIGHT)) {
			faces.remove(Face.ACE);
			faces.add(Face.ACE);
		}
		return faces;
	}

	private record FaceNumber(Face face, int number) {
	}
}
