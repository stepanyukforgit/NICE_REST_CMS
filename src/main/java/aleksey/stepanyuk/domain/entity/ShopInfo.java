package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Locale;
import java.util.Map;

@Entity @Table(name = "shopinfo")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class ShopInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "about", columnDefinition = "TEXT", nullable = false)
    @CollectionTable(name = "shopinfo_abouts", joinColumns = {
            @JoinColumn(name = "about_id", referencedColumnName = "id")})
    private Map<Locale, String> about;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "payment", columnDefinition = "TEXT", nullable = false)
    @CollectionTable(name = "shopinfo_payments", joinColumns = {
            @JoinColumn(name = "payment_id", referencedColumnName = "id")})
    private Map<Locale, String> payment;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "contactinfo", columnDefinition = "TEXT", nullable = false)
    @CollectionTable(name = "shopinfo_contactinfos", joinColumns = {
            @JoinColumn(name = "contactinfo_id", referencedColumnName = "id")})
    private Map<Locale, String> contactInfo;
}