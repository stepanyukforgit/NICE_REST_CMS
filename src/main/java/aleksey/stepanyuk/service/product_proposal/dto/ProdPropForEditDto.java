package aleksey.stepanyuk.service.product_proposal.dto;

import aleksey.stepanyuk.domain.entity.Attribute;
import aleksey.stepanyuk.domain.entity.Photo;
import aleksey.stepanyuk.service.product.dto.ProdForListDto;
import lombok.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class ProdPropForEditDto {

    private Long id;

    private Map<Locale, String> name;

    private Long price;

    private String vendorCode;

    private Map<Locale, String> description;

    private Double sale;

    private Boolean availability;

    private ProdForListDto product;

    private List<Attribute> attributes;

    private List<Photo> photos;
}