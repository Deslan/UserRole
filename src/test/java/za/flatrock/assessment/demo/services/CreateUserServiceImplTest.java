package za.flatrock.assessment.demo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.flatrock.assessment.demo.models.dto.CreateUserResponse;
import za.flatrock.assessment.demo.models.entities.User;
import za.flatrock.assessment.demo.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserServiceImplTest {

    public static final String NAME = "Deslan";
    public static final String SURNAME = "Pillay";
    public static final String CELL_NUMBER = "+27731832705";
    @InjectMocks
    CreateUserService createUserService;

    @Mock
    UserRepository mockUserRepository;

    @Test
    void testFindUserByNameAndSurnameAndCellNumber() {
        User user = new User();
        user.setName(NAME);
        user.setSurname(SURNAME);
        user.setCellNumber(CELL_NUMBER);

        when(mockUserRepository.findUserByNameAndSurnameAndCellNumber(NAME, SURNAME, CELL_NUMBER)).thenReturn(user);

        CreateUserResponse foundUser = createUserService.findUserByNameAndSurnameAndCellNumber(NAME, SURNAME, CELL_NUMBER);
        assertEquals(NAME, foundUser.getName());
        assertEquals(SURNAME, foundUser.getSurname());
        assertEquals(CELL_NUMBER, foundUser.getCellNumber());
    }
}
