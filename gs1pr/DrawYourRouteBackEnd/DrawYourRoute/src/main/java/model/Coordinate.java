package model;

import javax.persistence.*;

@Entity
@Table(name = "coordinate")
public class Coordinate {

    @Version
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coordinate")
    private int id_coordinate;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_route", foreignKey = @ForeignKey(name = "fk_id_route_coordinate"))
    private Route route;

}
