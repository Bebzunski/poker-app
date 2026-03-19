package org.bebzunski.poker.card.api;

import org.bebzunski.poker.pojo.Card;

public record CardDTO(String code, String value, String suit) {

	public CardDTO(Card card) {
		this(card.getId(), card.face().toString(), card.color().toString());
	}
}
