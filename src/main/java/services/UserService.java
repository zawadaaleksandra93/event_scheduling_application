package services;

import dao.User;
import lombok.RequiredArgsConstructor;
import model.UserForm;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService  {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(UserForm userForm) {
        final User user = userMapper.fromUserFormToUser(userForm);
        userRepository.save(user);

    }

    public User findByEmail(final String email) throws Exception {
        return userRepository.findById(email)
                .orElseThrow(() -> new Exception(String.format("User with email: %s does not exist", email)));
    }

    public User findByNick(final String nick) throws Exception{
        return userRepository.findById(nick)
                .orElseThrow(() -> new Exception(String.format("User with nick: %s does not exist", nick)));
    }


    public void deleteUser(final String email){
        userRepository.deleteById(email);
    }
}
