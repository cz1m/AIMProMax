package com.like4u.server.infrastructrue.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/1 16:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQuery {
    private String name;
    private Integer[] age;
    private String sex;
    private Integer pageNumber;
    private Integer pageSize;
}
