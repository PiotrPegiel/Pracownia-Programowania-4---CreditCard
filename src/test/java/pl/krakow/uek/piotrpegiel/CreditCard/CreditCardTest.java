package pl.krakow.uek.piotrpegiel.CreditCard;

import org.junit.jupiter.api.Test;
import pl.krakow.uek.piotrpegiel.CreditCard.CreditCard;
import pl.krakow.uek.piotrpegiel.CreditCard.exceptions.*;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCreditLimit(){
        CreditCard card = new CreditCard();

        card.assignCreditLimit(BigDecimal.valueOf(1000));

        assertEquals(
                BigDecimal.valueOf(1000),
                card.getBalance()
        );
    }

    @Test
    void itDenyCreditLimitBelowThreshold(){
        CreditCard card = new CreditCard();

        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCreditLimit(BigDecimal.valueOf(99))
        );
    }

    @Test
    void itDenyToAssignCreditLimitAgain(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        assertThrows(
                CreditAssignedAgainException.class,
                () -> card.assignCreditLimit(BigDecimal.valueOf(500))
        );
    }

    @Test
    void itAllowsToReassignCredit(){
        CreditCard card = new CreditCard();

        card.assignCreditLimit(BigDecimal.valueOf(1000));
        card.reassignCreditLimit(BigDecimal.valueOf(500));

        assertEquals(
                BigDecimal.valueOf(500),
                card.getBalance()
        );
    }

    @Test
    void itDenyToReassignCreditBelowThreshold(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.reassignCreditLimit(BigDecimal.valueOf(50))
        );
    }

    @Test
    void itAllowsToWithdraw(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        card.withdraw(BigDecimal.valueOf(1000));
        assertEquals(
                BigDecimal.valueOf(0),
                card.getBalance()
        );
    }

    @Test
    void itDenyToWithdrawOverLimit(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(200));

        assertThrows(
                WithdrawalOverLimitException.class,
                () -> card.withdraw(BigDecimal.valueOf(1000))
        );
    }

    @Test
    void itDenyToWithdrawOverBalance(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(200));
        card.withdraw(BigDecimal.valueOf(100));

        assertThrows(
                NotEnoughMoneyException.class,
                () -> card.withdraw(BigDecimal.valueOf(101))
        );
    }

    @Test
    void itDenyWithdrawalOverMaximumNumberOfTransactions(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        assertThrows(
                TooManyTransactionsException.class,
                () -> {
                    for(int i=0; i<11; i++){
                        card.withdraw(BigDecimal.valueOf(1));
                    }
                }
        );
    }
}
