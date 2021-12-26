package com.zhang.thresh.server.system.controller;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.system.DataPermissionTest;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import com.zhang.thresh.server.system.service.IDataPermissionTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 数据权限控制器
 *
 * @author MrZhang
 * @date 2020-04-14 15:25:33
 */
@Slf4j
@RestController
@RequestMapping("dataPermissionTest")
@RequiredArgsConstructor
public class DataPermissionTestController {

    private final IDataPermissionTestService dataPermissionTestService;


    /**
     * 获取数据列表
     * @param request
     * @param dataPermissionTest
     * @return
     */
    @GetMapping("list")
    @PreAuthorize("hasAuthority('others:datapermission')")
    public ThreshResponse dataPermissionTestList(QueryRequest request, DataPermissionTest dataPermissionTest) {
        Map<String, Object> dataTable = ThreshUtil.getDataTable(this.dataPermissionTestService.findDataPermissionTests(request, dataPermissionTest));
        return new ThreshResponse().data(dataTable);
    }
}
