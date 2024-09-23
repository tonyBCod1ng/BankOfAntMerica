//package org.perscholas.BankOfAntMerica;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.runner.RunWith;
//import org.perscholas.BankOfAntMerica.database.DAO.AccountDAO;
//import org.perscholas.BankOfAntMerica.database.DAO.BranchDAO;
//import org.perscholas.BankOfAntMerica.database.DAO.UserDAO;
//import org.perscholas.BankOfAntMerica.database.Entity.Account;
//import org.perscholas.BankOfAntMerica.database.Entity.Branch;
//import org.perscholas.BankOfAntMerica.database.Entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.Assert.assertEquals;
//
//@ContextConfiguration(classes = BankOfAntMericaApplication.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//class BankOfAntMericaApplicationTests {
//    @Autowired
//    private UserDAO userDAO;
//    @Autowired
//    private  AccountDAO accountDAO;
//    @Autowired
//    private  BranchDAO branchDAO;
//
//
//    @Test
//    @Order(1)
//    @DisplayName("Finds Users by Id")
//    public void whenMapIdToUsers_thenGetUserStream() {
//        Integer[] empIds = { 11, 12, 10 };
//
//        List<User> users = Stream.of(empIds)
//                .map(userDAO::findUserById)
//                .toList();
//
//        Assertions.assertEquals(users.size(), empIds.length);
//    }
//
//
//    @Order(3)
//    @Test
//    @DisplayName("Finds correct Account based on corresponding id number")
//    void findAccountByIdTest() {
//        Integer id = 1;
//
//        Account account = accountDAO.findAccountById(id);
//        Assertions.assertNotNull(account);
//        Assertions.assertEquals(id, account.getId());
//        Assertions.assertEquals(11, account.getUser().getId());
//    }
//
//    @Order(4)
//    @Test
//    @DisplayName("Finds correct Branch based on corresponding id number")
//    void findBranchByIdTest() {
//        Integer id = 1;
//
//        Branch branch = branchDAO.findBranchById(id);
//        Assertions.assertNotNull(branch);
//        Assertions.assertEquals(id, branch.getId());
//        Assertions.assertEquals(13, branch.getManagerId());
//    }
//
//    @Order(5)
//    @ParameterizedTest
//    @CsvSource({
//            "fake@email.com",
//            "nonadmin@test.com",
//            "ok@test.com"
//    })
//    @DisplayName("Finds correct User based on corresponding email")
//    void findUserByEmailTest(String email) {
//        User user = userDAO.findByEmailIgnoreCase(email);
//        Assertions.assertNotNull(user);
//        Assertions.assertEquals(email, user.getEmail());
//    }
//
//
//}
