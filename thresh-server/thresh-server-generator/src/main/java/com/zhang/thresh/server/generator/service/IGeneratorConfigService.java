package com.zhang.thresh.server.generator.service;

import com.zhang.thresh.common.core.entity.system.GeneratorConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author MrZhang
 */
public interface IGeneratorConfigService extends IService<GeneratorConfig> {

    /**
     * 查询
     *
     * @return GeneratorConfig
     */
    GeneratorConfig findGeneratorConfig();

    /**
     * 修改
     *
     * @param generatorConfig generatorConfig
     */
    void updateGeneratorConfig(GeneratorConfig generatorConfig);

}
