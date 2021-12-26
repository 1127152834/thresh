package com.zhang.thresh.server.system.controller;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.constant.StringConstant;
import com.zhang.thresh.common.core.entity.system.LoginLog;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import com.zhang.thresh.server.system.annotation.ControllerEndpoint;
import com.zhang.thresh.server.system.service.ILoginLogService;
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
 * 登录日志模块控制器
 * @author MrZhang
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("loginLog")
public class LoginLogController {

    private final ILoginLogService loginLogService;

    /**
     * 登录日志列表
     * @param loginLog
     * @param request
     * @return
     */
    @GetMapping
    public ThreshResponse loginLogList(LoginLog loginLog, QueryRequest request) {
        Map<String, Object> dataTable = ThreshUtil.getDataTable(this.loginLogService.findLoginLogs(loginLog, request));
        return new ThreshResponse().data(dataTable);
    }

    /**
     * 通过用户名获取用户最近7次登录日志
     * @return
     */
    @GetMapping("currentUser")
    public ThreshResponse getUserLastSevenLoginLogs() {
        String currentUsername = ThreshUtil.getCurrentUsername();
        List<LoginLog> userLastSevenLoginLogs = this.loginLogService.findUserLastSevenLoginLogs(currentUsername);
        return new ThreshResponse().data(userLastSevenLoginLogs);
    }

    /**
     * 删除登录日志
     * @param ids
     */
    @DeleteMapping("{ids}")
    @PreAuthorize("hasAuthority('loginlog:delete')")
    @ControllerEndpoint(operation = "删除登录日志", exceptionMessage = "删除登录日志失败")
    public void deleteLogs(@NotBlank(message = "{required}") @PathVariable String ids) {
        String[] loginLogIds = ids.split(StringConstant.COMMA);
        this.loginLogService.deleteLoginLogs(loginLogIds);
    }

    /**
     * 导出登录日志数据
     * @param request
     * @param loginLog
     * @param response
     */
    @PostMapping("excel")
    @PreAuthorize("hasAuthority('loginlog:export')")
    @ControllerEndpoint(operation = "导出登录日志数据", exceptionMessage = "导出Excel失败")
    public void export(QueryRequest request, LoginLog loginLog, HttpServletResponse response) {
        List<LoginLog> loginLogs = this.loginLogService.findLoginLogs(loginLog, request).getRecords();
        ExcelKit.$Export(LoginLog.class, response).downXlsx(loginLogs, false);
    }
}
