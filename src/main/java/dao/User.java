package dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "users")
public class User {

    @Id
    @NotNull
    @Email
    @Column(name = "login")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "name_to_reflect")
    private String nick;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus userStatus;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_to_role",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "login"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "name"))
    private List<Role> roles = new ArrayList<>();
}
