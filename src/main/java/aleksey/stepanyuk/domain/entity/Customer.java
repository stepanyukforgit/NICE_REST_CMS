package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class Customer {

    @Column(name = "cust_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String customerName;

    @Column(name = "cust_address", columnDefinition = "VARCHAR(200)", nullable = false)
    private String customerAddress;

    @Column(name = "cust_phone", columnDefinition = "CHAR(15)", nullable = false)
    private String customerPhone;

    @Column(name = "cust_email", columnDefinition = "VARCHAR(30)")
    private String customerEmail;
}