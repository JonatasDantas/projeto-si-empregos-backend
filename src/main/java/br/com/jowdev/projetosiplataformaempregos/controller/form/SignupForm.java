package br.com.jowdev.projetosiplataformaempregos.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.jowdev.projetosiplataformaempregos.models.Role;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import br.com.jowdev.projetosiplataformaempregos.models.user.UserGender;
import br.com.jowdev.projetosiplataformaempregos.repository.RoleRepository;

@Builder
@Data
public class SignupForm {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    private UserGender gender;

    @NotNull
    @NotEmpty
    private String cpf;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    private boolean recruiter;

    @NotNull
    private String state;

    @NotNull
    private String city;

    public User convert(RoleRepository roleRepository) {
        List<Role> roles;

        if (isRecruiter()) {
            roles = roleRepository.findByName("ROLE_RECRUITER");
        } else {
            roles = roleRepository.findByName("ROLE_USER");
        }

        String name = firstName.trim() + " " + lastName.trim();
        String hashedPassword = new BCryptPasswordEncoder().encode(password);

        return User.builder()
                .cpf(cpf)
                .city(city)
                .state(state)
                .email(email)
                .emailVerified(false)
                .gender(gender)
                .name(name)
                .password(hashedPassword)
                .profilePic("")
                .phone(phone)
                .roles(roles)
                .companies(new ArrayList<>())
                .experience(new ArrayList<>())
                .jobApplications(new ArrayList<>())
                .knowledges(new ArrayList<>())
                .certificates(new ArrayList<>())
                .build();
    }


}
