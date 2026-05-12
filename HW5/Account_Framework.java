/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2026
author: <your name>, Charles Peeke 

TODO - Summarize this class

--------------------------------------------------------------------------*/

import java.text.DecimalFormat;

public class Account {
    // TODO:
    public String accountName;
    public int accountNumber;
    public double balance;

    // TODO: 
    public Account(String name) {

    }

    // TODO:
    private void assignAccountNumber() {
        // TODO: 

    }

    // Getters and setters
    public int getAccountNumber() {
        return -1;
    }

    public double getBalance() {
        return -1.0;
    }

    // Deposit and Withdraw Methods
    // TODO: 
    public void deposit(double amount) {
        // TODO: 

    }

    // TODO: 
    public void withdraw(double amount) {
        // TODO:

    }

    // TODO:
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String s = "";
        s += "Account Name: " + this.accountName + " \t";
        s += "Account Number: " + this.accountNumber + " \t";
        s += "Balance: " + df.format(this.balance);
        return s;
    }
}
