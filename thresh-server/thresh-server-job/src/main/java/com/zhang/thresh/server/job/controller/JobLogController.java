package com.zhang.thresh.server.job.controller;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.constant.StringConstant;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import com.zhang.thresh.server.job.entity.JobLog;
import com.zhang.thresh.server.job.service.IJobLogService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 任务日志控制器
 * @author MrZhang
 */
@Slf4j
@Validated
@RestController
@RequestMapping("log")
@RequiredArgsConstructor
public class JobLogController {

    private final IJobLogService jobLogService;

    /**
     * 查看日志
     * @param request
     * @param log
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('job:log:view')")
    public ThreshResponse jobLogList(QueryRequest request, JobLog log) {
        Map<String, Object> dataTable = ThreshUtil.getDataTable(this.jobLogService.findJobLogs(request, log));
        return new ThreshResponse().data(dataTable);
    }

    /**
     * 删除日志
     * @param jobIds
     */
    @DeleteMapping("{jobIds}")
    @PreAuthorize("hasAuthority('job:log:delete')")
    public void deleteJobLog(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringConstant.COMMA);
        this.jobLogService.deleteJobLogs(ids);
    }

    /**
     * 打印日志
     * @param request
     * @param jobLog
     * @param response
     */
    @GetMapping("excel")
    @PreAuthorize("hasAuthority('job:log:export')")
    public void export(QueryRequest request, JobLog jobLog, HttpServletResponse response) {
        List<JobLog> jobLogs = this.jobLogService.findJobLogs(request, jobLog).getRecords();
        ExcelKit.$Export(JobLog.class, response).downXlsx(jobLogs, false);
    }
}
