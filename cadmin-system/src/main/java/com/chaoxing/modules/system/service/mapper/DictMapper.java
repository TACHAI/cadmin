package com.chaoxing.modules.system.service.mapper;

import com.chaoxing.base.BaseMapper;
import com.chaoxing.modules.system.domain.Dict;
import com.chaoxing.modules.system.service.dto.DictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapper extends BaseMapper<DictDTO, Dict> {

}