package org.bebzunski.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.bebzunski.calculator.pojo.Card;
import org.bebzunski.calculator.pojo.Hand;
import org.bebzunski.calculator.pojo.PlayerCards;
import org.bebzunski.calculator.pojo.TableCardsOutput;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class WinningOddsBuilder {

	public static WinningOddsOutput build(Players players, List<TableCardsOutput> tableCardsOutputs) {
		List<GameResult> gameResults = tableCardsOutputs.stream().map(output -> buildGameResult(players, output)).toList();
		Map<PlayerCards, Long> wonGamesByPlayer = buildNrOfWonGamesByPlayer(gameResults);
		Map<PlayerCards, BigDecimal> wonGamesAsPercentByPlayer = buildPercentByPlayer(wonGamesByPlayer);
		return new WinningOddsOutput(wonGamesAsPercentByPlayer);
	}

	private static Map<PlayerCards, Long> buildNrOfWonGamesByPlayer(List<GameResult> gameResults) {
		return gameResults.stream().map(WinningOddsBuilder::findPlayersWhoWon).filter(WinningOddsBuilder::onePlayerWon)
				.map(List::getFirst).collect(groupingBy(Function.identity(), counting()));
	}

	private static boolean onePlayerWon(List<PlayerCards> playerCards) {
		return playerCards.size() == 1;
	}

	private static List<PlayerCards> findPlayersWhoWon(GameResult result) {
		Hand bestHand = result.bestHand();
		return result.handByPlayer().entrySet().stream().filter(entry -> entry.getValue().equals(bestHand)).map(Map.Entry::getKey).toList();
	}

	private static GameResult buildGameResult(Players players, TableCardsOutput tableCardsOutput) {
		List<PlayerCards> playersCards = players.getPlayersCards();
		List<Card> allStreets = tableCardsOutput.fetchAllStreets();

		Map<PlayerCards, Hand> handByPlayer = playersCards.stream()
				.collect(toMap(Function.identity(), pc -> BestHandFinder.findBestHandFrom(pc.cards(), allStreets)));

		Hand bestHand = BestHandFinder.findBestHand(handByPlayer.values());

		return new GameResult(bestHand, handByPlayer);
	}

	private static Map<PlayerCards, BigDecimal> buildPercentByPlayer(Map<PlayerCards, Long> wonGamesByPlayer) {
		int numberOfGames = wonGamesByPlayer.values().stream().mapToInt(Long::intValue).sum();
		BigDecimal divider = new BigDecimal(numberOfGames).divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
		return wonGamesByPlayer.entrySet().stream()
				.collect(toMap(Map.Entry::getKey, e -> new BigDecimal(e.getValue()).divide(divider, 2, RoundingMode.HALF_UP)));
	}

	private record GameResult(Hand bestHand, Map<PlayerCards, Hand> handByPlayer) {
	}
}
