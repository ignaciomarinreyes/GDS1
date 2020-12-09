package model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

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
    
    @Column(name = "lastName", nullable = false)
    @Expose
    private String lastName;
    
    @Column(name = "nickName", nullable = false, unique=true)
    @Expose
    private String nickName;
    
    @Column(name = "password", nullable = false)
    @Expose
    private String password;
    
    @Column(name = "email", nullable = false)
    @Expose
    private String email;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Route> routes;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "friend", joinColumns = @JoinColumn(name = "id_user_1"),
            inverseJoinColumns = @JoinColumn(name = "id_user_2"),
            foreignKey = @ForeignKey(name = "fk_friend_to_user_1"),
            inverseForeignKey = @ForeignKey(name = "fk_friend_to_user_2"))
    private Set<User> friends;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "like_", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_route"),
            foreignKey = @ForeignKey(name = "fk_like_to_user"),
            inverseForeignKey = @ForeignKey(name = "fk_like_to_route"))
    private Set<Route> likes;

    public User() {

    }
    
    public User(int id, String name, String lastName, String nickName, String password, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
    }

    public User(String name, String lastName, String nickName, String password, String email) {
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", lastName=" + lastName + ", nickName=" + nickName + ", password=" + password + ", email=" + email + '}';
    }
}
