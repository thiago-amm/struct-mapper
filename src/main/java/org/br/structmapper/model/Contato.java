package org.br.structmapper.model;

public class Contato {

	// 1. Atributos
	private String nome;
	private String sobrenome;
	private String email;

	// 2. Construtores
	public Contato() {
		super();
	}

	public Contato(String nome, String sobrenome, String email) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
	}
	
	public static Contato of() {
		return new Contato();
	}
	
	public static Contato of(String nome, String sobrenome, String email) {
		return new Contato(nome, sobrenome, email);
	}

	// 3. equals e hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	// 4. toString
	
	@Override
	public String toString() {
		return String.format("Contato(nome = %s, sobrenome = %s, email = %s", nome, sobrenome, email);
	}
	
	// 5. getters e setters

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	// 6. Fluent Interface
	
	public String nome() {
		return this.getNome();
	}
	
	public Contato nome(String nome) {
		this.setNome(nome);
		return this;
	}
	
	public String sobrenome() {
		return this.getSobrenome();
	}
	
	public Contato sobrenome(String sobrenome) {
		this.setSobrenome(sobrenome);
		return this;
	}
	
	public String email() {
		return this.getEmail();
	}
	
	public Contato email(String email) {
		this.setEmail(email);
		return this;
	}

}