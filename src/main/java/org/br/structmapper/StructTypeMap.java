package org.br.structmapper;

import static org.br.structmapper.StructMapperMessages.MESSAGE_NO_CLASS_OF_SOURCE_OBJECTS_WAS_FOUND;
import static org.br.structmapper.StructMapperMessages.MESSAGE_NO_CLASS_OF_TARGET_OBJECTS_WAS_FOUND;
import static org.br.structmapper.StructMapperMessages.MESSAGE_NO_MAPPING_FUNCTION_WAS_FOUND;
import static org.br.structmapper.StructMapperMessages.MESSAGE_NO_SOURCE_LIST_WAS_FOUND;
import static org.br.structmapper.StructMapperMessages.MESSAGE_NO_SOURCE_OBJECT_WAS_FOUND;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StructTypeMap<S, T> {

	private String id;
	private Class<S> sourceClass;
	private Class<T> targetClass;
	private Function<S, T> mapping;

	public StructTypeMap(Class<S> sourceClass, Class<T> targetClass) throws StructMapperException {
		setSourceClass(sourceClass);
		setTargetClass(targetClass);
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

	public void setMapping(Function<S, T> mapping) throws StructMapperException {
		if (mapping == null) {
			throw new StructMapperException(MESSAGE_NO_MAPPING_FUNCTION_WAS_FOUND);
		}
		this.mapping = mapping;
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

	public T map(S sourceObject, Function<S, T> mapping) throws StructMapperException {
		validateSourceObject(sourceObject);
		setMapping(mapping);
		return (T) mapping.apply(sourceObject);
	}
	
	public T map(S sourceObject) throws StructMapperException {
		validateSourceObject(sourceObject);
		return mapping == null ? null : (T) mapping.apply(sourceObject);
	}

	public List<T> map(List<S> sourceList, Function<S, T> mapping) throws StructMapperException {
		validateSourceList(sourceList);
		setMapping(mapping);
		List<T> targetList = Collections.emptyList();
		if (!Objects.isNull(sourceList) && !sourceList.isEmpty()) {
			targetList = (List<T>) sourceList
					.stream()
					.map(sourceObject -> mapping.apply(sourceObject))
					.collect(Collectors.toList());
		}
		return targetList;
	}
	
	public List<T> map(List<S> sourceList) throws StructMapperException {
		validateSourceList(sourceList);
		return this.map(sourceList, mapping);
	}
	
	private void setSourceClass(Class<S> sourceClass) throws StructMapperException {
		if (sourceClass == null) {
			throw new StructMapperException(MESSAGE_NO_CLASS_OF_SOURCE_OBJECTS_WAS_FOUND);
		}
		this.sourceClass = sourceClass;
	}
	
	private void setTargetClass(Class<T> targetClass) throws StructMapperException {
		if (targetClass == null) {
			throw new StructMapperException(MESSAGE_NO_CLASS_OF_TARGET_OBJECTS_WAS_FOUND);
		}
		this.targetClass = targetClass;
	}
	
	private void validateSourceObject(S sourceObject) throws StructMapperException {
		if (sourceObject == null) {
			throw new StructMapperException(MESSAGE_NO_SOURCE_OBJECT_WAS_FOUND);
		}
	}
	
	private void validateSourceList(List<S> sourceList) throws StructMapperException {
		if (sourceList == null) {
			throw new StructMapperException(MESSAGE_NO_SOURCE_LIST_WAS_FOUND);
		}
	}

	public static <S, T> String generateId(Class<S> sourceClass, Class<T> targetClass) {
		return String.format("(%s, %s)", sourceClass.getName(), targetClass.getName());
	}

}
