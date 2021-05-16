package org.br.structmapper;

import org.br.structmapper.dto.ContatoDto;
import org.br.structmapper.model.Contato;
import org.junit.Test;

public class StructMapperTest {
	@Test
	public void testObjectMapper() {

		Contato contato = new Contato();
		contato.setNome("Thiago Alexandre");
		contato.setSobrenome("Martins Monteiro");

		StructMapper structMapper = new StructMapper();

		ContatoDto contatoDto = structMapper
				.createTypeMap(Contato.class, ContatoDto.class)
				.map(contato, c -> ContatoDto.of().nome(c.nome()));
		System.out.println(contatoDto);

		ContatoDto contatoDto2 = structMapper.map(contato, ContatoDto.class);
		System.out.println(contatoDto2);
	}
}
