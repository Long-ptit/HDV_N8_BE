package com.example.android;

import com.example.android.model.Good;
import com.example.android.repository.GoodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback
@Transactional
class AndroidApplicationTests {

    @Autowired
    GoodRepository goodRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    @Rollback
    void test123() {
        Good good = new Good();
        good.setName("haha");
        goodRepository.save(good);
    }

}
