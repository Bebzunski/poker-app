package org.bebzunski.poker.hand.output;

import java.util.List;

import org.bebzunski.poker.enums.Face;

public record Hand(HandType type, List<Face> faces) {
}
