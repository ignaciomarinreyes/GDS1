package model;

import javax.persistence.*;

@Entity
@Table(name = "coordinate")
public class Coordinate {

    @Version
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "id_route", foreignKey = @ForeignKey(name = "fk_coordinate_to_route"))
    private Route route;

    public Coordinate() {
    }

    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

}
