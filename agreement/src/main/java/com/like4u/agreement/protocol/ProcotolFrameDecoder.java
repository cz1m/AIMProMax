package com.like4u.agreement.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/15 19:41
 */
public class ProcotolFrameDecoder extends LengthFieldBasedFrameDecoder {

    public ProcotolFrameDecoder(){
        this(1024,12,4,0,0);
    }
    public ProcotolFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}
