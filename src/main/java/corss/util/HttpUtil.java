package corss.util;

import com.alibaba.fastjson.JSONObject;
import corss.configuration.ConfigContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lianrongfa on 2018/5/30.
 */
public abstract class HttpUtil {

    private final static Logger logger= LoggerFactory.getLogger(HttpUtil.class);
    /**
     * 发送http请求
     *
     * @param requestUrl    请求的url地址
     * @param requestMethod 请求方法
     * @param outputStr     参数
     * @return
     */
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr){
        if(logger.isDebugEnabled()){
            logger.debug("web端接口调用[url:"+requestUrl+"][param:"+outputStr+"]");
        }
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection conn = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(3000);
            conn.setRequestMethod(requestMethod);
            conn.connect();
            //往服务器端写内容 也就是发起http请求需要带的参数
            if (null != outputStr) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            //读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);

            String line = "";
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }

        } catch (Exception e) {
            logger.error("web端接口调用出错！[url:"+requestUrl+"][param:"+outputStr+"]",e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return buffer.toString();
    }
}
