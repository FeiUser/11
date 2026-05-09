package org.test.mybatis.mapper.ds1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.mybatis.entity.ds1.User;
import org.test.mybatis.entity.ds2.Goods;
import org.test.mybatis.mapper.ds2.GoodsMapper;
import org.test.mybatis.service.FileService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootTest
public class UserMapperTest {

//    @Resource(name = "asyncServiceExecutor")
//    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private FileService fileServer;
//    @Autowired
//    @Qualifier("sqlSessionTemplate1")
//    private SqlSessionTemplate sqlSessionTemplate1;
//    @Autowired
//    @Qualifier("sqlSessionTemplate2")
//    private SqlSessionTemplate sqlSessionTemplate2;
    @Test
    public void testUser() {
//        UserMapper userMapper = sqlSessionTemplate1.getMapper(UserMapper.class);
        User userById = userMapper.getUserById(1);
        System.out.println(userById.toString());
    }
    @Test
    public void testUserAdd() {
        User user = new User();
        user.setName("123");
        user.setPassword("124");
        user.setSex(123);
        user.setSexString("女");
        user.setCreateTime(new Date());
        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void testGoods() {
//        GoodsMapper goodsMapper =  sqlSessionTemplate2.getMapper(GoodsMapper.class);
        Goods goodsById = goodsMapper.getGoodsById(1);
        System.out.println(goodsById.toString());
        System.out.println("\uD83C\uDDE8\uD83C\uDDF3");
    }

    @Test
    public void taskDir123() {
//        System.out.println(taskExecutor.getThreadNamePrefix());
//        System.out.println(taskExecutor.getThreadPoolExecutor().getPoolSize());
//        System.out.println(taskExecutor.getThreadPoolExecutor().getActiveCount());
        String filePath = "E:\\lyf\\tyy\\20240202\\";
        List<String> list = new ArrayList<>();
        list.add("conf");
        list.add("mapping");
        list.forEach( floder -> {
            try {
                fileServer.fileServer(filePath, floder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("finished!");
    }


}