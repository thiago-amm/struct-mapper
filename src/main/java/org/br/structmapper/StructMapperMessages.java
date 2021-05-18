package org.br.structmapper;

public enum StructMapperMessages {

	MESSAGE_NO_CLASS_OF_SOURCE_OBJECTS_WAS_FOUND("No class of source objects was found to be mapped."),
	MESSAGE_NO_CLASS_OF_TARGET_OBJECTS_WAS_FOUND("No class of target objects was found to be mapped."),
	MESSAGE_NO_SOURCE_OBJECT_WAS_FOUND("No source object was found."),
	MESSAGE_NO_MAPPING_FUNCTION_WAS_FOUND("No mapping function was found."),
	MESSAGE_NO_SOURCE_LIST_WAS_FOUND("No source object was found.");

	private final String text;

	private StructMapperMessages(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public String text() {
		return this.getText();
	}

}
