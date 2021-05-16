package org.br.structmapper.dto;

public class ContatoDto {

	// 1. Atributos
	private String nome;

	public ContatoDto() {
		super();
	}

	// 2. Construtores
	public ContatoDto(String nome) {
		super();
		this.nome = nome;
	}

	public static ContatoDto of() {
		return new ContatoDto();
	}

	public static ContatoDto of(String nome) {
		return new ContatoDto(nome);
	}

	// 3. equals and hashCode

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		ContatoDto other = (ContatoDto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	// 4. toString

	@Override
	public String toString() {
		return String.format("ContatoDto(nome = %s)", nome);
	}

	// 5. getters e setters

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// 6. Fluent Interface

	public String nome() {
		return this.getNome();
	}

	public ContatoDto nome(String nome) {
		this.setNome(nome);
		return this;
	}

}
