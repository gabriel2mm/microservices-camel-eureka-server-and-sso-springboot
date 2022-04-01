package br.com.spring.batch.exemple.domain;

import lombok.*;

import java.util.Date;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private String name;

    private String email;

    private Date birthDate;

    private int idade;

    private int id;

}
