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
import java.math.BigDecimal;

/**
 * 赏金接口
 */

@Api(value = "MK赏金模块", description = "MK赏金模块")
public interface MkBountyApi extends Serializable {

    /**
     * 赏金添加
     * @return
     */
    @ApiOperation(value = "MK赏金添加接口", notes = "MK赏金添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "proportion", value = "租金比例"),
            @ApiImplicitParam(name = "bountyType", value = "租金类型"),
            @ApiImplicitParam(name = "userId", value = "创建人"),
            @ApiImplicitParam(name = "details", value = "赏金详情"),
            @ApiImplicitParam(name = "amount", value = "租金"),
            @ApiImplicitParam(name = "fstatus", value = "开启状态(0 关闭 1 开启)")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/bounty/add")
    public ResultJSON<?> add(@RequestParam(required = false, value = "proportion") Double proportion,
                             @RequestParam(required = false, value = "bountyType") String bountyType,
                             @RequestParam(required = false, value = "userId") String userId,
                             @RequestParam(required = false, value = "details") String details,
                             @RequestParam(required = false, value = "amount") BigDecimal amount,
                             @RequestParam(required = false, value = "fstatus") Integer fstatus
    );

//    /**
//     * 赏金获取
//     * @return
//     */
//    @ApiOperation(value = "MK赏金所有信息接口", notes = "MK赏金所有信息接口")
//    @ApiImplicitParams({})
//    @PostMapping(value = "/mk/api/apart/list")
//    public ResultJSON<?> list();

    /**
     * 赏金获取
     * @return
     */
    @ApiOperation(value = "MK赏金获取接口", notes = "MK赏金获取接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号")
    })
    @PostMapping(value = "/mk/api/bounty/list")
    public ResultJSON<?> list(
            @RequestParam(required = false, value = "userId") String userId
    );
//
//    /**
//     * 赏金分页删除
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "MK赏金删除接口", notes = "MK赏金删除接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "ID"),
//    })
//    @PostMapping(value = "/mk/api/apart/del")
//    @Transactional(rollbackFor = Exception.class)
//    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);
//
//
//    /**
//     * 赏金修改
//     *
//     * @param ftype
//     * @param name
//     * @param creatCode
//     * @return
//     */
//    @ApiOperation(value = "MK赏金添加接口", notes = "MK赏金添加接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "设施编号"),
//            @ApiImplicitParam(name = "ftype", value = "设施类型"),
//            @ApiImplicitParam(name = "name", value = "设施名"),
//            @ApiImplicitParam(name = "creatCode", value = "创建人")
//    })
//    @Transactional(rollbackFor = Exception.class)
//    @PostMapping(value = "/mk/api/apart/edit")
//    public ResultJSON<?> edit(
//            @RequestParam("id") Integer id,
//            @RequestParam("ftype") Integer ftype,
//            @RequestParam("name") String name,
//            @RequestParam("creatCode") String creatCode);

}
