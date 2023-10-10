package com.like4u.client.socket;
import com.like4u.client.application.UIService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/10/3 21:24
 */
public abstract class MyBizHandler<T> extends SimpleChannelInboundHandler<T> {
    protected UIService uiService;

    public MyBizHandler(UIService uiService) {
        this.uiService = uiService;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        channelRead(ctx.channel(), msg);
    }
    public abstract void channelRead(Channel channel, T msg);

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("断开连接了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("关闭" + ctx.channel().id());
    }
}
