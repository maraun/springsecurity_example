package kz.bitlab.StartSpringR.models;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @author Assylkhan
 * on 9.09.2019
 * @project StartSpringR
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active")
    private Boolean active;


    @PrePersist
    public void prePersist() {
        this.active = true;
    }


}
