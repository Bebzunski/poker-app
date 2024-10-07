package org.bebzunski.calculator.output;

import java.math.BigDecimal;
import java.util.Map;

import org.bebzunski.calculator.pojo.PlayerCards;

public record WinningOddsOutput(Map<PlayerCards, BigDecimal> wonByPlayer) {
}
