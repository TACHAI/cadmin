package com.chaoxing.modules.file.service;

import com.chaoxing.modules.file.domain.File;
import com.chaoxing.modules.file.service.dto.FileDTO;
import com.chaoxing.modules.file.service.dto.FileQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author tachai
* @date 2019-12-16
*/
public interface FileService {

    /**
    * 查询数据分页
    * @param criteria 条件参数
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(FileQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<FileDTO>
    */
    List<FileDTO> queryAll(FileQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return FileDTO
     */
    FileDTO findById(Integer id);

    FileDTO create(File resources);

    /**
     * 上传文件
     * @param name
     * @param file
     * @return
     */
    FileDTO upload(String name, MultipartFile file);

    void update(File resources);

    void delete(Integer id);

    void download(List<FileDTO> all, HttpServletResponse response) throws IOException;
}