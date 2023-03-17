package com.av.guessTheNumber;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GuessTheNumberApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads(){
        assertThat(applicationContext.getBean("serviceLayer")).isNotNull();
    }
}