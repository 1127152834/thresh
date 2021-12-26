package com.zhang.thresh.server.test.service;

import com.zhang.thresh.common.core.entity.system.TradeLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author MrZhang
 */
public interface ITradeLogService extends IService<TradeLog> {

    /**
     * 下单并支付
     *
     * @param tradeLog 交易日志
     */
    void orderAndPay(TradeLog tradeLog);
}
