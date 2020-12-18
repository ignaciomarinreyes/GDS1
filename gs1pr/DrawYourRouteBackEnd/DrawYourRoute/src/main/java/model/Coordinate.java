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

    @Column(name = "latitude", nullable = false)
    @Expose
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    @Expose
    private Double longitude;

    @ManyToOne()
    @JoinColumn(nullable = true, referencedColumnName="id", name = "id_route", foreignKey = @ForeignKey(name = "fk_coordinate_to_route"))
    private Route route;
    
    @ManyToOne()
    @JoinColumn(nullable = true, referencedColumnName="id", name = "id_draw", foreignKey = @ForeignKey(name = "fk_coordinate_to_draw"))
    private Draw draw;

    public Coordinate() {
    }

    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinate(Double latitude, Double longitude, Route route, Draw draw) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.route = route;
        this.draw = draw;
    }
 

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setDraw(Draw draw) {
        this.draw = draw;
    }
    
    
}
