/*--------------------------------------------------------------------------
GWU CSCI 1112 Spring 2026
author: Akram Atassi, Charles Peeke 
--------------------------------------------------------------------------*/

public class Transaction {
    public int accountNumber;                     // Number associated with the account
    private int operation;                        // Number of the operation made         
    private double amount;                        // Balance associated with the account

    // Reads the input operation the user wants to make on the account,
    // does it on the account found using input acctNumber with the input content. No return.
    public Transaction(int acctNumber, int operation, String content) {
        // Set account number
        this.accountNumber = acctNumber;
        // Set operation
        // If the operation is 0, 1, or 2, set the operation and amount
        if (operation < 0 || operation > 2) {
            // If the operation is not 0, 1, or 2, translate the old operation
            this.operation = translateOldOperation(content);
        }
        else {
            this.operation = operation;
            // If we create an account, no need for amount  
            if (operation == 0) {
                this.amount = 0;
                return;
            }
            this.amount = Double.parseDouble(content);
        }
    }

    // Returns the account number. No input.
    public int getAccountNumber() {
        return accountNumber;
    }

    // Returns the value of the operation. No input.
    public int getOperation() {
        return operation;
    }

    // Returns the current balance of the account. No input.
    public double getAmount() {
        return amount;
    }

    // Finds the operation to make using input content. Returns the integer value of this operations.
    private int translateOldOperation(String content) {
        // Translate old operation to new operation
        Stack stack = new Stack();
        
        // Parse content by spaces and push all string elements to stack
        String[] contentArray = content.split(" ");
        for (int i = 0; i < contentArray.length; i++) {
            String current = contentArray[i];
            // If the current content is an operator, perform the operation
            // Addition
            if (current.equals("+")) {
                double right = Double.parseDouble(stack.pop());
                double left = Double.parseDouble(stack.pop());
                double result = left + right;
                String resultString = Double.toString(result);
                stack.push(resultString);
            }
            // Subtraction
            else if (current.equals("-")) {
                double right = Double.parseDouble(stack.pop());
                double left = Double.parseDouble(stack.pop());
                double result = left - right;
                String resultString = Double.toString(result);
                stack.push(resultString);
            }
            // Multiplication
            else if (current.equals("*")) {
                double right = Double.parseDouble(stack.pop());
                double left = Double.parseDouble(stack.pop());
                double result = left * right;
                String resultString = Double.toString(result);
                stack.push(resultString);
            }
            // Division
            else if (current.equals("/")) {
                double right = Double.parseDouble(stack.pop());
                double left = Double.parseDouble(stack.pop());
                double result = left / right;
                String resultString = Double.toString(result);
                stack.push(resultString);
            }
            else {
                stack.push(current);
            }
        }

        // The only element left in the stack should be the result
        double totalResult = Double.parseDouble(stack.pop());

        // If the result is positive, return 1 - Deposit
        if (totalResult > 0) {
            this.amount = totalResult;
            return 1;
        }
        // If the result is negative, return 2 - Withdraw
        else if (totalResult < 0) {
            this.amount = totalResult * -1;
            return 2;
        }
        // Otherwise, return -1 - Error
        return -1;
    }


    // Returns a string containing all the transactions made. No input.
    @Override
    public String toString() {
        String operationString = "";
        if (this.operation == 0) {
            operationString = "Create";
        }
        else if (this.operation == 1) {
            operationString = "Deposit";
        }
        else if (this.operation == 2) {
            operationString = "Withdraw";
        }
        else {
            operationString = "Error";
        }
        return "Transaction [accountNumber=" + accountNumber + ", operation=" + operationString + ", amount=" + amount + "]";
    }
}
