package authentication;

import entity.UserCredentials;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

@Service
@Transactional
public class DatabaseUserDetailsPasswordService implements UserDetailsPasswordService {

    private final UserRepository userRepository;
    private final UserDetailsMapper userDetailsMapper;

    public DatabaseUserDetailsPasswordService(UserRepository userRepository, UserDetailsMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        UserCredentials userCredentials =
                userRepository.findByUserName(userDetails.getUsername());
        userCredentials.setPassword(newPassword);
        return userDetailsMapper.toUserDetails(userCredentials);
    }
}
