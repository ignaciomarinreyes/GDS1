package model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Version
    private int version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id_user;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Route> routes;

    @ManyToMany(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "amigos", joinColumns = @JoinColumn(name = "id_user1"),
            inverseJoinColumns = @JoinColumn(name = "id_user2"),
            foreignKey = @ForeignKey(name = "fk_id_user1_amigos"),
            inverseForeignKey = @ForeignKey(name = "fk_id_user2_amigos"))
    List< User> amigos;

    @ManyToMany(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "likes", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_route"),
            foreignKey = @ForeignKey(name = "fk_id_user_likes"),
            inverseForeignKey = @ForeignKey(name = "fk_id_route_likes"))
    List< Route> likes;

    public User() {

    }

    public User(Integer id_user) {
        this.id_user = id_user;
    }

    public User(String nombre) {
        this.nombre = nombre;
    }

    public User(Integer id_user, String nombre) {
        this.id_user = id_user;
        this.nombre = nombre;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", nombre='" + nombre + '\'' + ", routes=" + routes + '}';
    }
}
