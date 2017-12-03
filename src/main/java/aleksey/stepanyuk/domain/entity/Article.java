package aleksey.stepanyuk.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Entity @Table(name = "article")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class Article implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "title", columnDefinition = "VARCHAR(200)", nullable = false)
    @CollectionTable(name = "article_titles", joinColumns = {
            @JoinColumn(name = "title_id", referencedColumnName = "id")})
    private Map<Locale, String> title;

    @ElementCollection
    @MapKeyColumn(name = "language", columnDefinition = "VARCHAR(2)")
    @Column(name = "text", columnDefinition = "TEXT", nullable = false)
    @CollectionTable(name = "article_texts", joinColumns = {
            @JoinColumn(name = "text_id", referencedColumnName = "id")})
    private Map<Locale, String> text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date date;
}