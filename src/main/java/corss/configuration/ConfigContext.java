package corss.configuration;

import corss.clientn.PropertiesTest;
import corss.ui.Monitor;
import corss.util.PullEquipmentId;
import corss.util.UiTimedTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lianrongfa on 2018/5/21.
 */
public class ConfigContext {

    private static Properties properties;

    private static ProtocolConfig protocolConfig=new ProtocolConfig();

    private Monitor monitor;

    static {

        try {
            InputStream inputStream = PropertiesTest.class.getClassLoader().getResourceAsStream("configuration.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static ConfigContext configContext=new ConfigContext();

    public static ConfigContext getInstace(){
        return configContext;
    }

    /**
     * 服务端口
     */
    private int serverPort;
    private int socketPort;

    /**
     * redis
     */
    private String redisServer;
    private int redisPort;
    public static final String CROSS_KEY="cross";

    //web服务器接口地址
    private String webserverUrl;

    private ConfigContext(){
        load();
        //设备id自动维护
        new PullEquipmentId().executor();
        //ui
        new UiTimedTask().executor();
    }

    /**
     * 读取配置文件
     */
    private void load(){
        //端口
        serverPort=Integer.valueOf(properties.getProperty("server.port","8080"));
        socketPort=Integer.valueOf(properties.getProperty("socket.port","8089"));

        //redis
        redisServer=properties.getProperty("redis.server","127.0.0.1");
        redisPort=Integer.valueOf(properties.getProperty("redis.port","6379"));

        //web服务器接口地址
        webserverUrl=properties.getProperty("webserver.url");

        //协议标识符
        String property = properties.getProperty("receive.container");
        for (String item :  property.split(",")) {
            protocolConfig.addReceiveType(Byte.valueOf(item));
        }

        //协议长度
        for (Byte item:protocolConfig.getReceiveContainer()) {
            String s = properties.getProperty("receive.num." + item);
            protocolConfig.getReceiveMap().put(item,Integer.valueOf(s));
        }

    }

    public String getWebserverUrl() {
        return webserverUrl;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public int getSocketPort() {
        return socketPort;
    }

    public String getRedisServer() {
        return redisServer;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public ProtocolConfig getProtocolConfig(){
        return protocolConfig;
    }


    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}
