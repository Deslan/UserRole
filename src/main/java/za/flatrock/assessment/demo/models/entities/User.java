package za.flatrock.assessment.demo.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@IdClass(UserId.class)
@Data
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Id
    public String name;
    @Id
    public String surname;
    @Id
    public String cellNumber;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="USER_ROLE",
            joinColumns = {@JoinColumn (name="USER_ID", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="ROLE_ID", referencedColumnName="id")}
    )
    private List<Role> roles;
}
