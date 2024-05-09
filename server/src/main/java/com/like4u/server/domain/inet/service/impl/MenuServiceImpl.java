package com.like4u.server.domain.inet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.like4u.server.domain.inet.repository.MenuMapper;
import com.like4u.server.domain.inet.service.MenuService;
import com.like4u.server.infrastructrue.po.Menu;
import com.like4u.server.infrastructrue.po.MenuAndRoute;
import com.like4u.server.infrastructrue.po.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 17:20
 */
@Service//extends ServiceImpl<StudentMapper, Student>
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public MenuAndRoute findByUser(String username) {
        List<Menu> menuList=  menuMapper.findByUsername(username);
        return menu2MenuAndRoute(menuList);
    }

    @Override
    public MenuAndRoute findAll() {
        List<Menu> menuList = menuMapper.selectList(null);
        return menu2MenuAndRoute(menuList);
    }

    private MenuAndRoute menu2MenuAndRoute(List<Menu> list) {
        Map<Integer, Menu> all = new HashMap<>();
        List<Menu> tree = new ArrayList<>();
        List<Route> routes = new ArrayList<>();
        for (Menu menu : list) {
            if (menu.getPid() == 0) {
                tree.add(menu);
            }
            all.put(menu.getId(), menu);
            if (menu.getRoutePath() != null) {
                routes.add(new Route(menu.getRoutePath(), menu.getRouteComponent(), menu.getRouteName(), menu.getRouteParentName()));
            }
        }

        for (Menu menu : list) {
            Menu parent = all.get(menu.getPid());
            if (parent != null) {
                List<Menu> children = parent.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                children.add(menu);
            }
        }
        return new MenuAndRoute( tree,routes);
    }
}
