package com.lb.user.ldap;

import com.lb.user.ldap.Person;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

public interface PersonRepository extends CrudRepository<Person, Name> {

}