package com.zhang.thresh.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import com.zhang.thresh.common.core.converter.TimeConverter;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门信息表
 * @author MrZhang
 */
@Data
@TableName("t_dept")
@Excel("部门信息表")
public class Dept implements Serializable {

    /**
     * 最高级别部门ID
     */
    public static final Long TOP_DEPT_ID = 0L;
    private static final long serialVersionUID = -7790334862410409053L;
    /**
     * 部门ID
     */
    @TableId(value = "DEPT_ID", type = IdType.AUTO)
    private Long deptId;
    /**
     * 上级部门ID
     */
    @TableField(value = "PARENT_ID")
    private Long parentId;
    /**
     * 部门名称
     */
    @NotBlank(message = "{required}")
    @Size(max = 20, message = "{noMoreThan}")
    @ExcelField(value = "部门名称")
    private String deptName;
    /**
     * 序号
     */
    @TableField(value = "ORDER_NUM")
    private Integer orderNum;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "MODIFY_TIME")
    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date modifyTime;

    private transient String createTimeFrom;
    private transient String createTimeTo;

}
