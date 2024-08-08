package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

public abstract class GroupOfCards {

    protected ArrayList<UnoCard> cards;
    private int size;

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>(size);
    }

    public ArrayList<UnoCard> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public abstract UnoCard drawCard();
}
