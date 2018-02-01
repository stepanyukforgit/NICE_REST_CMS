package aleksey.stepanyuk.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Entity @Table(name = "photo")
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(of = "id")
public class Photo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link", columnDefinition = "VARCHAR(255)", nullable = false)
    private String link;

    @Column(name = "prev_link", columnDefinition = "VARCHAR(255)")
    private String prevLink;

    @PostRemove
    private void onPostRemove(){
        try {
            Files.delete(Paths.get(link));
            Files.delete(Paths.get(prevLink));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}