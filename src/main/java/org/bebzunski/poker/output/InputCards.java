package org.bebzunski.poker.output;

import java.util.List;

public record InputCards(List<InputPlayer> players, List<String> board) {
}
