package corss.clientn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import corss.server.netty.protocol.receive.RemoteSettingRecUART;
import corss.server.netty.protocol.send.RemoteSettingSendUART;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;



/**
 * Created by lianrongfa on 2018/5/21.
 */
public class PropertiesTest {
    public static void main(String[] args){

        String json="{\"assetId\":\"694\",\"closeRecord\":\"0\",\"dataUpload\":\"0\",\"dataUploadType\":\"0\",\"earlyWarning\":\"0\",\"fault\":\"0\",\"guardState\":\"1\",\"handrailType\":\"0\",\"marchOut\":\"0\",\"oneTwoLine\":\"0\",\"peacetimeState\":\"1\",\"reviseTime\":\"1\",\"voiceState\":\"1\"}";
        RemoteSettingSendUART remoteSettingSendUART = JSONObject.parseObject(json, RemoteSettingSendUART.class);
        System.out.println();

    }

    private static void test32() {
        RemoteSettingRecUART remoteSettingRecUART = new RemoteSettingRecUART();

        byte[] bytes ={69,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,
                     0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30
                    ,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30,0x30
                    };
        remoteSettingRecUART.setData(bytes);
        remoteSettingRecUART.parse();

        JSONObject o = (JSONObject)JSONObject.toJSON(remoteSettingRecUART);


        System.out.println(JSONObject.toJSONString(remoteSettingRecUART));
    }

    private static void refex() {
        try {
            Field a = Aa.class.getDeclaredField("a");
            Aa aa = new Aa();
            aa.setA((char)48);

            a.setAccessible(true);
            Object o = a.get(aa);
            String s = o.toString();
            System.out.println(s);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void jjj(String s){
        int i=1/0;
    }

    private static void test3() {
        String s1="      ";
        String regex="\\s+";
        boolean matches = Pattern.matches(regex, s1);
        System.out.println(matches);
        byte[] bytes = s1.getBytes();
        System.out.println();
    }

    static class Aa extends Aaa{
        private char a;

        public char getA() {
            return a;
        }

        public void setA(char a) {
            this.a = a;
        }
    }
    static class Aaa{
        private int b;
        private char c;

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public char getC() {
            return c;
        }

        public void setC(char c) {
            this.c = c;
        }
    }
    private static void test2() {
        char a=' ';

        try {
            System.out.println(new String(new byte[]{0x31,0x38,0x30,0x35,0x32,0x32},"US-ASCII"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void tset2() {
        String s="180522052441";

        //Integer integer = Integer.valueOf(s,16);

        try {

            byte[] bytes = s.getBytes("US-ASCII");
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void test() {
        URL url = PropertiesTest.class.getClassLoader().getResource("configuration.properties");

        File file = new File(url.getFile());

        try {
            FileInputStream inputStream = new FileInputStream(file);

            Properties properties = new Properties();
            properties.load(inputStream);

            String property = properties.getProperty("receive.container");
            String[] split = property.split(",");

            for (String item:split){

                Byte aByte = Byte.valueOf(item);
                System.out.println(aByte);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
