package com.chaoxing.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.chaoxing.domain.File;
import com.chaoxing.exception.BadRequestException;
import com.chaoxing.modules.system.repository.UserRepository;
import com.chaoxing.utils.*;
import com.chaoxing.repository.FileRepository;
import com.chaoxing.service.FileService;
import com.chaoxing.service.dto.FileDTO;
import com.chaoxing.service.dto.FileQueryCriteria;
import com.chaoxing.service.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author tachai
* @date 2019-12-16
*/
@Service
@CacheConfig(cacheNames = "file")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    private final UserRepository userRepository;

    private final FileMapper fileMapper;


    @Value("${file.path}")
    private String path;

    @Value("${file.maxSize}")
    private long maxSize;

    public FileServiceImpl(FileRepository fileRepository, FileMapper fileMapper,UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(FileQueryCriteria criteria, Pageable pageable){
        Page<File> page = fileRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(fileMapper::toDto));
    }

    @Override
    @Cacheable
    public List<FileDTO> queryAll(FileQueryCriteria criteria){
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
        try {
            name = StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
            File file1 = new File(
                    file.getName(),
                    file.getPath(),
                    type,
                    new Date(),
                    deptId
            );
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