package za.flatrock.assessment.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.flatrock.assessment.demo.models.RoleENUM;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {

    private String name;
    private String surname;
    private String cellNumber;
    private RoleENUM role;

}
