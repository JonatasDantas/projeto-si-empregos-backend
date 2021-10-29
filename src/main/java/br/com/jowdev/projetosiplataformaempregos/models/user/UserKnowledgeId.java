package br.com.jowdev.projetosiplataformaempregos.models.user;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserKnowledgeId implements Serializable {
    Long user;
    Long knowledge;
}
