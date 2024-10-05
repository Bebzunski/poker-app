package org.bebzunski.calculator.pojo;

import java.util.List;

import org.bebzunski.calculator.enums.Face;
import org.bebzunski.calculator.enums.HandType;

public record Hand(HandType type, List<Face> faces) implements Comparable<Hand> {

	@Override
	public int compareTo(Hand that) {
		if (that == null) {
			return 1;
		}
		if (this.type != that.type) {
			return this.type.compareTo(that.type);
		}
		for (int i = 0; i < faces.size(); i++) {
			int result = this.faces.get(i).compareTo(that.faces.get(i));
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
}
