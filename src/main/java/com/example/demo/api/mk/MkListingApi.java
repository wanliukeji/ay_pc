package com.example.demo.api.mk;

import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * MK房源模块接口
 */

@Api(value = "MK房源模块", description = "MK房源模块")
public interface MkListingApi extends Serializable {

    /**
     * 房源模块添加
     * @return
     */
    @ApiOperation(value = "MK房源模块添加接口", notes = "MK房源模块添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apartmentId", value = "小区ID/公寓ID(mk_apartment_id)"),
            @ApiImplicitParam(name = "dong", value = "栋/幢 (单位)"),
            @ApiImplicitParam(name = "unit", value = "单元(几单元)"),
            @ApiImplicitParam(name = "roomNo", value = "房门号"),
            @ApiImplicitParam(name = "area", value = "房屋面积"),
            @ApiImplicitParam(name = "floors", value = "楼层(第几层)"),
            @ApiImplicitParam(name = "unitType", value = "户型"),
            @ApiImplicitParam(name = "depositMethod", value = "押金方式"),
            @ApiImplicitParam(name = "payDay", value = "房租支付时间(账单前几天收租)"),
            @ApiImplicitParam(name = "otherAmount", value = "其他费用"),
            @ApiImplicitParam(name = "longType", value = "起租时长"),
            @ApiImplicitParam(name = "mPay", value = "月付(mk_bounty_id) -付款方式及赏金"),
            @ApiImplicitParam(name = "jPay", value = "季付(mk_bounty_id) -付款方式及赏金"),
            @ApiImplicitParam(name = "bPay", value = "半年付(mk_bounty_id) -付款方式及赏金"),
            @ApiImplicitParam(name = "nPay", value = "年付(mk_bounty_id) -付款方式及赏金"),
            @ApiImplicitParam(name = "kdCosts", value = "宽带费"),
            @ApiImplicitParam(name = "dCosts", value = "电费"),
            @ApiImplicitParam(name = "sCosts", value = "水费"),
            @ApiImplicitParam(name = "wyCosts", value = "物业费"),
            @ApiImplicitParam(name = "tcCosts", value = "停车费"),
            @ApiImplicitParam(name = "rqCosts", value = "燃气费"),
            @ApiImplicitParam(name = "decoration", value = "装修程度(mk_facility_id)"),
            @ApiImplicitParam(name = "towards", value = "朝向(mk_facility_id)"),
            @ApiImplicitParam(name = "supporting", value = "配套(mk_facility_id)"),
            @ApiImplicitParam(name = "features", value = "房屋特色(mk_facility_id)"),
            @ApiImplicitParam(name = "expectations", value = "期望(mk_facility_id)"),
            @ApiImplicitParam(name = "fidentity", value = "身份类型(1 个人 2 机构/企业)"),
            @ApiImplicitParam(name = "userId", value = "用户ID(mk_user_id)"),
            @ApiImplicitParam(name = "fileId", value = "文件ID(mk_file_id)"),
            @ApiImplicitParam(name = "proCode", value = "省份编号(mk_address_id)"),
            @ApiImplicitParam(name = "cityCode", value = "城市编号(mk_address_id)"),
            @ApiImplicitParam(name = "areaCode", value = "县区编号(mk_address_id)"),
            @ApiImplicitParam(name = "addrName", value = "具体地址"),
            @ApiImplicitParam(name = "leaseType", value = "整租类型(1整租,2 合租)"),
            @ApiImplicitParam(name = "labeles", value = "房源标签(mk_facility_id)"),
            @ApiImplicitParam(name = "x", value = "坐标经度(mk_pointxy_id)"),
            @ApiImplicitParam(name = "y", value = "坐标纬度(mk_pointxy_id)"),
            @ApiImplicitParam(name = "floosSum", value = "楼层总数"),
            @ApiImplicitParam(name = "remark", value = "备注"),
            @ApiImplicitParam(name = "zAmount", value = "租金"),
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/mkListing/add")
    @ResponseBody
    public ResultJSON<?> add(
            @RequestParam(required = false, name = "apartmentId") Long apartmentId,
            @RequestParam(required = false, name = "dong") Integer dong,
            @RequestParam(required = false, name = "unit") Integer unit,
            @RequestParam(required = false, name = "roomNo") String roomNo,
            @RequestParam(required = false, name = "area") Double area,
            @RequestParam(required = false, name = "floors") Integer floors,
            @RequestParam(required = false, name = "unitType") String unitType,
            @RequestParam(required = false, name = "depositMethod") String depositMethod,
            @RequestParam(required = false, name = "payDay") Integer payDay,
            @RequestParam(required = false, name = "otherAmount") String otherAmount,
            @RequestParam(required = false, name = "longType") String longType,
            @RequestParam(required = false, name = "mPay") String mPay,
            @RequestParam(required = false, name = "jPay") String jPay,
            @RequestParam(required = false, name = "bPay") String bPay,
            @RequestParam(required = false, name = "nPay") String nPay,
            @RequestParam(required = false, name = "kdCosts") BigDecimal kdCosts,
            @RequestParam(required = false, name = "dCosts") BigDecimal dCosts,
            @RequestParam(required = false, name = "sCosts") BigDecimal sCosts,
            @RequestParam(required = false, name = "wyCosts") BigDecimal wyCosts,
            @RequestParam(required = false, name = "tcCosts") BigDecimal tcCosts,
            @RequestParam(required = false, name = "rqCosts") BigDecimal rqCosts,
            @RequestParam(required = false, name = "decoration") String decoration,
            @RequestParam(required = false, name = "towards") String towards,
            @RequestParam(required = false, name = "supporting") String supporting,
            @RequestParam(required = false, name = "features") String features,
            @RequestParam(required = false, name = "expectations") String expectations,
            @RequestParam(required = false, name = "fidentity") Integer fidentity,
            @RequestParam(required = true, name = "userId") String userId,
            @RequestParam(required = false, name = "fileId") Integer fileId,
            @RequestParam(required = false, name = "proCode") String proCode,
            @RequestParam(required = false, name = "cityCode") String cityCode,
            @RequestParam(required = false, name = "areaCode") String areaCode,
            @RequestParam(required = false, name = "addrName") String addrName,
            @RequestParam(required = false, name = "leaseType") Integer leaseType,
            @RequestParam(required = false, name = "labeles") String labeles,
            @RequestParam(required = false, name = "x") String x,
            @RequestParam(required = false, name = "y") String y,
            @RequestParam(required = false, name = "floosSum") Integer floosSum,
            @RequestParam(required = false, name = "remark") String remark,
            @RequestParam(required = true, name = "zAmount") BigDecimal zAmount,
            @RequestParam(required = true, name = "yAmount") BigDecimal yAmount
            );

    /**
     * MK房源模块分页获取
     * leaseType 1 整租 2 合租 3短租
     * @param limit
     * @param row
     * @return
     */
    @ApiOperation(value = "MK房源分页接口", notes = "MK房源分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "leaseType", value = "租房类型"),
            @ApiImplicitParam(name = "areaCode", value = "县区编号"),
            @ApiImplicitParam(name = "maxPrice", value = "最大价格"),
            @ApiImplicitParam(name = "minPrice", value = "最小价格"),
            @ApiImplicitParam(name = "unitType", value = "户型"),
            @ApiImplicitParam(name = "limit", value = "起始页"),
            @ApiImplicitParam(name = "row", value = "行数"),
            @ApiImplicitParam(name = "longType", value = "租期"),
            @ApiImplicitParam(name = "maxArea", value = "最大面积"),
            @ApiImplicitParam(name = "minArea", value = "最小面积"),
            @ApiImplicitParam(name = "fidentity", value = "房东类型"),
            @ApiImplicitParam(name = "apartmentId", value = "品牌公寓"),
            @ApiImplicitParam(name = "decoration", value = "装修程度"),
            @ApiImplicitParam(name = "jstatus", value = "精品房(0 否, 1 是)"),
            @ApiImplicitParam(name = "tstatus", value = "推荐房(0 否, 1 是)"),
            @ApiImplicitParam(name = "val", value = "搜索内容"),
            @ApiImplicitParam(name = "id", value = "房源ID")
    })
    @PostMapping(value = "/mk/api/listing/page")
    public ResultJSON<?> page(
            @RequestParam(required = false, value = "leaseType") Integer leaseType,
            @RequestParam(required = false, value = "areaCode") String areaCode,
            @RequestParam(required = false, value = "maxPrice") Integer maxPrice,
            @RequestParam(required = false, value = "minPrice") Integer minPrice,
            @RequestParam(required = false, value = "unitType") String unitType,
            @RequestParam(required = false, value = "limit") Integer limit,
            @RequestParam(required = false, value = "row") Integer row,
            @RequestParam(required = false, value = "longType") String longType,
            @RequestParam(required = false, value = "maxArea") Double maxArea,
            @RequestParam(required = false, value = "minArea") Double minArea,
            @RequestParam(required = false, value = "fidentity") Integer fidentity,
            @RequestParam(required = false, value = "apartmentId") Integer apartmentId,
            @RequestParam(required = false, value = "decoration") Integer decoration,
            @RequestParam(required = false, value = "jstatus") Integer jstatus,
            @RequestParam(required = false, value = "tstatus") Integer tstatus,
            @RequestParam(required = false, value = "val") String val,
            @RequestParam(required = false, value = "id") Integer id
    );


    /**
     * MK房源详情获取
     * @param id
     * @return
     */
    @ApiOperation(value = "MK房源详情获取接口", notes = "MK房源详情获取添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "房源ID")
    })
    @PostMapping(value = "/mk/api/listing/getInfo")
    public ResultJSON<?> getInfo(
            @RequestParam(required = false, value = "id") Integer id
    );


    /**
     * 房源模块添加
     * @return
     */
    @ApiOperation(value = "MK房源模块修改接口", notes = "MK房源修改添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apartmentId", value = "小区ID/公寓ID(mk_apartment_id)"),
            @ApiImplicitParam(name = "dong", value = "栋/幢 (单位)"),
            @ApiImplicitParam(name = "unit", value = "单元(几单元)"),
            @ApiImplicitParam(name = "roomNo", value = "房门号"),
            @ApiImplicitParam(name = "area", value = "房屋面积"),
            @ApiImplicitParam(name = "floors", value = "楼层(第几层)"),
            @ApiImplicitParam(name = "unitType", value = "户型"),
            @ApiImplicitParam(name = "depositMethod", value = "押金方式"),
            @ApiImplicitParam(name = "payDay", value = "房租支付时间(账单前几天收租)"),
            @ApiImplicitParam(name = "otherAmount", value = "其他费用"),
            @ApiImplicitParam(name = "longType", value = "起租时长"),
            @ApiImplicitParam(name = "mPay", value = "月付(mk_bounty_id) -付款方式及赏金"),
            @ApiImplicitParam(name = "jPay", value = "季付(mk_bounty_id) -付款方式及赏金"),
            @ApiImplicitParam(name = "bPay", value = "半年付(mk_bounty_id) -付款方式及赏金"),
            @ApiImplicitParam(name = "nPay", value = "年付(mk_bounty_id) -付款方式及赏金"),
            @ApiImplicitParam(name = "kdCosts", value = "宽带费"),
            @ApiImplicitParam(name = "dCosts", value = "电费"),
            @ApiImplicitParam(name = "sCosts", value = "水费"),
            @ApiImplicitParam(name = "wyCosts", value = "物业费"),
            @ApiImplicitParam(name = "tcCosts", value = "停车费"),
            @ApiImplicitParam(name = "rqCosts", value = "燃气费"),
            @ApiImplicitParam(name = "decoration", value = "装修程度(mk_facility_id)"),
            @ApiImplicitParam(name = "towards", value = "朝向(mk_facility_id)"),
            @ApiImplicitParam(name = "supporting", value = "配套(mk_facility_id)"),
            @ApiImplicitParam(name = "features", value = "房屋特色(mk_facility_id)"),
            @ApiImplicitParam(name = "expectations", value = "期望(mk_facility_id)"),
            @ApiImplicitParam(name = "fidentity", value = "身份类型(1 个人 2 机构/企业)"),
            @ApiImplicitParam(name = "fileId", value = "文件ID(mk_file_id)"),
            @ApiImplicitParam(name = "proCode", value = "省份编号(mk_address_id)"),
            @ApiImplicitParam(name = "cityCode", value = "城市编号(mk_address_id)"),
            @ApiImplicitParam(name = "areaCode", value = "县区编号(mk_address_id)"),
            @ApiImplicitParam(name = "addrName", value = "具体地址"),
            @ApiImplicitParam(name = "leaseType", value = "整租类型(1整租,2 合租)"),
            @ApiImplicitParam(name = "labeles", value = "房源标签(mk_facility_id)"),
            @ApiImplicitParam(name = "x", value = "坐标经度(mk_pointxy_id)"),
            @ApiImplicitParam(name = "y", value = "坐标纬度(mk_pointxy_id)"),
            @ApiImplicitParam(name = "floosSum", value = "楼层总数"),
            @ApiImplicitParam(name = "remark", value = "备注"),
            @ApiImplicitParam(name = "zAmount", value = "租金"),
            @ApiImplicitParam(name = "fid", value = "房源ID")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/mkListing/edit")
    @ResponseBody
    public ResultJSON<?> edit(
            @RequestParam(required = false, name = "apartmentId") Long apartmentId,
            @RequestParam(required = false, name = "dong") Integer dong,
            @RequestParam(required = false, name = "unit") Integer unit,
            @RequestParam(required = false, name = "roomNo") String roomNo,
            @RequestParam(required = false, name = "area") Double area,
            @RequestParam(required = false, name = "floors") Integer floors,
            @RequestParam(required = false, name = "unitType") String unitType,
            @RequestParam(required = false, name = "depositMethod") String depositMethod,
            @RequestParam(required = false, name = "payDay") Integer payDay,
            @RequestParam(required = false, name = "otherAmount") String otherAmount,
            @RequestParam(required = false, name = "longType") String longType,
            @RequestParam(required = false, name = "mPay") String mPay,
            @RequestParam(required = false, name = "jPay") String jPay,
            @RequestParam(required = false, name = "bPay") String bPay,
            @RequestParam(required = false, name = "nPay") String nPay,
            @RequestParam(required = false, name = "kdCosts") BigDecimal kdCosts,
            @RequestParam(required = false, name = "dCosts") BigDecimal dCosts,
            @RequestParam(required = false, name = "sCosts") BigDecimal sCosts,
            @RequestParam(required = false, name = "wyCosts") BigDecimal wyCosts,
            @RequestParam(required = false, name = "tcCosts") BigDecimal tcCosts,
            @RequestParam(required = false, name = "rqCosts") BigDecimal rqCosts,
            @RequestParam(required = false, name = "decoration") String decoration,
            @RequestParam(required = false, name = "towards") String towards,
            @RequestParam(required = false, name = "supporting") String supporting,
            @RequestParam(required = false, name = "features") String features,
            @RequestParam(required = false, name = "expectations") String expectations,
            @RequestParam(required = false, name = "fidentity") Integer fidentity,
            @RequestParam(required = false, name = "fileId") Integer fileId,
            @RequestParam(required = false, name = "proCode") String proCode,
            @RequestParam(required = false, name = "cityCode") String cityCode,
            @RequestParam(required = false, name = "areaCode") String areaCode,
            @RequestParam(required = false, name = "addrName") String addrName,
            @RequestParam(required = false, name = "leaseType") Integer leaseType,
            @RequestParam(required = false, name = "labeles") String labeles,
            @RequestParam(required = false, name = "x") String x,
            @RequestParam(required = false, name = "y") String y,
            @RequestParam(required = false, name = "floosSum") Integer floosSum,
            @RequestParam(required = false, name = "remark") String remark,
            @RequestParam(required = false, name = "zAmount") BigDecimal zAmount,
            @RequestParam(required = false, name = "yAmount") BigDecimal yAmount,
            @RequestParam(required = true, name = "fid") String fid
    );


    /**
     * MK房源模块详情获取
     * leaseType 1 整租 2 合租 3短租
     * @return
     */
//    @ApiOperation(value = "MK房屋基本配置分页接口", notes = "MK房屋基本配置添加接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "房源ID")
//    })
//    @PostMapping(value = "/mk/api/listing/page")
//    public ResultJSON<?> page(
//            @RequestParam(required = false, value = "leaseType") Integer leaseType
//    );

//    /**
//     * 收入明细分页删除
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "MK房屋基本配置删除接口", notes = "MK房屋基本配置删除接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "ID"),
//    })
//    @PostMapping(value = "/mk/api/facility/del")
//    @Transactional
//    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);
//
//
//    /**
//     * 收入明细修改
//     *
//     * @param ftype
//     * @param name
//     * @param creatCode
//     * @return
//     */
//    @ApiOperation(value = "MK房屋基本配置添加接口", notes = "MK房屋基本配置添加接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "设施编号"),
//            @ApiImplicitParam(name = "ftype", value = "设施类型"),
//            @ApiImplicitParam(name = "name", value = "设施名"),
//            @ApiImplicitParam(name = "creatCode", value = "创建人")
//    })
//    @Transactional
//    @PostMapping(value = "/mk/api/facility/edit")
//    public ResultJSON<?> edit(
//            @RequestParam("id") Integer id,
//            @RequestParam("ftype") Integer ftype,
//            @RequestParam("name") String name,
//            @RequestParam("creatCode") String creatCode);

}
