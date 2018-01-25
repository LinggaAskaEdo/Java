package rabbit;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by adi on 1/19/18.
 */
public interface Processor {

    String process(String msg, ChannelHandlerContext ctx);
}
