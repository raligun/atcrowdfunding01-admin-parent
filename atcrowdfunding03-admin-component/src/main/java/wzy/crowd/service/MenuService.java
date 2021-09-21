package wzy.crowd.service;

import org.springframework.stereotype.Service;
import wzy.crowd.entity.Menu;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/8/29-20:48
 */
@Service
public interface MenuService {

    List<Menu> getAll();

    int addMenu(Menu menu);

    int updateMenu(Menu menu);

    int deleteMenu(Integer id);
}
