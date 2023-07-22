package karandin.rest.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
