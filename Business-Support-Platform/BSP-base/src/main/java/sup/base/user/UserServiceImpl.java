package sup.base.user;

import org.apache.dubbo.config.annotation.Service;
import sup.user.User;
import sup.user.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(int id) {
        return new User(1,"donghui","e10adc3949ba59abbe56e057f20f883e","董辉","m15501951002@163.com");
    }


    @Override
    public User getUserByName(String username) {
        return new User(1,"donghui","e10adc3949ba59abbe56e057f20f883e","董辉","m15501951002@163.com");
    }

    @Override
    public void regiserUser(User userInfo) {
        System.out.println("注册用户成功");
    }
}
