package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
    private Role role;

}
