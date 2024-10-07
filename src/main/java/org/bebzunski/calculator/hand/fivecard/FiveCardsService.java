package org.bebzunski.calculator.hand.fivecard;

import org.bebzunski.calculator.pojo.Card;

import java.util.Collection;
import java.util.List;

public abstract class FiveCardsService {

    public abstract List<FiveCards> mapFrom(Collection<Card> playerCards, Collection<Card> tableCards);

    protected List<Card> getCardsBy(List<Integer> indexes, List<Card> cards) {
        return indexes.stream().map(cards::get).toList();
    }
}
