package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Integer id;

    @NotNull(message = "Name can not be empty")
    private String name;

    private CompanyDto companyDto;

}
