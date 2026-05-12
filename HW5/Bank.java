/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2026
authors: Akram Atassi , Charles Peeke 

--------------------------------------------------------------------------*/

// Creates Bank class containing all the following info.

import java.util.Queue;

public class Bank {
    public String bankName;                     // Name of the Bank
    private Account[] accounts;                 // List of the accounts at the bank
    private Queue transactions;                 // List of the transactions at the bank
    private int count;                          // Number of accounts
    
    // Bank class with the same items as the previous, except this one is to be used when creating new accounts.
    public Bank(String name) {
        this.bankName = name;
        this.accounts = new Account[10];
        this.transactions = new Queue();
        this.count = 0;
    }

    // Creates new account using the previous class. Returns accountNumber, int. Input is the name of the account.
    public int createAccount(String name) {
        Account newAcc = new Account(name);
        // Check if accounts is full and reallocate if necessary
        if (this.accounts[this.accounts.length - 1] != null) {
            reallocateAccounts();
        }
        // Add new account to accounts
        for (int i = 0; i < this.accounts.length; i++) {
            if (this.accounts[i] == null) {
                this.accounts[i] = newAcc;
                break;
            }
        }
        // Increment count
        this.count++;
        // Return account number
        return newAcc.getAccountNumber();
    }

    // CLoses account. Input is accountNumber. Returns balance of the deleted account.
    public double closeAccount(int accountNumber) {
        // Iterative search accounts for accountNumber
        // If found, remove account from accounts
        // Return remaining balance
        // If not found, do nothing, return -1

        for (int i = 0; i < this.accounts.length; i++) {
            if (this.accounts[i].getAccountNumber() == accountNumber) {
                double remaining = this.accounts[i].getBalance();
                // Shift remaining accounts to the left
                for (int j = i; j < this.accounts.length - 1; j++) {
                    this.accounts[j] = this.accounts[j + 1];
                }
                // Make sure last account is null to avoid duplicates
                this.accounts[this.accounts.length - 1] = null;
                this.count--;
                return remaining;
            }
        }
        return -1;
    }

    // Finds the account using input AccountNumber and then returns its accountBalance.
    public double getBalance(int accountNumber) {
        // Iterative search accounts for accountNumber
        // If found, return balance
        // If not found, do nothing, return -1
        for (int i = 0; i < this.count; i++) {
            if (this.accounts[i].getAccountNumber() == accountNumber) {
                return this.accounts[i].getBalance();
            }
        }
        return -1;
    }

    // Deposits an inputn1 amount of money into the account defined by inputn2 accountNumber. Returns void.
    public void deposit(int accountNumber, double amount) {
        if (amount < 0) {
            return;
        }
        
        // Iterative search accounts for accountNumber
        // If found, deposit amount
        // If not found, do nothing

        for (int i = 0; i < this.count; i++) {
            if (this.accounts[i].getAccountNumber() == accountNumber) {
                this.accounts[i].deposit(amount);
            }
        }
    }

    // Withdraws an inputn1 amount of money into the account defined by inputn2 accountNumber. Returns void.
    public void withdraw(int accountNumber, double amount) {
        if (amount < 0) {
            return;
        }
        
        // Iterative search accounts for accountNumber
        // If found, withdraw amount
        // If not found, do nothing

        for (int i = 0; i < this.count; i++) {
            if (this.accounts[i].getAccountNumber() == accountNumber) {
                this.accounts[i].withdraw(amount);
            }
        }
    }

    // -------------------------------------   
    // Transactions Methods
    // Adds the input transaction t to the list of transactions previously defined in the Bank class. Returns void.
    public void addTransaction(Transaction t) {
        transactions.enqueue(t);
    }

    // Dequeues the last transaction in the list. Checks its nature. Calls the function it is connected to. No input, and returns void.
    public void processTransaction() {
        Transaction t = transactions.dequeue();
        if (t == null) {
            return;
        }
        // Perform transaction based on operation
        // If operation is deposit, call deposit
        // If operation is withdraw, call withdraw
        // If operation is close, call closeAccount
        // If operation is create, call createAccount

        if (t.getOperation() == 1) {
            deposit(t.getAccountNumber(), t.getAmount());
        } else if (t.getOperation() == 2) {
            withdraw(t.getAccountNumber(), t.getAmount());
        } else if (t.getOperation() == 0) {
            // Iterative search accounts
            // If account is found, close the account
            // If account is not found, create the account

            boolean found = false;
            for (int i = 0; i < this.count; i++) {
                if (this.accounts[i].getAccountNumber() == t.getAccountNumber()) {
                    closeAccount(t.getAccountNumber());
                    found = true;
                    break;
                }
            }
            // If account is not found, create the account
            // Convert Account number to ascii value as account name
            if (!found) {
                // Convert Account number to ascii value
                // convert ascii value to char
                // convert char to string

                int ascii = t.getAccountNumber();
                char c = (char) ascii;
                String name = "" + c;
                createAccount(name);
            }
        }
    }

    // If list of accounts is full, creates a deep copy of the previous list with double the size. No input, returns void.
    private void reallocateAccounts () {
        // create new array with double the size
        // copy old array into new array
        // set old array to new array

        Account[] newAccounts = new Account[this.accounts.length * 2];
        for (int i = 0; i < this.accounts.length; i++) {
            newAccounts[i] = this.accounts[i];
        }
        this.accounts = newAccounts;
    }

    // -------------------------------------   
    // Returns bankName. No input.
    public String getBankName() {
        return bankName;
    }
    // Returns number of accounts with the bank. No input.
    public int getNumberOfAccounts() {
        return this.count;
    }
    // Returns number of transactions made. No input.
    public int getNumberOfTransactions() {
        return transactions.size();
    }

    // Combines all the elements to create the account.
    @Override
    public String toString() {
        String s = "Bank Name: " + bankName + " \n";
        s += "Number of Accounts: " + count + " | ";
        s += "Number of Pending Transactions: " + transactions.size() + "\n";
        s += "Accounts: \n";
        for (int i = 0; i < count; i++) {
            s += accounts[i].toString() + " \n";
        }
        return s;
    }
}
