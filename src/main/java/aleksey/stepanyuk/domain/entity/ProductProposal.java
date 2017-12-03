package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "product_proposal")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
class ProductProposal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price", columnDefinition = "BIGINT", nullable = false)
    private Long price;

    @Column(name = "vendor_code", columnDefinition = "VARCHAR(8)", nullable = false, unique = true)
    private String vendorCode;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "sale", columnDefinition = "DECIMAL(4,2)")
    private Double sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productProposal", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Attribute> attributes;
}
