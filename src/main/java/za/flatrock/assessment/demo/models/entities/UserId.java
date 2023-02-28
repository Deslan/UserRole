package za.flatrock.assessment.demo.models.entities;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Getter
@Setter
public class UserId implements Serializable {

    private String name;

    private String surname;

    private String cellNumber;
}
