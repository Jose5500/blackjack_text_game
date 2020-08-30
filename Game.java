package com.example.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Player player;
    Scanner scanner = new Scanner(System.in);
    Card card = new Card();
    public Game(){
        this.player = new Player();
        backToDealer();
    }

    //every time the user loses or wins, as long as they have money left in their balance, they return here
    public void backToDealer(){
        drawDealer();
        System.out.println(player.getData());
        System.out.println("Please enter how much you would like to bet:");
        int amount;
        while(true) {
            try {
                amount = scanner.nextInt();
                scanner.nextLine();
                if(amount > 0 && amount <= player.getBalance()){
                    break;
                }
                System.out.println("Incorrect amount please try again");
            }catch (InputMismatchException e){
                System.out.println("Incorrect amount please try again");
                scanner.nextLine();
            }
        }
        playGame(amount);
    }

    public void playGame(int bet) {
        int dealerTotal = 0;
        int userTotal = 0;
        ArrayList<String> dealerCards = new ArrayList<>();
        ArrayList<String> userCards = new ArrayList<>();

        card.refreshCards();
        System.out.println("The dealer goes first");
        //gets dealers first card
                String cardName = card.randomCardName();
                Integer cardValue = card.cardValue(cardName);
                dealerTotal += cardValue;
                dealerCards.add(cardName);
                System.out.println("Dealer drew a " + cardName +
                        "\n Dealer total: " + dealerTotal);
                removeFromDeck(cardName);
        pressEnter();

        //gets users cards
        boolean skip = false;
        for (int i = 0; i < 2; i++) {
            cardName = card.randomCardName();
            cardValue = card.cardValue(cardName);
            userTotal += cardValue;
            userCards.add(cardName);
            System.out.println("You drew a " + cardName +
                    "\n Your total: " + userTotal);
            removeFromDeck(cardName);
            pressEnter();
        }
        while (true) {
            if (userTotal > 21) {
                skip = true;
                break;
            }
            String response;
            while (true) {
                System.out.println("Would you like to hit?(yes/no)");
                response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no")) {
                    break;
                } else {
                    System.out.println("That's not a valid response, please try again");
                }
            }
            if (response.equalsIgnoreCase("yes")) {
                cardName = card.randomCardName();
                cardValue = card.cardValue(cardName);
                userTotal += cardValue;
                userCards.add(cardName);
                System.out.println("You drew a " + cardName +
                        "\n Your total: " + userTotal);
                removeFromDeck(cardName);
                pressEnter();
            } else if (response.equalsIgnoreCase("no")) {
                break;
            }
        }
        if(skip != true){
        while(true) {
            if (dealerTotal < 17) {
                cardName = card.randomCardName();
                cardValue = card.cardValue(cardName);
                dealerTotal += cardValue;
                dealerCards.add(cardName);
                System.out.println("Dealer drew a " + cardName +
                        "\n Dealer total: " + dealerTotal);
                removeFromDeck(cardName);
                pressEnter();
            } else {
                break;
            }
        }
        }
        //contains all of the wining/ties/losing scenarios
        if (userTotal > 21) {
            System.out.println("You have busted, you lost" +
                    "\nYour total: " + userTotal + " Dealer Total: " + dealerTotal +
                    "\nAmount Lost: " + bet);
            player.setBalance(player.getBalance() - bet);
            player.setLosses(player.getLosses() + 1);
        } else if (dealerTotal > 21) {
            System.out.println("Dealer busted, you have won" +
                    "\nYour total: " + userTotal + " Dealer Total: " + dealerTotal +
                    "\nAmount won: " + bet);
            player.setBalance(player.getBalance() + bet);
            player.setWins(player.getWins() + 1);
        }else if (userTotal == dealerTotal) {
            System.out.println("You have tied" +
                    "\nYour total: " + userTotal + " Dealer Total: " + dealerTotal +
                    "\nYour bet was returned");
            player.setTies(player.getTies() + 1);
        } else if (userTotal > dealerTotal) {
            System.out.println("You have won" +
                    "\nYour total: " + userTotal + " Dealer Total: " + dealerTotal +
                    "\nAmount won: " + bet);
            player.setBalance(player.getBalance() + bet);
            player.setWins(player.getWins() + 1);
        }else if(userTotal < 21){
            System.out.println("You lost" +
                    "\nYour total: " + userTotal + " Dealer Total: " + dealerTotal +
                    "\nAmount Lost: " + bet);
            player.setBalance(player.getBalance() - bet);
            player.setLosses(player.getLosses() + 1);
        }

        String secondResponse;
        while (true) {
            System.out.println("Would you like to play again?(yes/no)");
            secondResponse = scanner.nextLine();
            if (secondResponse.equalsIgnoreCase("yes") || secondResponse.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("That's not a valid response, please try again");
                scanner.nextLine();
            }
        }
        //user has no more money in their balance
        if(player.getBalance() == 0){
            for (int i = 0; i < 30; i++) {
                System.out.println();
            }
            //Makes it clear where the next set of text will appear
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Thanks for playing" +
                    "\nPlayer Data" +
                    "\n-----------");
            System.out.println(player.getData());
        } else if (secondResponse.equalsIgnoreCase("yes")) {
            backToDealer();
        } else {
            for (int i = 0; i < 30; i++) {
                System.out.println();
            }
            //Makes it clear where the next set of text will appear
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Thanks for playing" +
                    "\nPlayer Data" +
                    "\n-----------");
            System.out.println(player.getData());
            System.out.println(userCards);
            System.out.println(dealerCards);
        }
    }
    //removes certain card from deck
    private void removeFromDeck(String cardName){
        int index = card.cardNames.indexOf(cardName);
        card.cardNames.remove(cardName);
        card.cardValues.remove(index);
    }
    //prompts the press enter method
    private void pressEnter() {
        System.out.println("Press enter to continue");
        while (true) {
            if (scanner.nextLine().isEmpty()) {
                System.out.println();
                break;
            } else {
                System.out.println("That is not the right key");
                System.out.println();
                System.out.println("Press enter to continue");
            }
        }
    }

    public void drawDealer(){
        System.out.println(
                "                   /  ///   \n" +
                        "         .        . .  /  \n" +
                        "          \\\\      <     ) \n" +
                        "          -C\\      \\_- |\n" +
                        "          \\_/     __|__/L__\n" +
                        "                 /         \\\n" +
                        "                      ___   \\\n" +
                        "        ______________  ______________\n" +
                        "       /        \uD83C\uDCDD\uD83C\uDCDD\uD83C\uDCDD\uD83C\uDCDD\uD83C\uDCDD\uD83C\uDCDD                \\\n" +
                        "      /________________________________\\\n" +
                        "      [________________________________]\n" +
                        "         \\ \\  / /            \\ \\  / /\n" +
                        "          \\ \\/ /              \\ \\/ /\n" +
                        "          _\\/ /________________\\ \\/_\n" +
                        "         [_/o/__________________\\o\\_]\n" +
                        "          / /\\ \\              / /\\ \\\n" +
                        "         lc/  \\_\\            /_/  \\_\\");
    }
}
