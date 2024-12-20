package ma.eventcraft.services;
import ma.eventcraft.models.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    boolean deleteUser(Long id);
}
