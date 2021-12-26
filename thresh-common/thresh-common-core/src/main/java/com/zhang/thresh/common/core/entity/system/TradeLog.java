package com.zhang.thresh.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhang.thresh.common.core.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 交易通知  用于测试分布式事务
 * @author MrZhang
 */
@Data
@TableName("t_trade_log")
public class TradeLog implements Serializable {

    private static final long serialVersionUID = 3902838426348137002L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("GOODS_ID")
    private String goodsId;
    @TableField("GOODS_NAME")
    private String goodsName;
    @TableField("STATUS")
    private String status;
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = DateUtil.FULL_TIME_SPLIT_PATTERN)
    private Date createTime;
}
