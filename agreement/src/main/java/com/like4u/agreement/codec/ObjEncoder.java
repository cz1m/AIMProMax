package com.like4u.agreement.codec;

import com.like4u.agreement.message.Message;
import com.like4u.agreement.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class ObjEncoder extends MessageToByteEncoder<Message> {



    @Override
    protected void encode(ChannelHandlerContext ctx, Message in, ByteBuf out) throws Exception {
        byte[] data = SerializationUtil.serialize(in);
        out.writeInt(data.length + 1);
        out.writeByte(in.getMessageType()); //添加指令
        out.writeBytes(data);
    }
}
