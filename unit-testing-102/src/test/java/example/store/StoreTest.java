package example.store;

import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

public class StoreTest {
    AccountManager accountManager = Mockito.mock(AccountManager.class);
    Store store = new StoreImpl(accountManager);
    Product product = new Product();
    Customer customer = new Customer();
    @Test
    void givenProductOutOfStock_whenBuy_thenFailPayment(){
        // Arrange
        product.setQuantity(0);

        try {
            // Act
            store.buy(product, customer);
            Assertions.fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            // Assert
            Assertions.assertEquals("Product out of stock", e.getMessage());
        }
    }
    @Test
    void givenProductInStockAndSuccessWithdrawFromCustomer_whenBuy_thenSuccessPayment(){
        // Arrange
        product.setQuantity(5);
        Mockito.when(accountManager.withdraw(Mockito.any(Customer.class), Mockito.anyInt()))
                .thenReturn("success");

        // Act
        store.buy(product,customer);

        // Assert
        Assertions.assertEquals(product.getQuantity(),4);
    }

    @Test
    void givenProductInStockAndFailedWithdrawFromCustomer_whenBuy_thenFailedPayment(){
        // Arrange
        product.setQuantity(5);
        Mockito.when(accountManager.withdraw(Mockito.any(Customer.class), Mockito.anyInt()))
                .thenReturn("fail");

        try {
            // Act
            store.buy(product, customer);
            Assertions.fail("Expected Payment failure was not thrown");
        } catch (RuntimeException e) {
            // Assert
            Assertions.assertEquals("Payment failure: fail", e.getMessage());
        }
    }
}
