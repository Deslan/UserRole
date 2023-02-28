package za.flatrock.assessment.demo.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.flatrock.assessment.demo.models.RoleENUM;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Enumerated(EnumType.STRING)
    private RoleENUM role;

}
