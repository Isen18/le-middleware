package redis.le.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author Isen
 * @date 2019/3/1 14:58
 * @since 1.0
 */
public class Demo {
    private static final Logger LOGGER = LogManager.getLogger(Demo.class);

    public static void main(String[] args) {
        LOGGER.info("hello {}", "world");
    }

    @Test
    public void testJedisSingle(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("name", "isen");
        String name = jedis.get("name");
        System.out.println("name=" + name);
        jedis.close();
    }
}
