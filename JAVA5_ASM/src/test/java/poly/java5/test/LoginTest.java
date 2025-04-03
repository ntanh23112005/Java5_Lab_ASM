package poly.java5.test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

//import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

	private AccountServiceTestImpl accountService;

    @BeforeMethod
    void setUp() {
        accountService = new AccountServiceTestImpl(); // Khởi tạo service
    }

    @Test
    void testFindByUsernameAndPassword_Success() {
        UserTest user = accountService.findByUsernameAndPasswordTest("validUser", "validPass");
        assertNotNull(user, "User should be found");
        assertEquals(user.getUsername(), "validUser");
        assertEquals(user.getPassword(), "validPass");
    }

    @Test
    void testFindByUsernameAndPassword_Failure_InvalidPassword() {
        UserTest user = accountService.findByUsernameAndPasswordTest("validUser", "wrongPass");
        assertNull(user, "User should not be found with wrong password");
    }

    @Test
    void testFindByUsernameAndPassword_Failure_InvalidUsername() {
        UserTest user = accountService.findByUsernameAndPasswordTest("invalidUser", "validPass");
        assertNull(user, "User should not be found with invalid username");
    }

    @Test
    void testFindByUsernameAndPassword_Failure_BothInvalid() {
        UserTest user = accountService.findByUsernameAndPasswordTest("invalidUser", "wrongPass");
        assertNull(user, "User should not be found with invalid username and password");
    }
}
