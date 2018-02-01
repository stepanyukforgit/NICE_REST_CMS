package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Entity @Table(name = "carrier")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class Carrier implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    @CollectionTable(name = "carrier_names", joinColumns = {
            @JoinColumn(name = "name_id", referencedColumnName = "id")})
    private Map<Locale, String> name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrier")
    private Set<Order> orders;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private Photo photo;
}