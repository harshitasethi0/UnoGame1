package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class UnoGame extends Game {

    private UnoDeck deck;
    private UnoCard topCard;
    private ArrayList<Player> players;
    private int currentPlayerIndex;

    public UnoGame(String name) {
        super(name);
        deck = new UnoDeck();
        deck.shuffle();
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        // Initialize the players (for demonstration purposes, add players here)
        players.add(new UnoPlayer("Player1")); // Example player
        players.add(new UnoPlayer("Player2")); // Example player
        startGame();
    }

    private void startGame() {
        // Deal 7 cards to each player
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }
        // Set the top card
        topCard = deck.drawCard();
        System.out.println("Top card: " + topCard);
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("Current player: " + currentPlayer.getName());
            System.out.println("Top card: " + topCard);
            System.out.println("Your hand: " + currentPlayer.getHand());

            // Ask player to play a card or draw
            System.out.println("Enter 'play' to play a card or 'draw' to draw a card.");
            String action = scanner.nextLine();
            if (action.equalsIgnoreCase("play")) {
                // Implement logic to play a card
                System.out.println("Enter the card to play (e.g., 'Red 5'):");
                String cardInput = scanner.nextLine();
                UnoCard playedCard = parseCard(cardInput);

                if (playedCard != null && currentPlayer.playCard(playedCard, topCard)) {
                    topCard = playedCard;
                } else {
                    System.out.println("Invalid card or not playable. Try again.");
                    continue;
                }
            } else if (action.equalsIgnoreCase("draw")) {
                currentPlayer.drawCard(deck);
            } else {
                System.out.println("Invalid action. Try again.");
                continue;
            }

            // Check if the current player has won
            if (currentPlayer.hasWon()) {
                declareWinner();
                break;
            }

            // Move to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        scanner.close();
    }

    private UnoCard parseCard(String cardInput) {
    // Expected input format: "Color Value" (e.g., "Red 8")
    String[] parts = cardInput.trim().split(" ");
    
    if (parts.length != 2) {
        System.out.println("Invalid card format. Please use 'Color Value' format.");
        return null;
    }
    
    UnoCard.Color color;
    UnoCard.Value value;

    try {
        color = UnoCard.Color.valueOf(parts[0].toUpperCase());
    } catch (IllegalArgumentException e) {
        System.out.println("Invalid card color: " + parts[0]);
        return null;
    }

    // Convert numerical values to appropriate enum
    try {
        value = UnoCard.Value.valueOf(parts[1].toUpperCase());
    } catch (IllegalArgumentException e) {
        try {
            // Attempt to parse numerical values directly
            int number = Integer.parseInt(parts[1]);
            if (number >= 0 && number <= 9) {
                value = UnoCard.Value.values()[number];
            } else {
                System.out.println("Invalid card value: " + parts[1]);
                return null;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid card value: " + parts[1]);
            return null;
        }
    }

    // Create and return the UnoCard object
    return new UnoCard(color, value);
}


    @Override
    public void declareWinner() {
        Player winner = players.get(currentPlayerIndex);
        System.out.println("The winner is: " + winner.getName());
    }
}
