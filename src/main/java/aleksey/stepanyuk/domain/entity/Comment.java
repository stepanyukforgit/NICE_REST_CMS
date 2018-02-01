package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(exclude = "text")
public class Comment implements Serializable{

    @Column(name = "author", columnDefinition = "VARCHAR(50)", nullable = false)
    private String author;

    @Column(name = "text", columnDefinition = "VARCHAR(255)", nullable = false)
    private String text;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date date;
}