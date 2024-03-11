// /*
//  * @ProjectName: 编程学习
//  * @Copyright:   2018 HangZhou Yiyuery Dev, Ltd. All Right Reserved.
//  * @address:     http://xiazhaoyang.tech
//  * @date:        2018/7/28 18:15
//  * @email:       xiazhaoyang@live.com
//  * @description: 本内容仅限于编程技术学习使用，转发请注明出处.
//  */
//
//
// import com.lb.user.UserApplication;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.ldap.core.LdapTemplate;
//
// /**
//  * <p>
//  *
//  * </p>
//  *
//  * @author xiachaoyang
//  * @version V1.0
//  * @date 2018年10月08日 11:47
//  * @modificationHistory=========================逻辑或功能性重大变更记录
//  * @modify By: {修改人} 2018年10月08日
//  * @modify reason: {方法名}:{原因}
//  * ...
//  */
// @SpringBootTest(classes = {UserApplication.class})
// public class LdapTest {
//     // @Resource
//     // public LdapTemplate ldapTemplate;
//     // @Resource
//     // private PersonRepository personRepo;
//     private LdapTemplate ldapTemplate;
//     private PersonRepository personRepository;
//
//     @Autowired
//     public LdapTest(LdapTemplate ldapTemplate, PersonRepository personRepository) {
//         this.ldapTemplate = ldapTemplate;
//         this.personRepository = personRepository;
//     }
//
//     @Test
//     public void ldapRestTestPart1() {
//
//
//         personRepository.findAll().forEach(System.out::println);
//
//
//         // personRepo.findAll().forEach(System.out::println);
//         // personRepo = new PersonRepoImpl();
//         // personRepo.setLdapTemplate(ldapTemplate);
//
//         // ldapTemplate.find
//         // 查询所有人员名称
//         // personRepo.getAllPersonNames().forEach(p-> System.out.println(p));
//         // 荣禧
//         // 荣耀
//         // feng_p1
//         // fengzi_0917_1
//         //....
//         // 查询所有人员集合（指定字段映射）
//         // personRepo.getAllPersons().forEach(p-> System.out.println(p.toString()));
//         // Person(personId=null, personName=fengzi_0917_7, orgId=14ed2744-fbd4-4868-8ebc-6b0b94d5ae60, sex=null, mobile=null, email=null, jobNo=null, studentId=null, certType=null, certNo=null, createTime=null, updateTime=null, status=null, disOrder=null, company=null)
//         // Person(personId=null, personName=fengzi_0917_104, orgId=14ed2744-fbd4-4868-8ebc-6b0b94d5ae60, sex=null, mobile=null, email=null, jobNo=null, studentId=null, certType=null, certNo=null, createTime=null, updateTime=null, status=null, disOrder=null, company=null)
//
//         // 根据dn查询
//         // System.out.println(personRepo.findPersonWithDn("ou=person,dc=coreservice,dc=platform,dc=xxx,dc=com").toString());
//
//         // 根据组织ID查询人员
//         // personRepo.getPersonNamesByOrgId("14ed2744-fbd4-4868-8ebc-6b0b94d5ae60").forEach(System.out::println);
//         // feng_0925_4687
//         // feng_0925_4693
//         //...
//
//         // 传统查询方式
//         // personRepo.getAllPersonNamesWithTraditionalWay().forEach(System.out::println);
//         // 荣禧
//         // 荣福
//         // feng_p1
//         // fengzi_0917_1
//         //....
//
//     }
// }
//
