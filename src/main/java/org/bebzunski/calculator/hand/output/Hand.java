package org.bebzunski.calculator.hand.output;

import java.util.List;

import org.bebzunski.calculator.enums.Face;

public record Hand(HandType type, List<Face> faces) {
}
