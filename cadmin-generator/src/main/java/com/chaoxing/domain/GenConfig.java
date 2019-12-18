package com.chaoxing.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Create by tachai on 2019-12-10 10:48
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */

@Data
@Entity
@Table(name="gen_config")
public class GenConfig {
    @Id
    private Long id;

    // 包路径
    private String pack;

    // 模块名
    @Column(name = "module_name")
    private String moduleName;

    // 前端文件路径
    private String path;

    // 前端文件路径
    @Column(name = "api_path")
    private String apiPath;

    // 作者
    private String author;

    // 表前缀
    private String prefix;

    // 是否覆盖
    private Boolean cover;
}
