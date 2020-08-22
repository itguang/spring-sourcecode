package com.itguang.factorybean;

import com.itguang.pojo.bean.component.Person;
import org.springframework.beans.factory.FactoryBean;

public class PersonFactoryBean implements FactoryBean<Person> {
    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Person getObject() throws Exception {

        System.out.println("---> com.itguang.factorybean.PersonFactoryBean.getObject()");

        Person person = new Person();
        person.setName("张三");
        person.setAge(18);
        return person;
    }

    @Override
    public Class<Person> getObjectType() {
        return Person.class;
    }
}
