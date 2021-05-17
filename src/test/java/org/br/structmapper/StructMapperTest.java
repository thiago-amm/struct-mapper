package org.br.structmapper;

import org.br.structmapper.dto.ContatoDto;
import org.br.structmapper.model.Contato;
import org.junit.Test;

public class StructMapperTest {
	@Test
	public void testObjectMapper() {

		Contato contato = new Contato();
		contato.setNome("Thiago");
		contato.setSobrenome("Monteiro");
		
		ContatoDto contatoDto = new ContatoDto();
		contatoDto.setNome("Alexandre");

		StructMapper structMapper = new StructMapper();

		contatoDto = structMapper
				.createTypeMap(Contato.class, ContatoDto.class)
				.map(contato, c -> ContatoDto.of().nome(c.nome()));
		System.out.println(contatoDto);

		contatoDto = structMapper.map(contato, ContatoDto.class);
		System.out.println(contatoDto);
		
		contato = structMapper
			.createTypeMap(ContatoDto.class, Contato.class)
			.map(contatoDto, dto -> Contato.of().nome(dto.nome()).sobrenome("MARTINS MONTEIRO"));
		System.out.println(contato);
	}
}
