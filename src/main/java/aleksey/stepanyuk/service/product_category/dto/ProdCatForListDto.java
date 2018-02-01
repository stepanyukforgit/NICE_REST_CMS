package aleksey.stepanyuk.service.product_category.dto;

import aleksey.stepanyuk.domain.entity.Photo;
import lombok.*;

import java.util.Locale;
import java.util.Map;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class ProdCatForListDto {

    private Long id;

    private Map<Locale, String> name;

    private Photo photo;
}