package aleksey.stepanyuk.service.product.dto;

import aleksey.stepanyuk.domain.entity.Photo;
import lombok.*;

import java.util.Locale;
import java.util.Map;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class ProdForListDto {

    private Long id;

    private Map<Locale, String> name;

    private Map<Locale, String> manufacturerName;

    private Photo photo;
}