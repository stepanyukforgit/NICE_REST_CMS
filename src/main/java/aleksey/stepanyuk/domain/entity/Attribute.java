package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

@Entity @Table(name = "attribute")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "name")
public class Attribute implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    @CollectionTable(name = "attribute_names", joinColumns = {
            @JoinColumn(name = "attribute_id", referencedColumnName = "id")})
    private Map<Locale, String> name;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "value", columnDefinition = "VARCHAR(50)")
    @CollectionTable(name = "attribute_values", joinColumns = {
            @JoinColumn(name = "attribute_id", referencedColumnName = "id")})
    private Map<Locale, String> value;
}