package com.chaoxing.modules.system.service.mapper;

import com.chaoxing.base.BaseMapper;
import com.chaoxing.modules.system.domain.Role;
import com.chaoxing.modules.system.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDTO, Role> {

}
