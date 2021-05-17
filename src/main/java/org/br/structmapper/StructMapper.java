package org.br.structmapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StructMapper {
	
	// TODO 1. Validar e tratar NPE (NullPointerException) nos parâmetros de todos os métodos,
    //         se necessário lançar StructMapperException.
	// TODO 2. Criar enumeração com mensagens de erro da biblioteca.
	// TODO 3. Criar mé todo que retorna o nome do par de classes que está sendo mapeado.
	// TODO 4. Validar se o mapeamento entre classes de objetos de origem e destino (StructTypeMap) 
	//         foi criado antes de invocar o método map().

	private Map<String, StructTypeMap<?, ?>> typeMaps;

	private void configure() {
		typeMaps = new HashMap<>();
	}

	public StructMapper() {
		super();
		configure();
	}

	public static StructMapper of() {
		return new StructMapper();
	}

	@SuppressWarnings("unchecked")
	public <S, T> StructTypeMap<S, T> createTypeMap(Class<S> sourceClass, Class<T> targetClass) {
		StructTypeMap<S, T> typeMap = new StructTypeMap<>(sourceClass, targetClass);
		if (!typeMaps.containsKey(typeMap.getId())) {
			typeMaps.put(typeMap.getId(), typeMap);
		}
		typeMap = (StructTypeMap<S, T>) typeMaps.get(typeMap.getId());
		return typeMap;
	}

	public <S, T> StructTypeMap<S, T> typeMap(Class<S> sourceClass, Class<T> targetClass) {
		return createTypeMap(sourceClass, targetClass);
	}

	@SuppressWarnings("unchecked")
	public <S, T> T map(S sourceObject, Class<T> targetClass) {
		String key = StructTypeMap.generateId(sourceObject.getClass(), targetClass);
		if (typeMaps.containsKey(key)) {
			StructTypeMap<S, T> typeMap = (StructTypeMap<S, T>) typeMaps.get(key);
			return typeMap.getMapping().apply(sourceObject);
		}
		return null;
	}

	public <S, T> List<T> map(List<S> sourceList, Class<T> targetClass) {
		return sourceList
				.stream()
				.map(sourceObject -> this.map(sourceObject, targetClass))
				.collect(Collectors.toList());
	}

}
