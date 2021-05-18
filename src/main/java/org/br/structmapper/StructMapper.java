package org.br.structmapper;

import static org.br.structmapper.StructMapperMessages.MESSAGE_NO_CLASS_OF_SOURCE_OBJECTS_WAS_FOUND;
import static org.br.structmapper.StructMapperMessages.MESSAGE_NO_CLASS_OF_TARGET_OBJECTS_WAS_FOUND;
import static org.br.structmapper.StructMapperMessages.MESSAGE_NO_SOURCE_LIST_WAS_FOUND;
import static org.br.structmapper.StructMapperMessages.MESSAGE_NO_SOURCE_OBJECT_WAS_FOUND;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	public <S, T> StructTypeMap<S, T> createTypeMap(Class<S> sourceClass, Class<T> targetClass) throws StructMapperException {
		validateSourceClass(sourceClass);
		validateTargetClass(targetClass);
		StructTypeMap<S, T> typeMap = new StructTypeMap<>(sourceClass, targetClass);
		if (!typeMaps.containsKey(typeMap.getId())) {
			typeMaps.put(typeMap.getId(), typeMap);
		} else {
			typeMap = (StructTypeMap<S, T>) typeMaps.get(typeMap.getId());	
		}
		return typeMap;
	}

	public <S, T> StructTypeMap<S, T> typeMap(Class<S> sourceClass, Class<T> targetClass) throws StructMapperException {
		return createTypeMap(sourceClass, targetClass);
	}

	@SuppressWarnings("unchecked")
	public <S, T> T map(S sourceObject, Class<T> targetClass) throws StructMapperException {
		validateSourceObject(sourceObject);
		validateTargetClass(targetClass);
		String key = StructTypeMap.generateId(sourceObject.getClass(), targetClass);
		if (typeMaps.containsKey(key)) {
			StructTypeMap<S, T> typeMap = (StructTypeMap<S, T>) typeMaps.get(key);
			return typeMap.getMapping().apply(sourceObject);
		}
		return null;
	}

	public <S, T> List<T> map(List<S> sourceList, Class<T> targetClass) {
		validateSourceList(sourceList);
		validateTargetClass(targetClass);
		return sourceList
				.stream()
				.map(sourceObject -> this.map(sourceObject, targetClass))
				.collect(Collectors.toList());
	}
	
	private <S> void validateSourceObject(S sourceObject) {
		if (sourceObject == null) {
			throw new StructMapperException(MESSAGE_NO_SOURCE_OBJECT_WAS_FOUND);
		}
	}
	
	private <S> void validateSourceClass(Class<S> sourceClass) {
		if (sourceClass == null) {
			throw new StructMapperException(MESSAGE_NO_CLASS_OF_SOURCE_OBJECTS_WAS_FOUND);
		}
	}
	
	private <T> void validateTargetClass(Class<T> targetClass) {
		if (targetClass == null) {
			throw new StructMapperException(MESSAGE_NO_CLASS_OF_TARGET_OBJECTS_WAS_FOUND);
		}
	}
	
	private <S> void validateSourceList(List<S> sourceList) {
		if (sourceList == null) {
			throw new StructMapperException(MESSAGE_NO_SOURCE_LIST_WAS_FOUND);
		}
	}

}
