package model;


import com.google.gson.annotations.Expose;
import java.util.Date;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "route")
public class Route {

    @Version
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Expose
    private int id;

    @Column(name = "name", nullable = false)
    @Expose
    private String name;
    
    @Column(name = "date", nullable = false)
    @Expose
    private Date date;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "id_user", foreignKey = @ForeignKey(name = "fk_route_to_user"))
    private User user;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private List<Coordinate> coordinates;

    public Route() {

    }

    public Route(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
