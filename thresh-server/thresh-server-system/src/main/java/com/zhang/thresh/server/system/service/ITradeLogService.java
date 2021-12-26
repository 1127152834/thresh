package com.zhang.thresh.server.system.service;

import com.zhang.thresh.common.core.entity.system.TradeLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author MrZhang
 */
public interface ITradeLogService extends IService<TradeLog> {

    /**
     * 打包并派送
     *
     * @param tradeLog 交易日志
     */
    void packageAndSend(TradeLog tradeLog);
}
