package com.zhang.thresh.common.core.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.constant.ThreshConstant;
import org.apache.commons.lang3.StringUtils;

/**
 * 处理排序工具类
 *
 * @author MrZhang
 */
public abstract class SortUtil {

    /**
     * 处理排序（分页情况下） for mybatis-plus
     *
     * @param request           QueryRequest
     * @param page              Page
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handlePageSort(QueryRequest request, Page<?> page, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        //设置当前页
        page.setCurrent(request.getPageNum());
        //设置页容量
        page.setSize(request.getPageSize());
        //获取排序字段
        String sortField = request.getField();
        //是否开启驼峰转下划线
        if (camelToUnderscore) {
            //将排序字段进行驼峰转下划线
            sortField = com.zhang.thresh.common.core.utils.ThreshUtil.camelToUnderscore(sortField);
            defaultSort = com.zhang.thresh.common.core.utils.ThreshUtil.camelToUnderscore(defaultSort);
        }
        //是否传入了排序字段
        if (StringUtils.isNotBlank(request.getField())
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(request.getField(), "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), ThreshConstant.ORDER_DESC)) {
                //根据传入的字段倒序
                page.addOrder(OrderItem.desc(sortField));
            } else {
                //根据传入的字段正序
                page.addOrder(OrderItem.asc(sortField));
            }
        } else {
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, ThreshConstant.ORDER_DESC)) {
                    //根据默认字段倒序
                    page.addOrder(OrderItem.desc(defaultSort));
                } else {
                    //根据默认字段正序
                    page.addOrder(OrderItem.asc(defaultSort));
                }
            }
        }
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request QueryRequest
     * @param page    Page
     */
    public static void handlePageSort(QueryRequest request, Page<?> page) {
        handlePageSort(request, page, null, null, false);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param page              Page
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handlePageSort(QueryRequest request, Page<?> page, boolean camelToUnderscore) {
        handlePageSort(request, page, null, null, camelToUnderscore);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param wrapper           wrapper
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper<?> wrapper, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        String sortField = request.getField();
        if (camelToUnderscore) {
            sortField = com.zhang.thresh.common.core.utils.ThreshUtil.camelToUnderscore(sortField);
            defaultSort = com.zhang.thresh.common.core.utils.ThreshUtil.camelToUnderscore(defaultSort);
        }
        if (StringUtils.isNotBlank(request.getField())
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(request.getField(), "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), ThreshConstant.ORDER_DESC)) {
                wrapper.orderByDesc(sortField);
            } else {
                wrapper.orderByAsc(sortField);
            }
        } else {
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, ThreshConstant.ORDER_DESC)) {
                    wrapper.orderByDesc(defaultSort);
                } else {
                    wrapper.orderByAsc(defaultSort);
                }
            }
        }
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request QueryRequest
     * @param wrapper wrapper
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper<?> wrapper) {
        handleWrapperSort(request, wrapper, null, null, false);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param wrapper           wrapper
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper<?> wrapper, boolean camelToUnderscore) {
        handleWrapperSort(request, wrapper, null, null, camelToUnderscore);
    }
}
