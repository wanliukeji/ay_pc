//package com.example.demo.api.mk;
//
//import com.example.demo.json.ResultJSON;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
///**
// * 属性接口
// */
//
//@Api(value = "MK属性模块", description = "MK属性模块")
//public interface MkAttrApi extends Serializable {
//
//    /**
//     * 属性添加
//     * @return
//     */
//    @ApiOperation(value = "MK属性添加接口", notes = "MK属性添加接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "uid", value = "用户ID"),
//            @ApiImplicitParam(name = "payUid", value = "收款人ID"),
//    })
//    @Transactional(rollbackFor = Exception.class)
//    @PostMapping(value = "/mk/api/bill/add")
//    public ResultJSON<?> add(
//            @RequestParam(required = false, value = "uid") String uid,
//            @RequestParam(required = false, value = "payUid") String payUid,
//            @RequestParam(required = false, value = "pm") Integer pm,
//            @RequestParam(required = false, value = "title") String title,
//            @RequestParam(required = false, value = "ftype") String ftype,
//            @RequestParam(required = false, value = "amount") BigDecimal amount,
//            @RequestParam(required = false, value = "mark") String mark,
//            @RequestParam(required = false, value = "fid") String fid
//    );
//
//    /**
//     * 属性获取
//     * @return
//     */
////    @ApiOperation(value = "MK属性所有信息接口", notes = "MK属性所有信息接口")
////    @ApiImplicitParams({})
////    @PostMapping(value = "/mk/api/bill/list")
////    public ResultJSON<?> list();
//
//    /**
//     * 属性分页获取
//     * @param limit
//     * @param row
//     * @return
//     */
//    @ApiOperation(value = "MK属性分页接口", notes = "MK属性添加接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "uid", value = "用户编号"),
//            @ApiImplicitParam(name = "limit", value = "起始页"),
//            @ApiImplicitParam(name = "row", value = "行数")
//    })
//    @PostMapping(value = "/mk/api/bill/page")
//    public ResultJSON<?> page(
//            @RequestParam(required = false, value = "uid") String uid,
//            @RequestParam(required = false, value = "limit") Integer limit,
//            @RequestParam(required = false, value = "row") Integer row);
//
//    /**
//     * 属性分页删除
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "MK属性删除接口", notes = "MK属性删除接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "ID"),
//    })
//    @PostMapping(value = "/mk/api/bill/del")
//    @Transactional(rollbackFor = Exception.class)
//    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);
//
//    /**
//     * 属性分页删除
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "MK属性详情接口", notes = "MK属性详情接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "ID"),
//    })
//    @PostMapping(value = "/mk/api/bill/getInfo")
//    @Transactional(rollbackFor = Exception.class)
//    public ResultJSON<?> getInfo(@RequestParam(required = true, value = "id") Integer id);
//
//
//}
