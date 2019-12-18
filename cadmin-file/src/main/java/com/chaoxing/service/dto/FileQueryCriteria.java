package com.chaoxing.service.dto;

import com.chaoxing.annotation.Query;
import lombok.Data;

/**
* @author tachai
* @date 2019-12-16
*/
@Data
public class FileQueryCriteria{

    // 精确
    @Query
    private Integer id;

    // 精确
    @Query
    private Integer depteId;
}