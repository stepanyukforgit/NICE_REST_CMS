package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "orders")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @Embedded
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<OrderedProduct> orderedProducts;
}

//todo don't forget complete Carrier!