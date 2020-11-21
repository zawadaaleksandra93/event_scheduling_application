package com.projekt.event_scheduling_application.model;

import com.projekt.event_scheduling_application.dao.Team;
import com.projekt.event_scheduling_application.dao.TeamRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserForm {

    @NotNull
    @Email
  //  @UniqueElements
    private String login;

    @NotNull
    @Length(min = 8, max = 30)
    private String password;

    private String confirmPassword;

    @NotBlank
    @Length(max = 50)
    private String nick;

    @NotNull
    private TeamRole teamRole;

    @NotNull
    private List<Team> team;


    @AssertTrue
    public boolean isPasswordValid(){
            return password !=null && password.equals(confirmPassword);
    }



}
