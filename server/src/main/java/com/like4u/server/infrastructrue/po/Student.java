package com.like4u.server.infrastructrue.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/3/29 22:33
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Student {
    @TableId(value = "id",type = IdType.AUTO)
    public Long id;
    public String name;

    @Min(value = 1, message = "年龄不符合要求")
    @Max(value = 120, message = "年龄不符合要求")
    public Integer age;

    public Character sex;

}
