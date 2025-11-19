package org.bebzunski.poker.hand;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bebzunski.poker.enums.Face;
import org.bebzunski.poker.hand.fivecard.FiveCards;
import org.bebzunski.poker.hand.output.Hand;
import org.bebzunski.poker.hand.output.HandType;
import org.bebzunski.poker.pojo.Card;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.bebzunski.poker.hand.output.HandType.FLUSH;
import static org.bebzunski.poker.hand.output.HandType.HIGH_CARD;
import static org.bebzunski.poker.hand.output.HandType.STRAIGHT;
import static org.bebzunski.poker.hand.output.HandType.STRAIGHT_FLUSH;

public class HandReader {

	private static final Comparator<FaceOccurrence> FROM_HIGHEST_FACE_OCCURRENCE = comparing(FaceOccurrence::occurrence, reverseOrder())
			.thenComparing(FaceOccurrence::face, reverseOrder());

	private final Set<Face> lowAceStraight;

	public static HandReader fullDeck() {
		return new HandReader(Face.TWO);
	}

	public static HandReader sixLowDeck() {
		return new HandReader(Face.SIX);
	}

	public Hand readFrom(FiveCards fiveCards) {
		return isPairKindHand(fiveCards) ? findPairKindHand(fiveCards) : findNoPairKindHand(fiveCards);
	}

	private HandReader(Face minFace) {
		this.lowAceStraight = buildLowAceStraight(minFace);
	}

	private boolean isPairKindHand(FiveCards fiveCards) {
		return fiveCards.fetchFaces().size() < FiveCards.FIVE_CARDS;
	}

	private Hand findPairKindHand(FiveCards fiveCards) {
		Map<Face, Long> occurrencesByFace = fiveCards.getCards().stream().collect(groupingBy(Card::face, counting()));

		List<FaceOccurrence> faceOccurrences = occurrencesByFace.entrySet().stream()
				.map(e -> new FaceOccurrence(e.getKey(), e.getValue().intValue())).sorted(FROM_HIGHEST_FACE_OCCURRENCE).toList();

		List<Integer> facesStructure = faceOccurrences.stream().map(FaceOccurrence::occurrence).toList();

		HandType pairHandType = Arrays.stream(HandType.values())
				.filter(HandType::isPairType)
				.filter(hand -> hand.getFacesStructure().equals(facesStructure))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Cannot find a pair hand type for: " + facesStructure));

		List<Face> faces = faceOccurrences.stream().map(FaceOccurrence::face).toList();

		return new Hand(pairHandType, faces);
	}

	private Hand findNoPairKindHand(FiveCards fiveCards) {
		List<Face> faces = fiveCards.fetchFaces().stream().sorted(reverseOrder()).toList();
		if (isStraight(fiveCards)) {
			HandType type = isFlush(fiveCards) ? STRAIGHT_FLUSH : STRAIGHT;
			Face highestFace = isLowAceStraight(fiveCards) ? faces.get(1) : faces.getFirst();
			return new Hand(type, List.of(highestFace));
		}
		HandType type = isFlush(fiveCards) ? FLUSH : HIGH_CARD;
		return new Hand(type, faces);
	}

	private boolean isFlush(FiveCards fiveCards) {
		return fiveCards.fetchColors().size() == 1;
	}

	private boolean isStraight(FiveCards fiveCards) {
		if (isLowAceStraight(fiveCards)) {
			return true;
		}
		List<Face> facesFromLowestToHighest = fiveCards.fetchFaces().stream().sorted().toList();
		Face lowestFace = facesFromLowestToHighest.getFirst();
		Face highestFace = facesFromLowestToHighest.getLast();
		int lastIndex = facesFromLowestToHighest.size() - 1;
		return lowestFace.ordinal() + lastIndex == highestFace.ordinal();
	}

	private boolean isLowAceStraight(FiveCards fiveCards) {
		return fiveCards.fetchFaces().equals(lowAceStraight);
	}

	private Set<Face> buildLowAceStraight(Face minFace) {
		Set<Face> faces = Face.getRange(minFace.ordinal(), minFace.ordinal() + 3);
		faces.add(Face.ACE);
		return faces;
	}

	private record FaceOccurrence(Face face, int occurrence) {
	}
}
