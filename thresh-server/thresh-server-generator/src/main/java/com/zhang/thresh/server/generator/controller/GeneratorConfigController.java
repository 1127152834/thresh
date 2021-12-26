package com.zhang.thresh.server.generator.controller;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.system.GeneratorConfig;
import com.zhang.thresh.common.core.exception.ThreshException;
import com.zhang.thresh.server.generator.service.IGeneratorConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 代码生成器配置controller
 * @author MrZhang
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("config")
public class GeneratorConfigController {

    private final IGeneratorConfigService generatorConfigService;

    /**
     * 获取配置信息
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('gen:config')")
    public ThreshResponse getGeneratorConfig() {
        return new ThreshResponse().data(generatorConfigService.findGeneratorConfig());
    }

    /**
     * 修改配置信息
     * @param generatorConfig
     * @throws ThreshException
     */
    @PostMapping
    @PreAuthorize("hasAuthority('gen:config:update')")
    public void updateGeneratorConfig(@Valid GeneratorConfig generatorConfig) throws ThreshException {
        if (StringUtils.isBlank(generatorConfig.getId())) {
            throw new ThreshException("配置id不能为空");
        }
        this.generatorConfigService.updateGeneratorConfig(generatorConfig);
    }
}
