package kr.pe.kwonnam.hibernatebatchsize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Date;

@Service
public class UserService {
    private Logger log = LoggerFactory.getLogger(UserService.class);

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = false)
    public void bulkInsert(int count) {
        log.info("Properties : {}", emf.getProperties());

        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(i);
            user.setName("name_" + i);
            user.setBirthday(new Date());

            log.info("Insert User : {}", user);
            userRepository.save(user);

            if ((i + 1) % 5 == 0) {
                log.info("Flushing...");
                userRepository.flush();
                userRepository.clear();
            }
        }
    }
}
