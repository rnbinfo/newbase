package com.rnb.demo.accessor.controller.trade;

import com.rnb.demo.accessor.controller.AbstractController;
import com.rnb.demo.entity.api.management.trade.QueryTradeOrdersRequest;
import com.rnb.demo.entity.po.noauth.NoauthOperator;
import com.rnb.demo.entity.po.trade.TradeOrder;
import com.rnb.demo.service.service.trade.TradeOrderService;
import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.controller.api.HttpPaginationCondition;
import com.rnb.newbase.controller.api.HttpPaginationRepertory;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.security.resolver.GetUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "交易管理 - 交易查询类")
@RestController
@RequestMapping("trade")
public class TradeOrderController extends AbstractController {
    @Resource
    private TradeOrderService tradeOrderService;

    @ApiOperation(value = "分页查询加油交易")
    @PostMapping("queryTradeOrders")
    public HttpPaginationRepertory<TradeOrder> queryTradeOrders(@Valid @RequestBody HttpFrontRequest<HttpPaginationCondition<QueryTradeOrdersRequest>> request,
                                                                @ApiIgnore @GetUser SystemUser systemUser) {
        NoauthOperator accessorOperator = findOperatorInfo(systemUser);
        Map<String, Object> condition = new HashMap<>();
        condition.put("accessorId", accessorOperator.getAccessorId());
        condition.put("startTime", request.getBody().getCondition().getStartTime());
        condition.put("endTime", request.getBody().getCondition().getEndTime());
        Map<String, String> sorts = new HashMap<>();
        sorts.put("id", "DESC");
        List<TradeOrder> tradeOrders = tradeOrderService.findOrders(request.getBody().getCurrentPage(), request.getBody().getPageSize(), condition, sorts);
        return new HttpPaginationRepertory<>(tradeOrders);
    }
}
