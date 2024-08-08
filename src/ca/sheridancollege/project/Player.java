package ca.sheridancollege.project;

import java.util.ArrayList;

public abstract class Player {

    private String name; // the unique name for this player
    protected ArrayList<UnoCard> hand; // the player's hand of cards

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UnoCard> getHand() {
        return hand;
    }

    public abstract void play();

    // Method to draw a card from the deck
    public void drawCard(UnoDeck deck) {
        UnoCard card = deck.drawCard();
        if (card != null) {
            hand.add(card);
        }
    }

    // Method to play a card (implement logic based on game rules)
    public boolean playCard(UnoCard card, UnoCard topCard) {
        // Implement the logic to determine if a card can be played
        // For now, we just assume any card can be played
        return hand.remove(card);
    }

    // Method to check if the player has won (e.g., has no cards left)
    public boolean hasWon() {
        return hand.isEmpty();
    }
}
