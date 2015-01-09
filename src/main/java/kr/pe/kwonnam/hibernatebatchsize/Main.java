package kr.pe.kwonnam.hibernatebatchsize;

import kr.pe.kwonnam.hibernatebatchsize.config.ApplicationContextConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        try {
            UserService userService = context.getBean(UserService.class);
            userService.bulkInsert(10);
        } finally {
            context.close();
        }
    }
}
