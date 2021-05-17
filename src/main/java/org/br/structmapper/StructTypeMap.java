package org.br.structmapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StructTypeMap<S, T> {

	// TODO 1. Validar e tratar NPE (NullPointerException) nos parâmetros de todos os métodos,
    //         se necessário lançar StructMapperException.
	// TODO 2. Criar enumeração com mensagens de erro da biblioteca.
	// TODO 3. Criar mé todo que retorna o nome do par de classes que está sendo mapeado.
	// TODO 4. Validar se o mapeamento entre classes de objetos de origem e destino (StructTypeMap) 
	//         foi criado antes de invocar o método map().

	private String id;
	private Class<S> sourceClass;
	private Class<T> targetClass;
	private Function<S, T> mapping;

	public StructTypeMap(Class<S> sourceClass, Class<T> targetClass) {
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
		this.id = generateId(sourceClass, targetClass);
	}

	public Class<S> getSourceClass() {
		return sourceClass;
	}

	public Class<T> getTargetClass() {
		return targetClass;
	}

	public String getId() {
		return id;
	}

	public Function<S, T> getMapping() {
		return this.mapping;
	}

	public StructTypeMap<S, T> setMapping(Function<S, T> mapping) {
		this.mapping = mapping;
		return this;
	}

	public Class<S> sourceClass() {
		return this.getSourceClass();
	}

	public Class<T> targetClass() {
		return this.getTargetClass();
	}

	public String id() {
		return this.getId();
	}

	public Function<S, T> mapping() {
		return this.getMapping();
	}

	public StructTypeMap<S, T> mapping(Function<S, T> mapping) {
		this.setMapping(mapping);
		return this;
	}

	public T map(S sourceObject, Function<S, T> mapping) {
		this.setMapping(mapping);
		return (T) mapping.apply(sourceObject);
	}

	public List<T> map(List<S> sourceList, Function<S, T> mapping) {
		this.setMapping(mapping);
		List<T> targetList = Collections.emptyList();
		if (!Objects.isNull(sourceList) && !sourceList.isEmpty()) {
			targetList = (List<T>) sourceList
					.stream()
					.map(sourceObject -> mapping.apply(sourceObject))
					.collect(Collectors.toList());
		}
		return targetList;
	}

	public static <S, T> String generateId(Class<S> sourceClass, Class<T> targetClass) {
		return String.format("(%s, %s)", sourceClass.getName(), targetClass.getName());
	}

}
