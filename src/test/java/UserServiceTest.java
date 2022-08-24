import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import com.revature.water_world.services.UserService;
import com.revature.water_world.daos.*;
import static org.mockito.Mockito.mock;


public class UserServiceTest {

    private UserService user;
    private final AccountDAO mockAcDAO = mock(AccountDAO.class);

    @Before
    public void setup(){
        user = new UserService(mockAcDAO);
    }

    @Test
    public void test_IsValidPassword_GivenGoodPassword(){
        //Arrange
        String validPass = "Revature90@";

        //Act
        boolean flag = user.isValidPassword(validPass);

        //Assert
        Assert.assertTrue(flag);

    }
    @Test
    public void test_isValidPassword_GivenBadPass(){
        String notValid = "badPass";
        boolean flag = user.isValidPassword(notValid);
        Assert.assertFalse(flag);
    }

    @Test
    public void test_isValidUsername_GivenGood(){
        String valid = "Matthew88";
        boolean flag = user.isValidUsername(valid);
        Assert.assertTrue(flag);
    }

    @Test
    public void test_isValidUsername_GivenBad(){
        String bad = "dave";
        boolean flag = user.isValidUsername(bad);
        Assert.assertFalse(flag);
    }

    @Test
    public void test_isSamePassword_GivenBad(){
        String pass = "Revature123@";
        String pass2 = "Revatuer33#";
        boolean flag = user.isSamePassword(pass,pass2);
        Assert.assertFalse(flag);
    }

    @Test
    public void test_isSamePassword_GivenGood(){
        String pass = "Revature123@";
        String pass2 = "Revature123@";
        boolean flag = user.isSamePassword(pass,pass2);
        Assert.assertTrue(flag);
    }
}
