package com.zhang.thresh.server.job.controller;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.constant.StringConstant;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import com.zhang.thresh.server.job.entity.Job;
import com.zhang.thresh.server.job.service.IJobService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 定时任务控制器
 * @author MrZhang
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class JobController {

    private final IJobService jobService;

    /**
     * 查看定时任务
     * @param request
     * @param job 查询条件
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('job:view')")
    public ThreshResponse jobList(QueryRequest request, Job job) {
        Map<String, Object> dataTable = ThreshUtil.getDataTable(this.jobService.findJobs(request, job));
        return new ThreshResponse().data(dataTable);
    }

    /**
     * 校验corn表达式是否合法
     * @param cron
     * @return
     */
    @GetMapping("cron/check")
    public boolean checkCron(String cron) {
        try {
            return CronExpression.isValidExpression(cron);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 添加定时任务
     */
    @PostMapping
    @PreAuthorize("hasAuthority('job:add')")
    public void addJob(@Valid Job job) {
        this.jobService.createJob(job);
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping("{jobIds}")
    @PreAuthorize("hasAuthority('job:delete')")
    public void deleteJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringConstant.COMMA);
        this.jobService.deleteJobs(ids);
    }

    /**
     * 修改定时任务
     */
    @PutMapping
    @PreAuthorize("hasAuthority('job:update')")
    public void updateJob(@Valid Job job) {
        this.jobService.updateJob(job);
    }

    /**
     * 启动定时任务
     */
    @GetMapping("run/{jobIds}")
    @PreAuthorize("hasAuthority('job:run')")
    public void runJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.run(jobIds);
    }

    /**
     * 暂停定时任务
     */
    @GetMapping("pause/{jobIds}")
    @PreAuthorize("hasAuthority('job:pause')")
    public void pauseJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.pause(jobIds);
    }

    /**
     * 恢复定时任务
     */
    @GetMapping("resume/{jobIds}")
    @PreAuthorize("hasAuthority('job:resume')")
    public void resumeJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.resume(jobIds);
    }

    /**
     * 打印
     */
    @PostMapping("excel")
    @PreAuthorize("hasAuthority('job:export')")
    public void export(QueryRequest request, Job job, HttpServletResponse response) {
        List<Job> jobs = this.jobService.findJobs(request, job).getRecords();
        ExcelKit.$Export(Job.class, response).downXlsx(jobs, false);
    }
}
