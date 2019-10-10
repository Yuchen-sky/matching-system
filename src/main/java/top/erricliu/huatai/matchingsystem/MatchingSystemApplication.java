package top.erricliu.huatai.matchingsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author liubi
 * @date 2019-10-08 16:52
 **/
@SpringBootApplication
@MapperScan("com.hello.springboot.mapper")
public class MatchingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(MatchingSystemApplication.class, args);
    }
}
