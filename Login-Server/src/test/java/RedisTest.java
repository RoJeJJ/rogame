import com.rojee.core.entity.User;
import com.rojee.login.LoginApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoginApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Test
    public void save(){
        User user = new User();
        user.setId(454534);
        user.setAccount("dfty345");
        user.setNickname("测试");
        user.setPassword("123456");
    }
}
