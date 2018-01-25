package rabbit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.CharsetUtil;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by adi on 1/19/18.
 */
public class ConsumerManagementServer {

    static final boolean SSL = System.getProperty("ssl") != null;

    ConsumerManagementServer(int port, Processor processor, CountDownLatch countDownLatch) throws InterruptedException, CertificateException, SSLException {

        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(LineBasedFrameDecoder.class.getName(), new LineBasedFrameDecoder(1024));
                            p.addLast(StringDecoder.class.getName(), new StringDecoder(CharsetUtil.UTF_8));
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc()));
                            }
//                            p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new ServerHandler(processor));
                        }
                    });

            // Start the server.
            ChannelFuture f = b.bind(port).sync();
            countDownLatch.countDown();
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
