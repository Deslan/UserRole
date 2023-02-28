package za.flatrock.assessment.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.flatrock.assessment.demo.models.entities.Role;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CreateUserResponse {

    Long id;
    String name;
    String surname;
    String cellNumber;
    private List<Role> roles;

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
