package kz.bitlab.StartSpringR.models;

import lombok.*;

import javax.persistence.*;

/**
 * @author Assylkhan
 * on 9.09.2019
 * @project StartSpringR
 */
@Entity
@Table(name = "items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "category_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_items_categories")
    )
    private Category category;

}
