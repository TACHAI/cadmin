package com.chaoxing.modules.file.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.chaoxing.config.DataScope;
import com.chaoxing.exception.BadRequestException;
import com.chaoxing.modules.file.domain.File;
import com.chaoxing.modules.file.repository.FileRepository;
import com.chaoxing.modules.file.service.FileService;
import com.chaoxing.modules.file.service.dto.FileDTO;
import com.chaoxing.modules.file.service.dto.FileQueryCriteria;
import com.chaoxing.modules.file.service.mapper.FileMapper;
import com.chaoxing.modules.system.domain.Dept;
import com.chaoxing.modules.system.repository.DeptRepository;
import com.chaoxing.modules.system.repository.UserRepository;

import com.chaoxing.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
* @author tachai
* @date 2019-12-16
*/
@Slf4j
@Service
@CacheConfig(cacheNames = "file")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    private final UserRepository userRepository;

    private final DeptRepository deptRepository;

    private final FileMapper fileMapper;

    private final DataScope dataScope;


    @Value("${file.path}")
    private String path;

    @Value("${file.maxSize}")
    private long maxSize;

    public FileServiceImpl(FileRepository fileRepository, FileMapper fileMapper,UserRepository userRepository,DeptRepository deptRepository,DataScope dataScope) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
        this.userRepository = userRepository;
        this.deptRepository = deptRepository;
        this.dataScope =dataScope;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(FileQueryCriteria criteria, Pageable pageable){
        //
        criteria.setDeptIds(dataScope.getDeptIds());

        Page<File> page = fileRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(fileMapper::toDto));
    }

    @Override
    @Cacheable
    public List<FileDTO> queryAll(FileQueryCriteria criteria){
        //
        criteria.setDeptIds(dataScope.getDeptIds());

        return fileMapper.toDto(fileRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Cacheable(key = "#p0")
    public FileDTO findById(Integer id) {
        File file = fileRepository.findById(id).orElseGet(File::new);
        ValidationUtil.isNull(file.getId(),"File","id",id);
        return fileMapper.toDto(file);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public FileDTO create(File resources) {
        return fileMapper.toDto(fileRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public FileDTO upload(String name, MultipartFile multipartFile) {

        FileUtil.checkSize(maxSize, multipartFile.getSize());
        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        // 可自行选择方式
//        String type = FileUtil.getFileTypeByMimeType(suffix);
        String type = FileUtil.getFileType(suffix);
        java.io.File file = FileUtil.upload(multipartFile, path + type +  java.io.File.separator);
        if(ObjectUtil.isNull(file)){
            throw new BadRequestException("上传失败");
        }
        long userId = SecurityUtils.getUserId();
        long deptId = userRepository.getOne(userId).getDept().getId();
        Dept dept = deptRepository.getOne(deptId);

        try {
            name = StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
            File file1 = new File();
            file1.setName(name);
            file1.setPath(file.getPath());
            file1.setCreateTime(new Date());
            file1.setDept(dept);
            file1.setType(type);
            file1.setIsDelete(1);
            return fileMapper.toDto(fileRepository.save(file1));
        }catch (Exception e){
            FileUtil.del(file);
            throw e;
        }

    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(File resources) {
        File file = fileRepository.findById(resources.getId()).orElseGet(File::new);
        ValidationUtil.isNull( file.getId(),"File","id",resources.getId());
        file.copy(resources);
        fileRepository.save(file);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        fileRepository.deleteById(id);
    }


    @Override
    public void download(List<FileDTO> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (FileDTO file : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("文件名", file.getName());
            map.put("文件路径", file.getPath());
            map.put("文件类型", file.getType());
            map.put("创建时间", file.getCreateTime());
            map.put("部门", file.getDept().getName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}