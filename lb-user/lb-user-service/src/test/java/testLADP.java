import com.lb.user.UserApplication;
import com.lb.user.ldap.Person;
import com.lb.user.ldap.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;

@SpringBootTest(classes = UserApplication.class)
public class testLADP {

    @Autowired
    LdapTemplate ldapTemplate;
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findAll() throws Exception {
        personRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void ladpLogin() {

        String username = "user_1";

        // 先判断用户名
        // LdapQuery query = LdapQueryBuilder.query().where("sAMAccountName").is("user_1");
        LdapQuery query = LdapQueryBuilder.query().where("commonName").is(username);
        // 如果没查到该用户名，则会抛出异常EmptyResultDataAccessException
        Person person = ldapTemplate.findOne(query, Person.class);

        // 再判断用户密码（利用ldap的认证）
        EqualsFilter filter = new EqualsFilter("commonName", username);
        if (!ldapTemplate.authenticate("", filter.toString(), username)) {
            System.out.println("用户密码错误！");
        }else {
            System.out.println("用户密码正确！");
        }
        System.out.println(person);
    }
}
