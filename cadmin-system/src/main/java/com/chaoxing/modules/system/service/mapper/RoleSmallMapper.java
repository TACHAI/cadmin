package com.chaoxing.modules.system.service.mapper;

import com.chaoxing.base.BaseMapper;
import com.chaoxing.modules.system.domain.Role;
import com.chaoxing.modules.system.service.dto.RoleSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Zheng Jie
 * @date 2019-5-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDTO, Role> {

}
