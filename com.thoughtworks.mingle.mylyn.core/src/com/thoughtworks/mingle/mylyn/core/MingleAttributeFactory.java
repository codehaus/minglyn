package com.thoughtworks.mingle.mylyn.core;

import java.util.Date;

import org.eclipse.mylyn.tasks.core.AbstractAttributeFactory;

public class MingleAttributeFactory extends AbstractAttributeFactory {

    public Date getDateForAttributeType(String attributeKey, String dateString) {
        return new Date();

    }

    public String getName(String key) {
        return key;
//        return key + " - getName";

    }

    public boolean isHidden(String key) {
        return false;
    }

    public boolean isReadOnly(String key) {
        return true;
    }

    public String mapCommonAttributeKey(String key) {
        return key;
//        if (key.equals(RepositoryTaskAttribute.SUMMARY)) {
//            return "Summary";
//        }
//        if (key.equals(RepositoryTaskAttribute.COMMENT_NEW)) {
//            return "New Comment";
//        }
//        if (key.equals(RepositoryTaskAttribute.DESCRIPTION)) {
//            return "Description";
//        }
//        if (key.equals(RepositoryTaskAttribute.STATUS)) {
//            return "Status";
//        }
//        if (key.equals(RepositoryTaskAttribute.PRIORITY)) {
//            return "Priority";
//        }
//        if (key.equals(RepositoryTaskAttribute.DATE_CREATION)) {
//            return "Creation Date";
//        }
//        if (key.equals(RepositoryTaskAttribute.DATE_MODIFIED)) {
//            return "Date Modified";
//        }
//        if (key.equals(RepositoryTaskAttribute.USER_ASSIGNED_NAME)) {
//            return "Assigned to";
//        }
//        if (key.equals(RepositoryTaskAttribute.USER_ASSIGNED)) {
//            return "Assigned to";
//        }
//        if (key.equals(RepositoryTaskAttribute.USER_REPORTER)) {
//            return "Reported by";
//        }
//        if (key.equals(RepositoryTaskAttribute.USER_REPORTER_NAME)) {
//            return "Reported by";
//        }
//        if (key.equals(RepositoryTaskAttribute.USER_CC)) {
//            return "CC";
//        }
//        if (key.equals(RepositoryTaskAttribute.ADD_SELF_CC)) {
//            return "Add self to CC";
//        }
//        return key + " - map";
    }

}

