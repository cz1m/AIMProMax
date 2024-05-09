package com.like4u.server.infrastructrue.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 17:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    //uri
    private String path;

    private String component;//路由路径

    private String name;

    private String parentName;

}
