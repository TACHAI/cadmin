package com.chaoxing.repository;

import com.chaoxing.domain.AlipayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zheng Jie
 * @date 2018-12-31
 */
public interface AlipayRepository extends JpaRepository<AlipayConfig,Long> {
}
