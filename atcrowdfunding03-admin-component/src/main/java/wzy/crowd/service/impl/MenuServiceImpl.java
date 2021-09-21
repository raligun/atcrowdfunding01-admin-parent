package wzy.crowd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wzy.crowd.entity.Menu;
import wzy.crowd.mapper.MenuMapper;
import wzy.crowd.service.MenuService;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/8/29-20:49
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(null);
    }

    @Override
    public int addMenu(Menu menu) {
        return menuMapper.insert(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int deleteMenu(Integer id) {

        return menuMapper.deleteByPrimaryKey(id);
    }
}
