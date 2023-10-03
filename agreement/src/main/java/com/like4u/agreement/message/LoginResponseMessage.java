package com.like4u.agreement.message;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class LoginResponseMessage extends AbstractResponseMessage {
    public LoginResponseMessage(Boolean isSuccess, String message){
        super(isSuccess, message);
    };

    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }
}
