package com.like4u.server.interfaces;

import com.like4u.server.domain.inet.service.MenuService;
import com.like4u.server.infrastructrue.comon.AjaxResult;
import com.like4u.server.infrastructrue.po.MenuAndRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 17:18
 */
@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public AjaxResult getAll(){
        MenuAndRoute all = menuService.findAll();
        return AjaxResult.success(all);
    }

    @GetMapping("/menu/{username}")
    public AjaxResult getMenuAndRoute(@PathVariable String username){
        MenuAndRoute menuAndRoute = menuService.findByUser(username);
        return AjaxResult.success(menuAndRoute);
    }
}
