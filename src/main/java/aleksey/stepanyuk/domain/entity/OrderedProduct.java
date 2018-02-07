package aleksey.stepanyuk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

@Entity @Table(name = "ordered_product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = OrderedProduct.class)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class OrderedProduct implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    @CollectionTable(name = "ordered_product_names", joinColumns = {
            @JoinColumn(name = "ordered_product_id", referencedColumnName = "id")})
    private Map<Locale, String> name;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "manuf", columnDefinition = "VARCHAR(100)")
    @CollectionTable(name = "ordered_product_manufs", joinColumns = {
            @JoinColumn(name = "ordered_product_id", referencedColumnName = "id")})
    private Map<Locale, String> manufacturerName;

    @Column(name = "quantity", columnDefinition = "BIGINT default 1")
    private Long quantity;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "prop", columnDefinition = "VARCHAR(50)", nullable = false)
    @CollectionTable(name = "ordered_product_props", joinColumns = {
            @JoinColumn(name = "ordered_product_id", referencedColumnName = "id")})
    private Map<Locale, String> proposalName;

    @Column(name = "vendor_code", columnDefinition = "VARCHAR(8)", nullable = false)
    private String vendorCode;

    @Column(name = "price", columnDefinition = "BIGINT", nullable = false)
    private Long price;

    @Column(name = "sale", columnDefinition = "DECIMAL(4,2)")
    private Double sale;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "attribute", columnDefinition = "VARCHAR(50)")
    @CollectionTable(name = "ordered_product_attributes", joinColumns = {
            @JoinColumn(name = "ordered_product_id", referencedColumnName = "id")})
    private Map<Locale, String> attributes;
}