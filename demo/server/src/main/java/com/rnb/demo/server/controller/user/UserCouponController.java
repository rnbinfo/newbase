package com.rnb.demo.server.controller.user;

import com.rnb.demo.entity.remote.tuanyou.CouponEntity;
import com.rnb.demo.server.controller.AbstractController;
import com.rnb.demo.server.remote.tuanyou.TuanyouRemoteService;
import com.rnb.newbase.controller.api.HttpFrontRequest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户优惠券类")
@RestController
@RequestMapping("user")
public class UserCouponController extends AbstractController {
    @Resource
    private TuanyouRemoteService tuanyouRemoteService;

    @PostMapping("queryCoupons")
    public QueryCouponsResponse queryCoupons(@Valid @RequestBody HttpFrontRequest<QueryUserCouponsRequest> request) {
        String token = getToken(request.getBody().getAccessorId(), request.getBody().getMobile());
        Map<String, String> paramters = new HashMap<>();
        paramters.put("token", token);
        paramters.put("mobilePhone", request.getBody().getMobile());
        List<CouponEntity> coupons = tuanyouRemoteService.queryCoupons(paramters);
        return QueryCouponsResponse.generateResponse(coupons);
    }
}
