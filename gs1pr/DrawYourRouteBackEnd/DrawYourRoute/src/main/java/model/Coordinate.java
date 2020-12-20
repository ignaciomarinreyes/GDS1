package model;

import com.google.gson.annotations.Expose;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "coordinate")
public class Coordinate {

    @Version
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Expose
    private int id;

    @Column(name = "lat", nullable = false)
    @Expose
    private Double lat;

    @Column(name = "lng", nullable = false)
    @Expose
    private Double lng;

    @ManyToOne()
    @JoinColumn(nullable = true, referencedColumnName="id", name = "id_route", foreignKey = @ForeignKey(name = "fk_coordinate_to_route"))
    private Route route;
    
    @ManyToOne()
    @JoinColumn(nullable = true, referencedColumnName="id", name = "id_draw", foreignKey = @ForeignKey(name = "fk_coordinate_to_draw"))
    private Draw draw;

    public Coordinate() {
    }

    public Coordinate(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Coordinate(Double lat, Double lng, Route route, Draw draw) {
        this.lat = lat;
        this.lng = lng;
        this.route = route;
        this.draw = draw;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setDraw(Draw draw) {
        this.draw = draw;
    }

}
