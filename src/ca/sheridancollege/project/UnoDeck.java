package ca.sheridancollege.project;

import java.util.ArrayList;

public class UnoDeck extends GroupOfCards {

    public UnoDeck() {
        super(108); // Initialize the base class with the size
        cards = new ArrayList<>(); // Initialize the ArrayList
        for (UnoCard.Color color : UnoCard.Color.values()) {
            if (color == UnoCard.Color.WILD) {
                for (int i = 0; i < 4; i++) {
                    cards.add(new UnoCard(color, UnoCard.Value.WILD));
                    cards.add(new UnoCard(color, UnoCard.Value.WILD_DRAW_FOUR));
                }
            } else {
                for (UnoCard.Value value : UnoCard.Value.values()) {
                    if (value != UnoCard.Value.WILD && value != UnoCard.Value.WILD_DRAW_FOUR) {
                        cards.add(new UnoCard(color, value));
                        if (value != UnoCard.Value.ZERO) {
                            cards.add(new UnoCard(color, value));
                        }
                    }
                }
            }
        }
        shuffle();
    }

    @Override
    public UnoCard drawCard() {
        if (cards.isEmpty()) {
            return null; // Handle the empty deck case
        }
        return cards.remove(cards.size() - 1);
    }
}
