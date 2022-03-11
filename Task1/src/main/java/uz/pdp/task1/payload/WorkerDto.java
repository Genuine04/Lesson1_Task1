package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    private Integer id;

    @NotNull(message = "Name can not be empty")
    private String name;

    @NotNull(message = "PhoneNumber can not be empty")
    private String phoneNumber;

    @NotNull(message = "Address can not be empty")
    private AddressDto addressDto;

    @NotNull(message = "Name can not be empty")
    private DepartmentDto departmentDto;

}
