package org.projet_iwa.auth.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.projet_iwa.auth.api.model.User;
import org.projet_iwa.auth.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static  org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Save user")
    void save() {
        User user = userRepository.save(SampleData.getSampleUser());

        assertThat(user.getEmail()).isEqualTo(SampleData.getSampleUser().getEmail());
        assertThat(user.getUsername()).isEqualTo(SampleData.getSampleUser().getUsername());
        assertThat(user.getPhone_number()).isEqualTo(SampleData.getSampleUser().getPhone_number());
        assertThat(user.getLast_name()).isEqualTo(SampleData.getSampleUser().getLast_name());
        assertThat(user.getFirst_name()).isEqualTo(SampleData.getSampleUser().getFirst_name());

        userRepository.deleteById(user.getUser_id());
    }

    @Test
    @DisplayName("Find user by username")
    public void findByUsername(){
        userRepository.save(SampleData.getSampleUser());
        User user = userRepository.findByUsername(SampleData.getSampleUser().getUsername()).orElse(null);

        assert user != null;
        assertThat(user.getEmail()).isEqualTo(SampleData.getSampleUser().getEmail());
        assertThat(user.getUsername()).isEqualTo(SampleData.getSampleUser().getUsername());
        assertThat(user.getPhone_number()).isEqualTo(SampleData.getSampleUser().getPhone_number());
        assertThat(user.getLast_name()).isEqualTo(SampleData.getSampleUser().getLast_name());
        assertThat(user.getFirst_name()).isEqualTo(SampleData.getSampleUser().getFirst_name());

        userRepository.deleteById(user.getUser_id());
    }

    @Test
    @DisplayName("Find user by username")
    public void existsUser(){
        User user = userRepository.save(SampleData.getSampleUser());

        boolean exist = userRepository.existsByUsername(SampleData.getSampleUser().getUsername());
        assertThat(exist).isTrue();
        exist = userRepository.existsByEmail(SampleData.getSampleUser().getEmail());
        assertThat(exist).isTrue();

        userRepository.deleteById(user.getUser_id());
    }

    // Do the same with the rest ....
}
