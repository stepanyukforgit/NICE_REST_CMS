package aleksey.stepanyuk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Entity @Table(name = "product_proposal")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProductProposal.class)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class ProductProposal implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    @CollectionTable(name = "product_proposal_names", joinColumns = {
            @JoinColumn(name = "name_id", referencedColumnName = "id")})
    private Map<Locale, String> name;

    @Column(name = "price", columnDefinition = "BIGINT", nullable = false)
    private Long price;

    //todo make unique?
    @Column(name = "vendor_code", columnDefinition = "VARCHAR(8)", nullable = false)
    private String vendorCode;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "description", columnDefinition = "TEXT")
    @CollectionTable(name = "product_proposal_descriptions", joinColumns = {
            @JoinColumn(name = "description_id", referencedColumnName = "id")})
    private Map<Locale, String> description;

    @Column(name = "sale", columnDefinition = "DECIMAL(4,2)")
    private Double sale;

    @Column(name = "availability", columnDefinition = "BOOLEAN")
    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "product_proposal_attribute",
            joinColumns = @JoinColumn(name = "product_proposal_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id"))
    private List<Attribute> attributes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "product_proposal_photos",
            joinColumns = @JoinColumn(name = "product_proposal_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos;
}