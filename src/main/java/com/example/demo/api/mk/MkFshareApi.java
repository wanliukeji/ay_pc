package com.example.demo.api.mk;

import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * 个人收藏分享接口
 */

@Api(value = "MK个人收藏分享模块", description = "MK个人收藏分享模块")
public interface MkFshareApi extends Serializable {

    /**
     * 个人收藏分享添加
     *
     * @return
     */
    @ApiOperation(value = "MK个人收藏分享添加接口", notes = "MK个人收藏分享添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ftype", value = "(1 收藏 2 分享)"),
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "fid", value = "房源ID")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/fshare/add")
    public ResultJSON<?> add(@RequestParam(required = false, value = "ftype") Integer ftype,
                             @RequestParam(required = false, value = "userId") String userId,
                             @RequestParam(required = false, value = "fid") String fid
    );

    /**
     * 个人收藏分享获取详情
     *
     * @return
     */
    @ApiOperation(value = "MK个人收藏分享取消接口", notes = "MK个人收藏分享取消详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "fid", value = "房源ID")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/fshare/del")
    public ResultJSON<?> del(
            @RequestParam(required = false, value = "userId") String userId,
            @RequestParam(required = false, value = "fid")String  fid
    );


    /**
     * 个人收藏分享获取详情
     *
     * @return
     */
    @ApiOperation(value = "MK个人收藏分享分页接口", notes = "MK个人收藏分享分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/fshare/page")
    public ResultJSON<?> page(
            @RequestParam(required = false, value = "limit")Integer  limit,
            @RequestParam(required = false, value = "row")Integer  row,
            @RequestParam(required = false, value = "userId") Integer userId
            );

}
