package corss.server.protocol;

/**
 * Created by lianrongfa on 2018/5/22.
 */
public interface UART {

    /**
     * 得到所有数据
     * @return byte数组
     */
    byte [] getData();

    /**
     * 设置数据
     * @param data byte数组
     */
    void setData(byte[] data);

    /**
     * 得到标识位，下标为0 1
     * @return byte.length=2 ,类型为10进制整数
     */
    byte [] getMark();

    /**
     * 得到标识位，下标为0 1
     * @return byte.length=2 ,类型为10进制整数 String表示类型
     */
    String getMarkString();


    /**
     * 预留接口
     * @return
     */
    byte [] getId();

    /**
     * 预留接口
     * @return
     */
    String getIdString();

    /**
     * 启动解析
     */
    void parse();
}
