package sup.user;

public class UserServiceMock implements UserService {
    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public void regiserUser(User userInfo) {

    }
}
