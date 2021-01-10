
package model;

import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "draw")
public class Draw {
    
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
    @JoinColumn(nullable = false, name = "id_user", foreignKey = @ForeignKey(name = "fk_draw_to_user"))
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "draw", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Coordinate> coordinates;
    
    @OneToMany(mappedBy = "draw", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Route> routes;
    
    public Draw() {
    }
    
    public Draw(String name, Date date, User user, List<Coordinate> coordinates) {
        this.name = name;
        this.date = date;
        this.user = user;
        this.coordinates = coordinates;
    }

    

    public int getVersion() {
        return version;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Draw{" + "version=" + version + ", id=" + id + ", name=" + name + ", date=" + date + ", user=" + user + '}';
    }
    
    
   
    
}
