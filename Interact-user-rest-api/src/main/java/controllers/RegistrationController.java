package controllers;

import entity.UserCredentials;
import entity.UserCredentialsDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import repository.UserRepository;

@RestController
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody UserCredentialsDto userCredentialsDto) {
        System.out.println("Hello!!!!!!!!!!!!");
        UserCredentials userCredentials = UserCredentials.builder()
                .enabled(true)
                .username(userCredentialsDto.getUsername())
                .password(passwordEncoder.encode(userCredentialsDto.getPassword()))
//                .email(userCredentialsDto.getEmail())
                .build();

        userRepository.save(userCredentials);
    }
}
