package com.chaoxing.modules.system.service.mapper;

import com.chaoxing.base.BaseMapper;
import com.chaoxing.modules.system.domain.DictDetail;
import com.chaoxing.modules.system.service.dto.DictDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends BaseMapper<DictDetailDTO, DictDetail> {

}