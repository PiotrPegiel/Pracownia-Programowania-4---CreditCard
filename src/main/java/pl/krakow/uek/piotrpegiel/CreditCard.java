package pl.krakow.uek.piotrpegiel;

import pl.krakow.uek.piotrpegiel.exceptions.CreditAssignedAgain;
import pl.krakow.uek.piotrpegiel.exceptions.CreditBelowThresholdException;
import pl.krakow.uek.piotrpegiel.exceptions.NotEnoughMoney;
import pl.krakow.uek.piotrpegiel.exceptions.WithdrawalOverLimit;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal balance;
    private BigDecimal creditLimit;
    private int numberOfTransactions = 0;
    private BigDecimal creditTreshold = new BigDecimal(100);
    private int maximumNumberOfTransactions = 10;
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
        if (this.creditLimit != null){
            throw new CreditAssignedAgain();
        }
        if (creditTreshold.compareTo(creditLimit) > 0){
            throw new CreditBelowThresholdException();
        }
        this.creditLimit = creditLimit;
        this.balance = this.creditLimit;
    }

    public void reassignCreditLimit(BigDecimal creditLimit){
        if (creditTreshold.compareTo(creditLimit) > 0){
            throw new CreditBelowThresholdException();
        }
        this.creditLimit = creditLimit;
        this.balance = this.creditLimit;
    }

    public void withdraw(BigDecimal money){
        if(getNumberOfTransactions() >= this.maximumNumberOfTransactions){
            throw new TooManyTransactions();
        }
        if(getCreditLimit().compareTo(money) < 0){
            throw new WithdrawalOverLimit();
        }
        if(getBalance().compareTo(money) < 0){
            throw new NotEnoughMoney();
        }
        this.balance = this.balance.subtract(money);
        this.numberOfTransactions ++;
    }
}
