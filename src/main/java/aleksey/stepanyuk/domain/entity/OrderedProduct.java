package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Entity @Table(name = "ordered_products")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class OrderedProduct {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(name = "vendor_code", columnDefinition = "VARCHAR(8)", nullable = false, unique = true)
    private String vendorCode;

    @Column(name = "manufacturer", columnDefinition = "VARCHAR(100)")
    private String manufacturer;

    @Column(name = "price", columnDefinition = "BIGINT", nullable = false)
    private Long price;

    @Column(name = "sale", columnDefinition = "DECIMAL(4,2)")
    private Double sale;

    @Column(name = "quantity", columnDefinition = "BIGINT default 1")
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "ordered_product_attributes", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "value")
    @MapKeyColumn(name = "attr_name", columnDefinition = "VARCHAR(50)")
    private Map<String, String> attributes;
}
