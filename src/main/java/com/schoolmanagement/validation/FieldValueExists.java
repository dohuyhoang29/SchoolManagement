package com.schoolmanagement.validation;

public interface FieldValueExists {

  boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}
