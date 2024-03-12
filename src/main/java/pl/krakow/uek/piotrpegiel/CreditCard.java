package pl.krakow.uek.piotrpegiel;

import pl.krakow.uek.piotrpegiel.exceptions.CreditAssignedAgainException;
import pl.krakow.uek.piotrpegiel.exceptions.CreditBelowThresholdException;
import pl.krakow.uek.piotrpegiel.exceptions.NotEnoughMoneyException;
import pl.krakow.uek.piotrpegiel.exceptions.WithdrawalOverLimitException;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal balance;
    private BigDecimal creditLimit;
    private int numberOfTransactions = 0;
    private final BigDecimal CREDIT_THRESHOLD = new BigDecimal(100);
    private final int MAXIMUM_NUMBER_OF_TRANSACTIONS = 10;
    public BigDecimal getBalance() {
        return this.balance;
    }

    public BigDecimal getCreditLimit(){
        return this.creditLimit;
    }

    public int getNumberOfTransactions(){
        return this.numberOfTransactions;
    }
    public void assignCreditLimit(BigDecimal creditLimit){
        if (isCreditAlreadyAssigned()){
            throw new CreditAssignedAgainException();
        }
        if (isCreditBelowThreshold(creditLimit)){
            throw new CreditBelowThresholdException();
        }
        this.creditLimit = creditLimit;
        this.balance = this.creditLimit;
    }

    private boolean isCreditAlreadyAssigned() {
        return this.creditLimit != null;
    }

    private boolean isCreditBelowThreshold(BigDecimal creditLimit) {
        return CREDIT_THRESHOLD.compareTo(creditLimit) > 0;
    }

    public void reassignCreditLimit(BigDecimal creditLimit){
        if (isCreditBelowThreshold(creditLimit)){
            throw new CreditBelowThresholdException();
        }
        this.creditLimit = creditLimit;
        this.balance = this.creditLimit;
    }

    public void withdraw(BigDecimal money){
        if(isNumberOfTransactionsBelowThreshold()){
            throw new TooManyTransactionsException();
        }
        if(isPaymentAboveCreditLimit(money)){
            throw new WithdrawalOverLimitException();
        }
        if(isBalanceBelowPayment(money)){
            throw new NotEnoughMoneyException();
        }
        this.balance = this.balance.subtract(money);
        this.numberOfTransactions ++;
    }

    private boolean isBalanceBelowPayment(BigDecimal money) {
        return getBalance().compareTo(money) < 0;
    }

    private boolean isPaymentAboveCreditLimit(BigDecimal money) {
        return getCreditLimit().compareTo(money) < 0;
    }

    private boolean isNumberOfTransactionsBelowThreshold() {
        return getNumberOfTransactions() >= this.MAXIMUM_NUMBER_OF_TRANSACTIONS;
    }
}
