package com.zhang.thresh.server.generator.service.impl;

import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.constant.ThreshConstant;
import com.zhang.thresh.common.core.entity.system.Column;
import com.zhang.thresh.common.core.entity.system.Table;
import com.zhang.thresh.common.core.utils.SortUtil;
import com.zhang.thresh.server.generator.mapper.GeneratorMapper;
import com.zhang.thresh.server.generator.service.IGeneratorService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MrZhang
 */
@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl implements IGeneratorService {

    private final GeneratorMapper generatorMapper;

    @Override
    public List<String> getDatabases(String databaseType) {
        return generatorMapper.getDatabases(databaseType);
    }

    @Override
    public IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName) {
        Page<Table> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, ThreshConstant.CREATE_TIME, ThreshConstant.ORDER_ASC, false);
        return generatorMapper.getTables(page, tableName, databaseType, schemaName);
    }

    @Override
    public List<Column> getColumns(String databaseType, String schemaName, String tableName) {
        return generatorMapper.getColumns(databaseType, schemaName, tableName);
    }
}
