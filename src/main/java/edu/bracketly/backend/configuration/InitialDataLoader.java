package edu.bracketly.backend.configuration;

import edu.bracketly.backend.model.entity.user.User;
import edu.bracketly.backend.model.entity.user.UserDetails;
import edu.bracketly.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (userRepository.existsByUsername("test")) return;
        User test = new User();
        test.setUsername("test");
        test.setPassword(passwordEncoder.encode("test"));
        UserDetails userDetails = new UserDetails();
        test.setDetails(userDetails);
        userRepository.save(test);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
