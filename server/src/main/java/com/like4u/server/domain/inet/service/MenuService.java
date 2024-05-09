package com.like4u.server.domain.inet.service;


import com.like4u.server.infrastructrue.po.MenuAndRoute;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 17:20
 */

public interface MenuService {
    MenuAndRoute findByUser(String username);

    MenuAndRoute findAll();
}
