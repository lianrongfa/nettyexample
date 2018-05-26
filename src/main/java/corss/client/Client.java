package corss.client;

import corss.server.codec.SimpleDecoder;
import corss.server.codec.SimpleEncoder;
import corss.server.codec.UARTDecoder;
import corss.server.protocol.SimpleProduct;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

/**
 * Created by lianrongfa on 2018/5/17.
 */
public class Client {
    private int port;
    private String host;
    private SocketChannel socketChannel;
    public Client(String host,int port) throws Exception {
        this.host = host;
        this.port = port;
        start();
    }
    private void start() throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.group(eventLoopGroup);
        bootstrap.remoteAddress(this.host, this.port);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(
                        new UARTDecoder(),
                        new ByteArrayEncoder(),
                        new ClientHandler());
            }
        });
        ChannelFuture future = bootstrap.connect(this.host, this.port).sync();
        if (future.isSuccess()) {
            socketChannel = (SocketChannel)future.channel();
            System.out.println("connect server  success|");
        }
    }

    public void sendMessage(byte[] s){
        //SimpleProduct simpleProduct = new SimpleProduct(s.length(), s.getBytes());

        socketChannel.writeAndFlush(s);
    }

    public int getPort() {
        return this.port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
}
