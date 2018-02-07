package aleksey.stepanyuk.service.product_proposal.dto;

import aleksey.stepanyuk.domain.entity.Attribute;
import lombok.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class ProdPropForOrderDto {

    private Map<Locale, String> name;

    private Long price;

    private String vendorCode;

    private Double sale;

    private Boolean availability;

    private List<Attribute> attributes;
}