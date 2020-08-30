package com.example.company;

public final class Player {
    private int balance = 1000;
    private int wins = 0;
    private int losses = 0;
    private int ties = 0;

    public Player(){

    }
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }
    public String getData(){
        return("Balance: " + this.balance +
                "\nWins: " + this.wins +
                "\nLosses: " + this.losses +
                "\nTies: " + this.ties);
    }
}
