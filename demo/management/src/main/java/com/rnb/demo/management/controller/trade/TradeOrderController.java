package com.rnb.demo.management.controller.trade;

import com.rnb.demo.entity.api.management.trade.QueryTradeOrdersRequest;
import com.rnb.demo.entity.po.trade.TradeOrder;
import com.rnb.demo.management.controller.AbstractController;
import com.rnb.demo.service.service.trade.TradeOrderService;
import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.controller.api.HttpPaginationCondition;
import com.rnb.newbase.controller.api.HttpPaginationRepertory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public HttpPaginationRepertory<TradeOrder> queryTradeOrders(@Validated @RequestBody HttpFrontRequest<HttpPaginationCondition<QueryTradeOrdersRequest>> request) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("accessorId", request.getBody().getCondition().getAccessorId());
        condition.put("startTime", request.getBody().getCondition().getStartTime());
        condition.put("endtime", request.getBody().getCondition().getEndTime());
        Map<String, String> sorts = new HashMap<>();
        sorts.put("id", "DESC");
        List<TradeOrder> tradeOrders = tradeOrderService.findOrders(request.getBody().getCurrentPage(), request.getBody().getPageSize(), condition, sorts);
        return new HttpPaginationRepertory<>(tradeOrders);
    }
}
