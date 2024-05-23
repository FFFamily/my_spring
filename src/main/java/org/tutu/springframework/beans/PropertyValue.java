package org.tutu.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyValue {
    private String name;
    private Object value;
}
