package com.zhang.thresh.server.system.mapper;

import com.zhang.thresh.common.core.entity.system.DataPermissionTest;
import com.zhang.thresh.common.datasource.starter.annotation.DataPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author MrZhang
 */
@DataPermission(methods = {"selectPage"})
public interface DataPermissionTestMapper extends BaseMapper<DataPermissionTest> {

}
