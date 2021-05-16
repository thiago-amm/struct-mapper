package org.br.structmapper;

import java.util.HashMap;
import java.util.Map;

public class StructMapper {

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

}
