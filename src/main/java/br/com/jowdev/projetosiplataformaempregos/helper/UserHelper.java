package br.com.jowdev.projetosiplataformaempregos.helper;

import br.com.jowdev.projetosiplataformaempregos.models.User;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserHelper {

    public boolean isRecruiter(User user) {
        return user.getRoles().stream().anyMatch(e -> e.getName().toLowerCase(Locale.ROOT).equals("role_recruiter"));
    }

}
