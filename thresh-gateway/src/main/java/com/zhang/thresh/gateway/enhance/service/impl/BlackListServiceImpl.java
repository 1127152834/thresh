package com.zhang.thresh.gateway.enhance.service.impl;

import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.constant.StringConstant;
import com.zhang.thresh.common.core.utils.DateUtil;
import com.zhang.thresh.gateway.enhance.entity.BlackList;
import com.zhang.thresh.gateway.enhance.mapper.BlackListMapper;
import com.zhang.thresh.gateway.enhance.service.BlackListService;
import com.zhang.thresh.gateway.enhance.service.RouteEnhanceCacheService;
import com.zhang.thresh.gateway.enhance.utils.AddressUtil;
import com.zhang.thresh.gateway.enhance.utils.PageableExecutionUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 黑名单
 * @author MrZhang
 */
@Service
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService {

    private final RouteEnhanceCacheService routeEnhanceCacheService;
    private BlackListMapper blackListMapper;
    /**
     * 操作mongodb
     */
    private ReactiveMongoTemplate template;

    @Autowired(required = false)
    public void setBlackListMapper(BlackListMapper blackListMapper) {
        this.blackListMapper = blackListMapper;
    }

    @Autowired(required = false)
    public void setTemplate(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Flux<BlackList> findAll() {
        return blackListMapper.findAll();
    }

    @Override
    public Mono<BlackList> create(BlackList blackList) {
        blackList.setCreateTime(DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        if (StringUtils.isNotBlank(blackList.getIp())) {
            blackList.setLocation(AddressUtil.getCityInfo(blackList.getIp()));
        }
        return blackListMapper.insert(blackList).doOnSuccess(b -> routeEnhanceCacheService.saveBlackList(blackList));
    }

    @Override
    public Mono<BlackList> update(BlackList blackList) {
        return this.blackListMapper.findById(blackList.getId())
                .flatMap(b -> {
                    routeEnhanceCacheService.removeBlackList(b);
                    BeanUtils.copyProperties(blackList, b);
                    return this.blackListMapper.save(b);
                }).doOnSuccess(routeEnhanceCacheService::saveBlackList);
    }

    @Override
    public Flux<BlackList> delete(String ids) {
        String[] idArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(ids, StringConstant.COMMA);
        return blackListMapper.deleteByIdIn(Arrays.asList(idArray))
                .doOnNext(routeEnhanceCacheService::removeBlackList);
    }

    @Override
    public Flux<BlackList> findPages(QueryRequest request, BlackList blackList) {
        Query query = getQuery(blackList);
        return PageableExecutionUtil.getPages(query, request, BlackList.class, template);
    }

    @Override
    public Mono<Long> findCount(BlackList blackList) {
        Query query = getQuery(blackList);
        return template.count(query, BlackList.class);
    }

    @Override
    public Flux<BlackList> findByCondition(String ip, String requestUri, String requestMethod) {
        if (StringUtils.isBlank(ip)) {
            return blackListMapper.findByRequestUriAndRequestMethod(requestUri, requestMethod);
        }
        return blackListMapper.findByIpAndRequestUriAndRequestMethod(ip, requestUri, requestMethod);
    }

    private Query getQuery(BlackList blackList) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(blackList.getIp())) {
            criteria.and("ip").is(blackList.getIp());
        }
        if (StringUtils.isNotBlank(blackList.getRequestUri())) {
            criteria.and("requestUri").is(blackList.getRequestUri());
        }
        if (StringUtils.isNotBlank(blackList.getRequestMethod())) {
            criteria.and("requestMethod").is(blackList.getRequestMethod());
        }
        if (StringUtils.isNotBlank(blackList.getStatus())) {
            criteria.and("status").is(blackList.getStatus());
        }
        query.addCriteria(criteria);
        return query;
    }
}
