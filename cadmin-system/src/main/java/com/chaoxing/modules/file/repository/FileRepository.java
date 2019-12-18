package com.chaoxing.modules.file.repository;

import com.chaoxing.modules.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author tachai
* @date 2019-12-16
*/
public interface FileRepository extends JpaRepository<File, Integer>, JpaSpecificationExecutor<File> {

}