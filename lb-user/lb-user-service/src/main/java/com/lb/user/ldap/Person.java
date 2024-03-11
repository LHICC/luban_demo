package com.lb.user.ldap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(base = "cn=dev,ou=luban,dc=example,dc=com", objectClasses = "inetOrgPerson")
public class Person {

    @Id
    @JsonIgnore // 必写
    private Name id;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "sn")
    private String suerName;

    @Attribute(name = "userPassword")
    private String userPassword;

    @Attribute(name = "uidNumber")
    private String uidNumber;
}