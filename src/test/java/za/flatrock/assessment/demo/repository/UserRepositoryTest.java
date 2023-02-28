package za.flatrock.assessment.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import za.flatrock.assessment.demo.models.RoleENUM;
import za.flatrock.assessment.demo.models.entities.Role;
import za.flatrock.assessment.demo.models.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testAdd() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, RoleENUM.ADMIN));
        User user = new User(new Random().nextLong(), "Deslan", "Pillay", "+27731832705", roles);
        userRepository.save(user);
        List<User> result = new ArrayList<>(userRepository.findAll());
        assertEquals(3, result.size());
    }

    @Test
    void testDelete() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, RoleENUM.ADMIN));
        User user = new User(new Random().nextLong(), "Deslan", "Pillay", "+27731832705", roles);
        userRepository.save(user);

        userRepository.delete(user);
        List<User> resultAgain = new ArrayList<>(userRepository.findAll());
        assertEquals(2, resultAgain.size());

    }

    @Test
    void testFindLongId() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, RoleENUM.ADMIN));
        User user = new User(new Random().nextLong(), "Deslan", "Pillay", "+27731832705", roles);
        userRepository.save(user);

        User byLongId = userRepository.findByLongId(user.getId());
        assertEquals(user.getId(), byLongId.getId());
    }

    @Test
    void testFindUserByNameAndSurnameAndCellNumber() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, RoleENUM.ADMIN));
        User user = new User(new Random().nextLong(), "Deslan", "Pillay", "+27731832705", roles);
        userRepository.save(user);

        User newUser = userRepository.findUserByNameAndSurnameAndCellNumber(user.getName(), user.getSurname(), user.getCellNumber());
        assertEquals(user.getName(), newUser.getName());
        assertEquals(user.getSurname(), newUser.getSurname());
        assertEquals(user.getCellNumber(), newUser.getCellNumber());
    }

    @Test
    void testUpdateUserRole() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, RoleENUM.ADMIN));
        User user = new User(new Random().nextLong(), "Deslan", "Pillay", "+27731832705", roles);
        userRepository.save(user);

        userRepository.updateUserWithRoleId(user.getId(), user.getRoles().get(0).getId());
    }
}
