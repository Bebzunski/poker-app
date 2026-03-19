package org.bebzunski.poker.card;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bebzunski.poker.bridge.BridgeService;
import org.bebzunski.poker.card.api.CardDTO;
import org.bebzunski.poker.pojo.Card;
import org.bebzunski.poker.services.Deck;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deal")
public class CardDealerController {

	@GetMapping("/full-deck")
	public List<CardDTO> dealCards() {
		Deck fullDeck = Deck.fullDeck();
		int cardsToDeal = fullDeck.getCardsScope().size();
		return fullDeck.dealCards(cardsToDeal).stream().map(CardDTO::new).toList();
	}

	@GetMapping("/{amount}")
	public List<Card> dealCards(@PathVariable int amount) {
		Deck fullDeck = Deck.fullDeck();
		int nrOfCards = Math.clamp(amount, 0, fullDeck.getCardsScope().size());
		return fullDeck.dealCards(nrOfCards);
	}

	@GetMapping("/players/{players}/on-hand/{onHand}")
	public List<List<Card>> dealCards(@PathVariable int players, @PathVariable int onHand) {
		Deck fullDeck = Deck.fullDeck();
		int nrOfPlayers = Math.max(players, 0);
		int cardsOnHand = Math.max(onHand, 0);
		int nrOfCardsToDeal = Math.min(nrOfPlayers * cardsOnHand, fullDeck.getCardsScope().size());
		List<List<Card>> playersCards = IntStream.range(0, nrOfPlayers).mapToObj(i -> new ArrayList<Card>()).collect(Collectors.toList());
		int currentCard = 0;
		for (Card card : fullDeck.dealCards(nrOfCardsToDeal)) {
			int currentPlayer = currentCard % nrOfPlayers;
			playersCards.get(currentPlayer).add(card);
			currentCard++;
		}
		return playersCards;
	}

	@GetMapping("/bridge/download")
	public ResponseEntity<byte[]> fullDeckRandomBridge() {
		String pbnContent = BridgeService.dealCardsInPbnFormat();
		byte[] data = pbnContent.getBytes(StandardCharsets.UTF_8);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"random_bridge.pbn\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.contentLength(data.length)
				.body(data);
	}

	@GetMapping("/bridge/write")
	public void fullDeckRandomBridgeWrite() throws IOException {
		Path directoryPath = Paths.get("C:\\Users\\dbogdalski\\OneDrive - LPP S.A\\Pulpit\\moje\\rozdania");
		Path filePath = directoryPath.resolve("losowe_rozdanie_%s.pbn".formatted(LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))));
		String content = BridgeService.dealCardsInPbnFormat();
		Files.writeString(filePath, content);
	}
}
