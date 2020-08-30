package com.example.company;

import java.lang.reflect.Array;
import java.util.*;

public class Card {
    public Card(){

    }
    ArrayList<String> cardNames = new ArrayList<>();
    ArrayList<Integer> cardValues = new ArrayList<>();

    public void refreshCards(){
        cardNames.clear();
        cardValues.clear();
        int counter = 0;
        String suit = null;
        while(counter < 4) {
            switch (counter){
                case 0:
                    suit = "Spades";
                    break;
                case 1:
                    suit = "Clubs";
                    break;
                case 2:
                    suit = "Diamonds";
                    break;
                case 3:
                    suit = "Hearts";
                    break;
            }
            for (int i = 1; i < 14; i++) {
                if (i == 10) {
                    cardNames.add("Jack of " + suit);
                    cardValues.add(10);
                } else if (i == 11) {
                    cardNames.add("Queen of " + suit);
                    cardValues.add(10);
                } else if (i == 12) {
                    cardNames.add("King of " + suit);
                    cardValues.add(10);
                } else if (i == 13) {
                    cardNames.add("Ace of " + suit);
                    cardValues.add(11);
                } else {
                    cardNames.add(i + " of " + suit);
                    cardValues.add(i);
                }

            }
            counter++;
        }
    }

    public String randomCardName(){
        Random random = new Random();
        int randomNum = random.nextInt(cardNames.size());
        return cardNames.get(randomNum);
    }

    public Integer cardValue(String name){
        int index = cardNames.indexOf(name);
        return  cardValues.get(index);
    }
}
