package com.chaoxing.modules.system.service.mapper;

import com.chaoxing.base.BaseMapper;
import com.chaoxing.modules.system.domain.Job;
import com.chaoxing.modules.system.service.dto.JobDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
* @author Zheng Jie
* @date 2019-03-29
*/
@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends BaseMapper<JobDTO, Job> {

    @Mapping(source = "deptSuperiorName", target = "deptSuperiorName")
    JobDTO toDto(Job job, String deptSuperiorName);
}