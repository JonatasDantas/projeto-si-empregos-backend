package br.com.jowdev.projetosiplataformaempregos.controller.dto.user;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.ExperienceDto;
import br.com.jowdev.projetosiplataformaempregos.models.Role;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserPublicDto {


    private Long id;
    private String name;
    private String email;
    private String phone;
    private String profilePic;
    private List<UserKnowledgeDto> knowledges;
    private List<ExperienceDto> experience;
    private List<CertificateDto> certificates;
    private String city;
    private String state;
    private String biography;

    public UserPublicDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.profilePic = user.getProfilePic();
        this.knowledges = user
                .getKnowledges()
                .stream()
                .map(UserKnowledgeDto::new)
                .collect(Collectors.toList());
        this.state = user.getState();
        this.city = user.getCity();
        this.biography = user.getBiography();
        this.certificates = user
                .getCertificates()
                .stream()
                .map(CertificateDto::new)
                .collect(Collectors.toList());

        this.experience = user
                .getExperience()
                .stream()
                .map(ExperienceDto::new)
                .collect(Collectors.toList());
    }

}
