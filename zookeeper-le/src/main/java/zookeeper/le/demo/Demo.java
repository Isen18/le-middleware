package zookeeper.le.demo;

import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Isen
 * @date 2019/2/15 16:29
 * @since 1.0
 */
public class Demo {
    private static String CLIENT_PORT = "2181";
    private static int CONNECTION_TIMEOUT = 5000;

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 创建一个与服务器的连接
        ZooKeeper zk = new ZooKeeper("localhost:" + CLIENT_PORT, CONNECTION_TIMEOUT, (event) -> {
            // 监控所有被触发的事件
            System.out.println("已经触发了" + event.getType() + "事件！");
        });

        // 创建一个目录节点
        zk.create("/FirstZnode", "Hello world!".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 创建一个子目录节点
        zk.create("/FirstZnode/FirstChildZnode", "Hello first child!".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/FirstZnode", false, null)));

        // 取出子目录节点列表
        System.out.println(zk.getChildren("/FirstZnode", true));

        // 修改子目录节点数据
        zk.setData("/FirstZnode/FirstChildZnode", "modifyFirstChildZnode".getBytes(), -1);
        System.out.println("目录节点状态：[" + zk.exists("/FirstZnode", true) + "]");

        // 创建另外一个子目录节点
        zk.create("/FirstZnode/SecondChildZnode", "secondChildZnode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/FirstZnode/SecondChildZnode", true, null)));

        // 删除子目录节点
        zk.delete("/FirstZnode/FirstChildZnode", -1);
        zk.delete("/FirstZnode/SecondChildZnode", -1);

        // 删除父目录节点
        zk.delete("/FirstZnode", -1);

        LOGGER.info("ok");

        // 关闭连接
        zk.close();
    }
}
