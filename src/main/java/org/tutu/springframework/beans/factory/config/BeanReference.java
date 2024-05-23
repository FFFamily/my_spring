package org.tutu.springframework.beans.factory.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BeanReference {
    private String beanName;
}
