package com.thoughtworks.mingle.mylyn.core;

import java.util.Date;

import org.eclipse.mylyn.tasks.core.AbstractAttributeFactory;

public class MingleAttributeFactory extends AbstractAttributeFactory {

    public Date getDateForAttributeType(String attributeKey, String dateString) {
        return new Date();

    }

    public String getName(String key) {
    	return key + " - MingleAttributeFactory.getName()";

    }

    public boolean isHidden(String key) {
        return false;
    }

    public boolean isReadOnly(String key) {
    	return true;
    }

    public String mapCommonAttributeKey(String key) {
        return key + " - MingleAttributeFactory.mapCommonAttributeKey()";
    }

}
