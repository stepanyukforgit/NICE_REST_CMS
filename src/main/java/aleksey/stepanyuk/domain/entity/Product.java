package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity @Table(name = "product")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_category")
    private ProductCategory productCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "product_photos",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private Set<Photo> photos;

    @ElementCollection
    @CollectionTable(name = "comment", joinColumns = @JoinColumn(name = "product_id"))
    private List<Comment> comments;

    @OneToMany(mappedBy = "product")
    private Set<ProductProposal> proposals;
}
