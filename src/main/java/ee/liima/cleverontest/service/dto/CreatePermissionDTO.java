package ee.liima.cleverontest.service.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
public class CreatePermissionDTO {

    @NotNull
    @Size(min = 3, message = "Name can't be smaller than 3 characters!")
    String name;

}
