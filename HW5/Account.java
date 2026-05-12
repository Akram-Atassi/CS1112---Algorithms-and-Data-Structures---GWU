/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2026
author: Akram Atassi, Charles Peeke 

--------------------------------------------------------------------------*/

import java.text.DecimalFormat;

public class Account {
    public String accountName;              // Name associated with the account.
    public int accountNumber;               // Number associated with the account.
    public double balance;                  // Balance associated with the account.

    public Account(String name) {
        this.accountName = name;            // Base value of accountName.
        this.balance = 0;                   // Base value of balance.
        assignAccountNumber();              // Assigns an account number to this account.

    }

    // Assigns an account number to the account using ASCII values. No input, returns void.
    private void assignAccountNumber() {
        for (int i = 0; i < this.accountName.length(); i++) {
            this.accountNumber += (int) this.accountName.charAt(i);     //Iterates through the accountName, setting ASCII values as the accountNumber
        }

    }

    // Getters and setters

    // Returns accountNumber. No input.
    public int getAccountNumber() {
        return accountNumber;
    }

    // Returns balance. No input.
    public double getBalance() {
        return balance;
    }

    // Deposit and Withdraw Methods

    // Deposits an input amount into the account, updating the value of balance. Amount is the input. Returns void.
    public void deposit(double amount) {
        this.balance += amount;

    }

    // Withdraws an input amount into the account, updating the value of balance. Amount is the input. Returns void.
    public void withdraw(double amount) {
        this.balance -= amount;

    }

    // Combines all the elements to create the account.
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
