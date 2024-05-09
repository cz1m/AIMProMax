package com.like4u.server.infrastructrue.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuAndRoute {
    private List<Menu> menus;

    private List<Route> routes;
}
