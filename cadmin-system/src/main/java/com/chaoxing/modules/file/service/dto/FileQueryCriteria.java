package com.chaoxing.modules.file.service.dto;

import com.chaoxing.annotation.Query;
import lombok.Data;

import java.util.Set;

/**
* @author tachai
* @date 2019-12-16
*/
@Data
public class FileQueryCriteria{

    // 精确
    @Query
    private Integer id;

    @Query(propName = "id", joinName = "dept")
    private Long deptId;

    @Query(propName = "id", joinName = "dept", type = Query.Type.IN)
    private Set<Long> deptIds;
}