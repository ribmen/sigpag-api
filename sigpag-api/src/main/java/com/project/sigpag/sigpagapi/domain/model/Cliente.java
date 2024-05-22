package com.project.sigpag.sigpagapi.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Table(name = "tb_cliente")
public class Cliente {

    @EqualsAndHashCode.Include //equals e hashcode gerados apenas com base na propriedade Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //usar a forma nativa do banco de dados (no caso o auto increment) para gerar os Ids
    private Long id;

    //@Column(name = "name")
    @NotBlank //depois, (message = "blablabla")
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    @Column(name = "phone")
    private String telefone;
}
