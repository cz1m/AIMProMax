package com.like4u.agreement.message;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2023/9/17 16:09
 */
public class PingMessage extends Message{
    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
