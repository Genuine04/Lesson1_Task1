package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Integer id;

    @NotNull(message = "CorpName can not be empty")
    private String corpName;

    @NotNull(message = "DirectorName can not be empty")
    private String directorName;

//    @NotNull(message = "Address can not be empty")
    private AddressDto addressDto;

}
