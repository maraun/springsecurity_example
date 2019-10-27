package kz.bitlab.StartSpringR.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * @author Assylkhan
 * on 5.09.2019
 * @project StartSpringR
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "users_roles",
            joinColumns =
                    {
                            @JoinColumn(
                                    name = "user_id",
                                    nullable = false,
                                    foreignKey = @ForeignKey(name = "fk_users_roles_users")
                            )
                    },
            inverseJoinColumns =
                    {
                            @JoinColumn(
                                    name = "role_id",
                                    nullable = false,
                                    foreignKey = @ForeignKey(name = "fk_users_roles_roles")
                            )
                    }
    )
    private Set<Role> roles;
}
