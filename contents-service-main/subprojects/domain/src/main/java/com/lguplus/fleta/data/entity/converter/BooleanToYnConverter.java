package com.lguplus.fleta.data.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToYnConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {

        return attribute != null && attribute.booleanValue() ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {

        return Boolean.valueOf("Y".equals(dbData));
    }
}
