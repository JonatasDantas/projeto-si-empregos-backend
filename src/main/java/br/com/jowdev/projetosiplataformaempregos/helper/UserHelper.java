package br.com.jowdev.projetosiplataformaempregos.helper;

import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserHelper {

    public boolean isRecruiter(User user) {
        return user.getRoles().stream().anyMatch(e -> e.getName().toLowerCase(Locale.ROOT).equals("role_recruiter"));
    }

    public boolean isAdmin(User user) {
        return user.getRoles().stream().anyMatch(e -> e.getName().toLowerCase(Locale.ROOT).equals("role_admin"));
    }

    public boolean hasJob(User user, Long jobId) {
        return user.getCompanies().stream().anyMatch(company -> company.getJobs().stream().anyMatch(job -> job.getId().equals(jobId)));
    }

}
