package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Integer id;

    @NotNull(message = "Street can not be empty")
    private String street;

    @NotNull(message = "HomeNumber can not be empty")
    private Integer homeNumber;
}
