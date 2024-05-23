package org.tutu.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于传递类的属性
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        return propertyValueList.stream().filter(item -> item.getName().equals(propertyName)).findFirst().orElse(null);
    }
}
