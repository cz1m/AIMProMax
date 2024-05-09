package com.like4u.server.infrastructrue.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 17:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class LoginDto {

    @Length(min = 2,max=20 ,message = "用户名长度不合适")
    @TableField("userId")
    private String userId;
    @TableField("userPassword")
    @Length(min = 2,message = "密码过短")
    private String userPassword;
    @TableField("userNickName")
    private String userNickName;

    @TableField("userHead")
    private String userHead;
}
