package org.bebzunski.poker.output;

import java.math.BigDecimal;
import java.util.Map;

import org.bebzunski.poker.pojo.PlayerCards;

public record WinningOddsOutput(Map<PlayerCards, BigDecimal> wonByPlayer) {
}
