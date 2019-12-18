package com.chaoxing.modules.file.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.chaoxing.modules.system.domain.Dept;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
* @author tachai
* @date 2019-12-16
*/
@Entity
@Data
@Table(name="file")
public class File implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 文件名
    @Column(name = "name")
    private String name;

    // 文件路径
    @Column(name = "path")
    private String path;

    // 文件类型
    @Column(name = "type")
    private String type;

    // 创建时间
    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;

    /*// 部门id
    @Column(name = "depte_id")
    private Integer depteId;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id")
    private Dept dept;

    // 是否删除0是已删除1是未删除
    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "static_path")
    private String staticPath;

    /*public File(String fileName, String path, String type, Date createTime,Dept dept){
        this.name=fileName;
        this.path=path;
        this.type=type;
        this.createTime=createTime;
        this.dept = dept;
    }*/


    public File(){

    }

    public void copy(File source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}