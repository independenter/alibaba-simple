package sup.user;

public interface UserService {

    public User getUserById(int id);

    public User getUserByName(java.lang.String username);

    public void regiserUser(User userInfo);
}
