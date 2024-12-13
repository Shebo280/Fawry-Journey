package example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {
    Customer customer = new Customer();
    AccountManager accountManager = new AccountManagerImpl();
    @Test
    void givenPositiveAmount_whenDeposit_thenUpdateBalance(){

        // Arrange
        int amount = 100;

        // Act
        accountManager.deposit(customer,amount);

        // Assert
        Assertions.assertEquals(customer.getBalance(),100);
    }

    @Test
    void givenNegativeAmount_whenDeposit_thenThrowAnError(){

        // Arrange
        int amount = -100;

        try{
            // Act
            accountManager.deposit(customer,amount);
            Assertions.fail("Expected RuntimeException was not thrown");
        }
        catch (RuntimeException e){
            // Assert
            Assertions.assertEquals("The deposit amount must be positive",e.getMessage());
        }

    }
    @Test
    void givenCustomerWithEnoughBalance_whenWithdraw_thenSuccess(){

        // Arrange
        customer.setBalance(600);

        //Act
        String result=accountManager.withdraw(customer,500);

        // Assert
        Assertions.assertEquals(customer.getBalance(),100);
        Assertions.assertEquals("success", result);
    }
    @Test
    void givenCustomerWithNoEnoughBalanceAndNoCreditAllowed_whenWithdraw_thenFail(){

        // Arrange
        customer.setBalance(400);
        customer.setCreditAllowed(false);

        //Act
        String result = accountManager.withdraw(customer,500);

        // Assert
        Assertions.assertEquals(400, customer.getBalance());
        Assertions.assertEquals("insufficient account balance", result);
    }
    @Test
    void givenCustomerWithNoEnoughBalanceAndDoesNotExceedCreditLimit_whenWithdraw_thenSuccess(){

        // Arrange
        customer.setBalance(400);
        customer.setCreditAllowed(true);

        //Act
        String result=accountManager.withdraw(customer,500);

        // Assert
        Assertions.assertEquals(customer.getBalance(),-100);
        Assertions.assertEquals("success", result);
    }
    @Test
    void givenCustomerWithNoEnoughBalanceAndExceedsCreditLimit_whenWithdraw_thenFail(){

        // Arrange
        customer.setBalance(400);
        customer.setCreditAllowed(true);

        //Act
        String result=accountManager.withdraw(customer,2000);

        // Assert
        Assertions.assertEquals(400, customer.getBalance());
        Assertions.assertEquals("maximum credit exceeded", result);
    }
    @Test
    void givenVIPCustomer_whenWithdraw_thenSuccess(){

        // Arrange
        customer.setCreditAllowed(true);
        customer.setVip(true);

        //Act
        String result=accountManager.withdraw(customer,2000);

        // Assert
        Assertions.assertEquals(customer.getBalance(),-2000);
        Assertions.assertEquals("success", result);
    }

}
