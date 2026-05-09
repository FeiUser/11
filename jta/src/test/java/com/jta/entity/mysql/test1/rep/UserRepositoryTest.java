package com.jta.entity.mysql.test1.rep;

import com.jta.entity.Gender;
import com.jta.entity.mysql.test1.tab.User;
import com.jta.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class UserRepositoryTest {
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestService testService;

    @Test
    @Transactional
    public void testUserAdd() {
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                System.out.println(1/0);
            }
            User user = new User();
            user.setUsername(i+"");
            user.setPassword(i+"");
            userAdd(user);
        }
    }

    @Test
    public void testEnum() {
            User user = new User();
            user.setUsername("123");
            user.setPassword("123");
            user.setGender(Gender.male);
        try {
            User save = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            System.out.println("123");
        }
        System.out.println(user.toString());
    }

    public void userAdd(User user) {
        userRepository.save(user);
    }

    @Test
    public void testUser() {
        User user = userRepository.findById(1).orElse(null);
        System.out.println(user.getUsername());
        int i = userRepository.updateUsername("123", user.getId());
        System.out.println(i);
//        testUserAdd();
    }

    @Test
    public void listTest() throws InterruptedException {
        testService.testList();
        testService.testList();
    }

    @Test
    public void testUp() {
        String s = "N/A".toLowerCase();
        System.out.println(s);
    }

    @Test
    public void testReplaceSpecialChar() {
        String s0 = "~tool.ch i/n-a_z.com|88~8";
        String s = s0.replaceAll("[^\\w]|_|\\s","");
//        String s = s0.replaceAll("[^\\w]|_","");
        if (s0.startsWith("~")) {
            s = "~" + s;
        }
        System.out.println(s0);
        System.out.println(s);
    }

    @Test
    public void testDate() throws ParseException {
        String pubtime = "2024-09-26 09:26:00";
        Date parse = simpleDateFormat.parse(pubtime);
        String startTime = simpleDateFormat.format(parse.getTime() - (1 * 1000));
        System.out.println(startTime);
    }
    @Test
    public void testTry() throws ParseException, InterruptedException {
        Integer i = 0;
        try {
            while (true) {
                if (10 == i) {
                    System.out.println("入库");
                    break;
                }
                i++;
                Thread.sleep(1000);
            }

//            i = 1 / 0;
        } catch (Exception e) {
            System.out.println("catch");
        } finally {
            i++;
            System.out.println("finally");
            System.out.println(i);
        }
    }
}