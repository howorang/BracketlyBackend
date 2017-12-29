package edu.bracketly.backend.configuration;

import edu.bracketly.backend.model.entity.user.Player;
import edu.bracketly.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
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
        Player test = new Player();
        test.setUsername("test");
        test.setPassword(passwordEncoder.encode("test"));
        ;
        userRepository.save(test);

        Player howo = new Player();
        howo.setUsername("howo");
        howo.setPassword(passwordEncoder.encode("test"));
        userRepository.save(howo);
    }

}
