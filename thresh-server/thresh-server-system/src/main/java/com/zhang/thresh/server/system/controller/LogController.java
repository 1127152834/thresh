package com.zhang.thresh.server.system.controller;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.constant.StringConstant;
import com.zhang.thresh.common.core.entity.system.Log;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import com.zhang.thresh.server.system.annotation.ControllerEndpoint;
import com.zhang.thresh.server.system.service.ILogService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 日志模块控制器
 * @author MrZhang
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("log")
public class LogController {

    private final ILogService logService;

    /**
     * 日志列表
     * @param log
     * @param request
     * @return
     */
    @GetMapping
    public ThreshResponse logList(Log log, QueryRequest request) {
        Map<String, Object> dataTable = ThreshUtil.getDataTable(this.logService.findLogs(log, request));
        return new ThreshResponse().data(dataTable);
    }

    /**
     * 删除日志
     * @param ids
     */
    @DeleteMapping("{ids}")
    @PreAuthorize("hasAuthority('log:delete')")
    @ControllerEndpoint(exceptionMessage = "删除日志失败")
    public void deleteLogss(@NotBlank(message = "{required}") @PathVariable String ids) {
        String[] logIds = ids.split(StringConstant.COMMA);
        this.logService.deleteLogs(logIds);
    }

    /**
     * 打印日志 导出为excel
     * @param request
     * @param lg
     * @param response
     */
    @PostMapping("excel")
    @PreAuthorize("hasAuthority('log:export')")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest request, Log lg, HttpServletResponse response) {
        List<Log> logs = this.logService.findLogs(lg, request).getRecords();
        ExcelKit.$Export(Log.class, response).downXlsx(logs, false);
    }
}
