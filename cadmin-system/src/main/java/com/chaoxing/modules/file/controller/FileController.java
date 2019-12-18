package com.chaoxing.modules.file.controller;

import com.chaoxing.aop.log.Log;
import com.chaoxing.modules.file.domain.File;
import com.chaoxing.modules.file.service.FileService;
import com.chaoxing.modules.file.service.dto.FileQueryCriteria;
import com.chaoxing.modules.system.domain.Dept;
import com.chaoxing.modules.system.domain.User;
import com.chaoxing.modules.system.repository.UserRepository;
import com.chaoxing.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author tachai
* @date 2019-12-16
*/
@RestController
@Api(tags = "文件: File管理")
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    private final UserRepository userRepository;

    public FileController(FileService fileService,UserRepository userRepository) {
        this.fileService = fileService;
        this.userRepository = userRepository;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('file:list')")
    public void download(HttpServletResponse response, FileQueryCriteria criteria) throws IOException {
        /*long userId = SecurityUtils.getUserId();
        User user = userRepository.getOne(userId);
        long deptId = user.getDept().getId();
        criteria.setDepteId((int) deptId);
        if(user.getRoles().contains("管理员")){
            criteria =null;
        }*/
        fileService.download(fileService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询File")
    @ApiOperation("查询File")
    @PreAuthorize("@el.check('file:list')")
    public ResponseEntity getFiles(FileQueryCriteria criteria, Pageable pageable){
        // 错误  正确的在serviceimpl 中
        /*long userId = SecurityUtils.getUserId();
        User user = userRepository.getOne(userId);
        long deptId = user.getDept().getId();
        criteria.setDepteId((int) deptId);
        if(user.getRoles().contains("管理员")){
            criteria =null;
        }*/
        return new ResponseEntity<>(fileService.queryAll(criteria,pageable),HttpStatus.OK);
    }


    // todo 上传文件
    @ApiOperation("上传文件")
    @PostMapping
    @PreAuthorize("@el.check('file:upload')")
    public ResponseEntity create(@RequestParam String name, @RequestParam("file") MultipartFile file){
        return new ResponseEntity<>(fileService.upload(name, file),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改File")
    @ApiOperation("修改File")
    @PreAuthorize("@el.check('file:edit')")
    public ResponseEntity update(@Validated @RequestBody File resources){
        fileService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @Log("删除File")
    @ApiOperation("删除File")
    @PreAuthorize("@el.check('file:del')")
    public ResponseEntity delete(@PathVariable Integer id){
        fileService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}