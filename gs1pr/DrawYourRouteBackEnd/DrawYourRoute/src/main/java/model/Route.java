package model;


import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "route")
public class Route{

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
    
    @Column(name = "score", nullable = false)
    @Expose
    private Double score;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "id_user", foreignKey = @ForeignKey(name = "fk_route_to_user"))
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Coordinate> coordinates;
    
    @ManyToMany(mappedBy="likes",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> likes = new HashSet<User>();

    public int getId() {
        return id;
    }
    
    @ManyToOne()
    @JoinColumn(nullable = false, name = "id_draw", foreignKey = @ForeignKey(name = "fk_route_to_draw"))
    private Draw draw;

    public Route() {

    }

    public Route(String name, Date date, Double score) {
        this.name = name;
        this.date = date;
        this.score = score;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public void setDraw(Draw draw) {
        this.draw = draw;
    }

    public Set<User> getLikes() {
        return likes;
    }
    
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
    
}
