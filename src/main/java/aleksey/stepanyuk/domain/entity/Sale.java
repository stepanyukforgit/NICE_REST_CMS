package aleksey.stepanyuk.domain.entity;

import lombok.*;

import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Sale {
    private Long id;
    private String description;
    private Photo photo;
    private Set<Product> products;
}