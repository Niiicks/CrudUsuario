package com.neuwirt.orange.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull 
	@Column(unique = true)
	@Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "Favor seguir o padrão 000.000.000-00")
	private String cpf;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "usuario")
	@JsonIgnoreProperties("usuario")
	private List<Endereco> enderecos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> endereco) {
		this.enderecos = endereco;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}