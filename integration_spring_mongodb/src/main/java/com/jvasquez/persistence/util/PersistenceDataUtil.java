package com.jvasquez.persistence.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricco on 30/08/2015.
 */
public class PersistenceDataUtil<T> {

    public static List generateList(List listDTO, Class aClass) {
        List listClazzPersistence = new ArrayList();

        for (Object objectDTO : listDTO) {

            try {
                listClazzPersistence.add(transformObject(aClass.newInstance(), objectDTO));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return listClazzPersistence;
    }

    public static <T> T transformObject( T objectPersistence, Object initialObject) {


        if (initialObject != null) {

            try {

                for (PropertyDescriptor propertyDescriptor : PropertyUtils.getPropertyDescriptors(initialObject)) {
                    if (!propertyDescriptor.getName().equals("class")) {
                        PropertyUtils.setProperty(objectPersistence, propertyDescriptor.getName(), PropertyUtils.getProperty(initialObject, propertyDescriptor.getName()));
                    }

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return objectPersistence;
    }
}
