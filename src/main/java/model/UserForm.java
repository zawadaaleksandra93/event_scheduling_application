package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserForm {

    @NotNull
    @Email
    @UniqueElements
    private String login;

    @NotNull
    @Length(min = 8, max = 30)
    private String password;

    private String confirmPassword;

    @NotBlank
    @Length(max = 50)
    private String nick;


    @AssertTrue
    public boolean isPasswordValid(){
            return password !=null && password.equals(confirmPassword);
    }



}
