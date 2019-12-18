package com.chaoxing.modules.file.service.mapper;

import com.chaoxing.base.BaseMapper;
import com.chaoxing.modules.file.domain.File;
import com.chaoxing.modules.file.service.dto.FileDTO;
import com.chaoxing.modules.system.domain.Dept;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author tachai
* @date 2019-12-16
*/

@Mapper(componentModel = "spring", uses={Dept.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileMapper extends BaseMapper<FileDTO, File> {

    /*@Mapping(source = "dept.name", target = "deptName")
    FileDTO toDto(FileDTO dto, String deptName);*/
}