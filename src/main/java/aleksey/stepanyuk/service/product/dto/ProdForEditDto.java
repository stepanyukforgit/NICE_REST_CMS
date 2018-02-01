package aleksey.stepanyuk.service.product.dto;

import aleksey.stepanyuk.domain.entity.Comment;
import aleksey.stepanyuk.domain.entity.Photo;
import aleksey.stepanyuk.service.product_category.dto.ProdCatForListDto;
import aleksey.stepanyuk.service.manufacturer.dto.ManufForListDto;
import aleksey.stepanyuk.service.product_proposal.dto.ProdPropForListDto;
import lombok.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class ProdForEditDto {

    private Long id;

    private Map<Locale, String> name;

    private Map<Locale, String> description;

    private ProdCatForListDto productCategory;

    private ManufForListDto manufacturer;

    private Photo photo;

    private List<Comment> comments;

    private List<ProdPropForListDto> productProposals;
}