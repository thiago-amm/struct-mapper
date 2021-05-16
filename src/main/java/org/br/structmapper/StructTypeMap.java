package org.br.structmapper;

import java.util.function.Function;

public class StructTypeMap<S, T> {

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

	public static <S, T> String generateId(Class<S> sourceClass, Class<T> targetClass) {
		return String.format("(%s, %s)", sourceClass.getName(), targetClass.getName());
	}

}
