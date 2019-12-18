package com.chaoxing.controller;

import com.chaoxing.aop.log.Log;
import com.chaoxing.domain.File;
import com.chaoxing.service.FileService;
import com.chaoxing.service.dto.FileQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author tachai
* @date 2019-12-16
*/
@RestController
@Api(tags = "文件: File管理")
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('file:list')")
    public void download(HttpServletResponse response, FileQueryCriteria criteria) throws IOException {
        fileService.download(fileService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询File")
    @ApiOperation("查询File")
    @PreAuthorize("@el.check('file:list')")
    public ResponseEntity getFiles(FileQueryCriteria criteria, Pageable pageable){
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