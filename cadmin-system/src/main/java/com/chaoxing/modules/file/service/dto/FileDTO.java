package com.chaoxing.modules.file.service.dto;

import com.chaoxing.modules.system.service.dto.DeptSmallDTO;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


/**
* @author tachai
* @date 2019-12-16
*/
@Data
public class FileDTO implements Serializable {

    private Integer id;

    // 文件名
    private String name;

    // 文件路径
    private String path;

    // 文件类型
    private String type;

    // 创建时间
    private Timestamp createTime;

    private DeptSmallDTO dept;


    // 是否删除0是已删除1是未删除
    //private Integer isDelete;
}