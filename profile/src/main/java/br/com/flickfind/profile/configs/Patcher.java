package br.com.flickfind.profile.configs;

import br.com.flickfind.profile.domain.filter.Filter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {
    public void internPatcher(Filter existingFilter, Filter incompleteFilter) throws IllegalAccessException {

        Class<?> filterClass= Filter.class;
        Field[] filterFields=filterClass.getDeclaredFields();
        System.out.println(filterFields.length);
        for(Field field : filterFields){
            System.out.println(field.getName());
            field.setAccessible(true);
            Object value=field.get(incompleteFilter);
            if(value!=null){
                field.set(existingFilter,value);
            }
            field.setAccessible(false);
        }

    }
}
