package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Entity @Table(name = "product_category")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class ProductCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    @CollectionTable(name = "product_category_names", joinColumns = {
            @JoinColumn(name = "name_id", referencedColumnName = "id")})
    private Map<Locale, String> name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "productCategory")
    private Set<Product> products;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "product_category_banner",
            joinColumns = @JoinColumn(name = "product_category_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> bannerPhotos;
}
