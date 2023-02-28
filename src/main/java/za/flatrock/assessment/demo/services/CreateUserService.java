package za.flatrock.assessment.demo.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.flatrock.assessment.demo.exceptions.InternalApplicationException;
import za.flatrock.assessment.demo.models.dto.CreateUserRequest;
import za.flatrock.assessment.demo.models.dto.CreateUserResponse;
import za.flatrock.assessment.demo.models.dto.DeleteUserResponse;
import za.flatrock.assessment.demo.models.entities.Role;
import za.flatrock.assessment.demo.models.entities.User;
import za.flatrock.assessment.demo.repository.RoleRepository;
import za.flatrock.assessment.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public CreateUserResponse create(CreateUserRequest createUserRequest) {

        boolean validCellNo = validateCellNumber(createUserRequest.getCellNumber());

        if (!validCellNo) {
            throw new InternalApplicationException("Cell Number no valid", "Enter a valid cell number");
        }

        Role role = roleRepository.findByRole(createUserRequest.getRole());
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        long id = UUID.randomUUID().getMostSignificantBits();
        User user = userRepository.saveAndFlush(new User(
                id,
                createUserRequest.getName(),
                createUserRequest.getSurname(),
                createUserRequest.getCellNumber(),
                roles
        ));

        User userByNameAndSurnameAndCellNumber = userRepository.findUserByNameAndSurnameAndCellNumber(user.getName(), user.getSurname(), user.getCellNumber());
        CreateUserResponse response = new CreateUserResponse();
        response.setId(userByNameAndSurnameAndCellNumber.getId());
        response.setName(userByNameAndSurnameAndCellNumber.getName());
        response.setSurname(userByNameAndSurnameAndCellNumber.getSurname());
        response.setCellNumber(userByNameAndSurnameAndCellNumber.getCellNumber());

        return response;
    }

    public DeleteUserResponse delete(Long id) {
        DeleteUserResponse response = new DeleteUserResponse();
        response.setSuccess(false);
        try {
            User user = userRepository.findByLongId(id);
            userRepository.delete(user);
            response.setSuccess(true);
        } catch (Exception e) {
            log.error("Something went wrong when deleting user.", e.getMessage());
        }
        return response;
    }

    private boolean validateCellNumber(String cellNumber) {
        Pattern pattern = Pattern.compile("\\+27\\d\\d\\d\\d\\d\\d\\d\\d\\d$");
        Matcher matcher = pattern.matcher(cellNumber);
        return matcher.matches();
    }

    @Transactional
    public DeleteUserResponse modifyUserRole(Long id, Long roleId) {
        userRepository.updateUserWithRoleId(id, roleId);
        DeleteUserResponse response = new DeleteUserResponse();
        response.setSuccess(true);
        return response;
    }

    public CreateUserResponse findUserByNameAndSurnameAndCellNumber(String name, String surname, String cellNumber) {
        CreateUserResponse response = new CreateUserResponse();
        try {
            User user = userRepository.findUserByNameAndSurnameAndCellNumber(name, surname, cellNumber);
            response.setId(user.getId());
            response.setName(user.getName());
            response.setSurname(user.getSurname());
            response.setCellNumber(user.getCellNumber());
            List<Role> roles = user.getRoles();
            response.setRoles(roles);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalApplicationException("Could not find user", e.getMessage());
        }
        return response;
    }
}
