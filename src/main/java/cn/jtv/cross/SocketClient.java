package cn.jtv.cross;import java.io.*;import java.net.Socket;/** * Created by lianrongfa on 2018/6/1. */public class SocketClient {    /**     * 给中间消息服务器发送json信息     *     * @param json json信息，具体格式视type类型而定     * @param ip ip地址     * @param port 端口     * @param type 参照下文     * @return jsonString {'state':true/false,'msg':''}     */    public static String sendMsg(String ip, int port, String json, int type) {        StringBuilder buffer = new StringBuilder();        Socket socket = null;        try {            socket = new Socket(ip, port);            OutputStream outputStream = socket.getOutputStream();            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);            //顺序不能乱            dataOutputStream.writeInt(0xCAFF);//消息头            dataOutputStream.writeInt(type);//操作类型            dataOutputStream.writeInt(json.length());//消息长度            dataOutputStream.write(json.getBytes());//消息            dataOutputStream.flush();            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));            buffer.append(reader.readLine());        } catch (IOException e) {            e.printStackTrace();        } finally {            if (socket != null) {                try {                    socket.close();                } catch (IOException e) {                    e.printStackTrace();                }            }        }        return buffer.toString();    }    public static void main(String[] args) {        sendMsg("127.0.0.1",9001,"{\"equipmentId\":\"1013\",\"warnNotOut\":\"12\",\"warnNotOff\":\"25\",\"earlyOn\":\"4\",\"upStartWarn\":\"44\",\"downStartWarn\":\"41\",\"onGuardTime\":\"4\",\"voiceOffStart\":\"4\",\"voiceOffEnd\":\"14\",\"type\":\"2\"} ",1);    }}