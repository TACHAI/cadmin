package com.chaoxing.modules.system.repository;

import com.chaoxing.modules.system.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author Zheng Jie
 * @date 2018-12-17
 */
public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {

    Menu findByName(String name);

    Menu findByComponentName(String name);

    List<Menu> findByPid(long pid);
    // todo  语句是后来加的  springData jpa 2.2.3 和 2.1.2
    //@Modifying
    //@Query(value = "select * from menu m left join roles_menus rm on m.id =rm.menu.id left join role r on r.id=rm.role.id where r.id = ?1 and m.type= ?2",nativeQuery = true)
    //@Modifying
    //@Query(value = "select m.id,m.i_frame,m.name,m.component,m.pid,m.sort,m.icon,m.path,m.cache,m.hidden,m.component_name,m.create_time,m.permission,m.type from menu m left join roles_menus rm on m.id =rm.menu_id left join role r on r.id=rm.role_id where r.id = 1? and m.type= 2? ")
    LinkedHashSet<Menu> findByRoles_IdAndTypeIsNotInOrderBySortAsc(Long id, Integer type);
}
