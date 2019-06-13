import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sup.user.User;
import sup.user.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:base-customer.xml")
@SpringBootTest
public class BSPCutomer {

    @Autowired
    private UserService userService;

    @Test
    public void contentLoad(){
        User user = userService.getUserById(1);
        // Display the call result
        System.out.println(user);
    }
}
