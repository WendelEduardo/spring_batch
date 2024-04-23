package com.springbatch.processadorvalidacao.dominio;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Cliente {

	@NotNull(message = "O nome não pode ser nulo")
	@Size(min = 1, max = 100, message = "O nome não pode ultrapassar 100 caracteres e nem possuir menos de 1 caracter")
	@Pattern(regexp = "[a-zA-Z\\s]+", message = "Nome deve ser alfabético")
	private String nome;

	@NotNull(message = "A idade não pode ser nula")
	@Range(min = 18, max = 110, message = "A idade não pode ser menor que 18 e nem maior que 110")
	private Integer idade;

	@NotNull(message = "O email não pode ser nulo")
	@Size(min = 1, max = 50, message = "O email não pode ter menos que 1 caractere e nem mais de 50 caracteres")
	@Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email inválido")
	private String email;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Cliente{" +
                "nome='" + nome + "'" +
                ", idade='" + idade + "'" +
                ", email='" + email + "'" +
                '}';
	}
}
