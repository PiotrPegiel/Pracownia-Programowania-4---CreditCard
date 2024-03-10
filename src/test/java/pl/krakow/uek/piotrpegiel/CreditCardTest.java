package pl.krakow.uek.piotrpegiel;

import org.junit.jupiter.api.Test;
import pl.krakow.uek.piotrpegiel.exceptions.CreditAssignedAgain;
import pl.krakow.uek.piotrpegiel.exceptions.CreditBelowThresholdException;
import pl.krakow.uek.piotrpegiel.exceptions.NotEnoughMoney;
import pl.krakow.uek.piotrpegiel.exceptions.WithdrawalOverLimit;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCreditLimit(){
        CreditCard card = new CreditCard();

        card.assignCreditLimit(BigDecimal.valueOf(1000));

        assert BigDecimal.valueOf(1000).equals(card.getBalance());
    }

    @Test
    void itDenyCreditLimitBelowThreshold(){
        CreditCard card = new CreditCard();

        try {
            card.assignCreditLimit(BigDecimal.valueOf(99));
            fail("Should throw exception");
        } catch (CreditBelowThresholdException e){
            assertTrue(true);
        }
    }

    @Test
    void itDenyToAssignCreditLimitAgain(){
        CreditCard card = new CreditCard();

        card.assignCreditLimit(BigDecimal.valueOf(1000));
        try {
            card.assignCreditLimit(BigDecimal.valueOf(500));
            fail("Should throw exception");
        } catch (CreditAssignedAgain e){
            assertTrue(true);
        }
    }

    @Test
    void itAllowsToReassignCredit(){
        CreditCard card = new CreditCard();

        card.assignCreditLimit(BigDecimal.valueOf(1000));

        card.reassignCreditLimit(BigDecimal.valueOf(500));

        assert BigDecimal.valueOf(500).equals(card.getBalance());
    }

    @Test
    void itDenyToReassignCreditBelowThreshold(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        try {
            card.reassignCreditLimit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        } catch (CreditBelowThresholdException e){
            assertTrue(true);
        }
    }

    @Test
    void itAllowsToWithdraw(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        card.withdraw(BigDecimal.valueOf(100));
        assert BigDecimal.valueOf(900).equals(card.getBalance());
    }

    @Test
    void itDenyToWithdrawOverLimit(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(200));

        try {
            card.withdraw(BigDecimal.valueOf(1000));
            fail("Should throw exception");
        } catch (WithdrawalOverLimit e){
            assertTrue(true);
        }
    }

    @Test
    void itDenyToWithdrawOverBalance(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(200));
        card.withdraw(BigDecimal.valueOf(100));

        try {
            card.withdraw(BigDecimal.valueOf(101));
            fail("should throw exception");
        } catch (NotEnoughMoney e){
            assertTrue(true);
        }
    }

    @Test
    void itDenyOverMaximumNumberOfTransactions(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        try {
            for(int i=0; i<11; i++){
                card.withdraw(BigDecimal.valueOf(1));
            }
            fail("should throw exception");
        } catch (TooManyTransactions e){
            assertTrue(true);
        }
    }
}
