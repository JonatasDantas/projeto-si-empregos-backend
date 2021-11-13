package br.com.jowdev.projetosiplataformaempregos.models.user.form;

import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import br.com.jowdev.projetosiplataformaempregos.models.user.KnowledgeLevel;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import br.com.jowdev.projetosiplataformaempregos.models.user.UserKnowledge;
import br.com.jowdev.projetosiplataformaempregos.repository.KnowledgeRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.UserKnowledgeRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UserKnowledgeForm {

    @NotNull
    Long knowledgeId;

    @NotNull
    KnowledgeLevel knowledgeLevel;

    public UserKnowledge persist(UserKnowledgeRepository userKnowledgeRepository, User user) {


        final val knowledge = new Knowledge();
        knowledge.setId(knowledgeId);

        final val userKnowledge = new UserKnowledge();
        userKnowledge.setKnowledge(knowledge);
        userKnowledge.setUser(user);
        userKnowledge.setKnowledgeLevel(knowledgeLevel);

        return userKnowledgeRepository.save(userKnowledge);

    }

}
