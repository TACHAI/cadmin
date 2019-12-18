package com.chaoxing.modules.system.service.mapper;

import com.chaoxing.base.BaseMapper;
import com.chaoxing.modules.system.domain.Dept;
import com.chaoxing.modules.system.service.dto.DeptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDTO, Dept> {

}