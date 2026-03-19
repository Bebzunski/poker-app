package org.bebzunski.poker.controller;

import java.util.List;

import org.bebzunski.poker.hand.HandReader;
import org.bebzunski.poker.hand.fivecard.FiveCards;
import org.bebzunski.poker.hand.output.Hand;
import org.bebzunski.poker.pojo.Card;
import org.bebzunski.poker.services.Deck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PokerController {

	@GetMapping("/hand")
	public Hand dealCards() {
		Deck fullDeck = Deck.fullDeck();
		fullDeck.loadFresh();
		FiveCards fiveCards = new FiveCards(fullDeck.dealCards(5));
		HandReader handReader = HandReader.fullDeck();
		return handReader.readFrom(fiveCards);
	}
}
