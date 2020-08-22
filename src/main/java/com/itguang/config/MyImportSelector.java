package com.itguang.config;

import com.itguang.pojo.Blue;
import com.itguang.pojo.bean.component.Person;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据返回的 Class 全限定名 作为 Bean
 */
public class MyImportSelector implements ImportSelector {
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        ArrayList<Class> list = new ArrayList<Class>();
        list.add(Blue.class);
        list.add(Person.class);

        List<String> classes = list.stream().map(aClass -> aClass.getName())
                .collect(Collectors.toList());


        return classes.toArray(new String[classes.size()]);
    }
}
