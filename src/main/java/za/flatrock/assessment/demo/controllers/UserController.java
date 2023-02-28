package za.flatrock.assessment.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.flatrock.assessment.demo.models.dto.CreateUserRequest;
import za.flatrock.assessment.demo.models.dto.CreateUserResponse;
import za.flatrock.assessment.demo.models.dto.DeleteUserResponse;
import za.flatrock.assessment.demo.services.CreateUserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CreateUserService createUserService;

    @Autowired
    public UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PatchMapping(value = "/create")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        return createUserService.create(createUserRequest);
    }

    @PostMapping(value = "/delete/{id}")
    public DeleteUserResponse deleteUser(@PathVariable Long id) {
        return createUserService.delete(id);
    }

    @PostMapping(value = "/modify/{id}/{roleId}")
    public DeleteUserResponse modifyUserRole(@PathVariable Long id, @PathVariable Long roleId) {
        return createUserService.modifyUserRole(id, roleId);
    }

    @PostMapping(value = "/findUser/{name}/{surname}/{cellNumber}")
    public CreateUserResponse findUserByNameAndSurnameAndCellNumber(@PathVariable String name, @PathVariable String surname, @PathVariable String cellNumber) {
        return createUserService.findUserByNameAndSurnameAndCellNumber(name, surname, cellNumber);
    }
}
