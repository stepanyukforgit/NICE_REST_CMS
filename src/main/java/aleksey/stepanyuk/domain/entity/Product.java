package aleksey.stepanyuk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Entity @Table(name = "product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Product.class)
@Getter @Setter @NoArgsConstructor @ToString(exclude = "productProposals") @EqualsAndHashCode(of = "id")
public class Product implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    @CollectionTable(name = "product_names", joinColumns = {
            @JoinColumn(name = "name_id", referencedColumnName = "id")})
    private Map<Locale, String> name;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "description", columnDefinition = "TEXT")
    @CollectionTable(name = "product_descriptions", joinColumns = {
            @JoinColumn(name = "description_id", referencedColumnName = "id")})
    private Map<Locale, String> description;

    //    todo add Lazy!!!
    @ManyToOne
    @JoinColumn(name = "product_category", nullable = false)
    private ProductCategory productCategory;

    //    todo add Lazy!!!
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @ElementCollection
    @CollectionTable(name = "comment", joinColumns = @JoinColumn(name = "product_id"))
    private List<Comment> comments;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductProposal> productProposals;
}