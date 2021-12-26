package com.zhang.thresh.server.generator.controller;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.constant.GeneratorConstant;
import com.zhang.thresh.common.core.entity.system.Column;
import com.zhang.thresh.common.core.entity.system.GeneratorConfig;
import com.zhang.thresh.common.core.exception.ThreshException;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import com.zhang.thresh.common.core.utils.FileUtil;
import com.zhang.thresh.server.generator.helper.GeneratorHelper;
import com.zhang.thresh.server.generator.service.IGeneratorConfigService;
import com.zhang.thresh.server.generator.service.IGeneratorService;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileSystemUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器controller
 * @author MrZhang
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class GeneratorController {

    private static final String SUFFIX = "_code.zip";

    private final IGeneratorService generatorService;
    private final IGeneratorConfigService generatorConfigService;
    private final GeneratorHelper generatorHelper;
    private final DynamicDataSourceProperties properties;

    /**
     * 获取数据源名称
     * @return
     */
    @GetMapping("datasources")
    @PreAuthorize("hasAuthority('gen:generate')")
    public ThreshResponse datasources() {
        Map<String, DataSourceProperty> datasources = properties.getDatasource();
        List<String> datasourcesName = new ArrayList<>();
        datasources.forEach((k, v) -> {
            String datasourceName = StringUtils.substringBefore(StringUtils.substringAfterLast(v.getUrl(), "/"), "?");
            datasourcesName.add(datasourceName);
        });
        return new ThreshResponse().data(datasourcesName);
    }

    /**
     * 获取表信息
     * @param tableName 表名称
     * @param datasource 所属数据源
     * @param request
     * @return
     */
    @GetMapping("tables")
    @PreAuthorize("hasAuthority('gen:generate')")
    public ThreshResponse tablesInfo(String tableName, String datasource, QueryRequest request) {
        Map<String, Object> dataTable = ThreshUtil.getDataTable(generatorService.getTables(tableName, request, GeneratorConstant.DATABASE_TYPE, datasource));
        return new ThreshResponse().data(dataTable);
    }

    /**
     * 生成代码
     * @param name 类名
     * @param datasource 数据源
     * @param remark 备注
     * @param response
     * @throws Exception
     */
    @PostMapping
    @PreAuthorize("hasAuthority('gen:generate:gen')")
    public void generate(@NotBlank(message = "{required}") String name,
                         @NotBlank(message = "{required}") String datasource,
                         String remark, HttpServletResponse response) throws Exception {
        GeneratorConfig generatorConfig = generatorConfigService.findGeneratorConfig();
        if (generatorConfig == null) {
            throw new ThreshException("代码生成配置为空");
        }

        String className = name;
        if (GeneratorConfig.TRIM_YES.equals(generatorConfig.getIsTrim())) {
            className = RegExUtils.replaceFirst(name, generatorConfig.getTrimValue(), StringUtils.EMPTY);
        }

        generatorConfig.setTableName(name);
        generatorConfig.setClassName(ThreshUtil.underscoreToCamel(className));
        generatorConfig.setTableComment(remark);
        // 生成代码到临时目录
        List<Column> columns = generatorService.getColumns(GeneratorConstant.DATABASE_TYPE, datasource, name);
        generatorHelper.generateEntityFile(columns, generatorConfig);
        generatorHelper.generateMapperFile(columns, generatorConfig);
        generatorHelper.generateMapperXmlFile(columns, generatorConfig);
        generatorHelper.generateServiceFile(columns, generatorConfig);
        generatorHelper.generateServiceImplFile(columns, generatorConfig);
        generatorHelper.generateControllerFile(columns, generatorConfig);
        // 打包
        String zipFile = System.currentTimeMillis() + SUFFIX;
        FileUtil.compress(GeneratorConstant.TEMP_PATH + "src", zipFile);
        // 下载
        FileUtil.download(zipFile, name + SUFFIX, true, response);
        // 删除临时目录
        FileSystemUtils.deleteRecursively(new File(GeneratorConstant.TEMP_PATH));
    }
}
