package za.flatrock.assessment.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class InternalApplicationException extends RuntimeException {

    String description;
    String message;

}
