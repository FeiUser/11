package noweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestB {
    @Autowired
    private TestA testA;
    public void test2() {
        testA.test1();
    }
    public void test3() {
        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
