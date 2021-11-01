package br.com.jowdev.projetosiplataformaempregos.models.Job;

import lombok.Data;

import java.io.Serializable;

@Data
public class JobApplicationId implements Serializable {
    private Long user;
    private Long job;
}
