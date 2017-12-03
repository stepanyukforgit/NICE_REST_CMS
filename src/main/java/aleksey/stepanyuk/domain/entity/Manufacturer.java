package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Locale;
import java.util.Map;

@Entity @Table(name = "manufacturer")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class Manufacturer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    @CollectionTable(name = "manufacturer_names", joinColumns = {
            @JoinColumn(name = "name_id", referencedColumnName = "id")})
    private Map<Locale, String> name;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "description", columnDefinition = "TEXT")
    @CollectionTable(name = "manufacturer_descriptions", joinColumns = {
            @JoinColumn(name = "description_id", referencedColumnName = "id")})
    private Map<Locale, String> description;

    @OneToMany(mappedBy = "manufacturer")
    private java.util.Set<Product> products;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private Photo photo;
}