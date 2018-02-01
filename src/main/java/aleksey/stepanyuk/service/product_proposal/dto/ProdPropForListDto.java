package aleksey.stepanyuk.service.product_proposal.dto;

import lombok.*;

import java.util.Locale;
import java.util.Map;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class ProdPropForListDto {

    private Long id;

    private Map<Locale, String> name;

    private String vendorCode;

    private Boolean availability;
}