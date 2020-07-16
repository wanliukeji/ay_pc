package com.example.demo.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(value = "MK微信小程序支付模块", description = "MK微信小程序支付模块")
@Controller
@Slf4j
public class WeChatPayController {
//    private final String MCH_ID = "填写商户号";//商户号
//    private final String SPBILL_CREATE_IP = "填写终端IP";//终端IP
//    private final String NOTIFY_URL = "域名/weixin/paycallback.do";//通知地址
//    private final String TRADE_TYPE = "JSAPI";//交易类型
//    private final String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单API接口链接
//    private final String KEY = "&key=填写商户支付密钥"; // 商户支付密钥
//    private final String APPID = "填写小程序AppId";


    //    private final String MCH_ID = "填写商户号";//商户号
//    private final String SPBILL_CREATE_IP = "119.45.50.208";//终端IP
    private final String NOTIFY_URL = "/weixin/paycallback.do";//通知地址
    private final String TRADE_TYPE = "JSAPI";//交易类型
    private final String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单API接口链接
//    private final String KEY = "&key=填写商户支付密钥"; // 商户支付密钥
//    private final String APPID = "1400399759";

    /**
     * @param openId
     * @param total_fee 订单总金额，单位为分。
     * @param body      商品简单描述，该字段请按照规范传递。 例：腾讯充值中心-心悦会员充值
     * @return
     * @throws UnsupportedEncodingException
     * @throws DocumentException
     */

    @ApiOperation(value = "MK微信小程序支付(统一下单接口)接口", notes = "MK微信小程序支付(统一下单接口  (out_trade_no -> 最终生成订单号))接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", required = true, value = "用户openid"),
            @ApiImplicitParam(name = "total_fee", required = true, value = "商品金额(整数)"),
            @ApiImplicitParam(name = "body", required = true, value = "商品描述"),
            @ApiImplicitParam(name = "mch_id", required = true, value = "商户号"),
            @ApiImplicitParam(name = "key", required = true, value = "商户支付密钥"),
            @ApiImplicitParam(name = "appid", required = true, value = "小程序ID"),
            @ApiImplicitParam(name = "spbill_create_ip", required = true, value = "用户终端IP"),
    })

    @PostMapping("/mk/api/wechat/pay")
    @ResponseBody
    public ResultJSON<?> payment(@RequestParam(required = true) String openId,
                                 @RequestParam(required = true) String total_fee,
                                 @RequestParam(required = true) String body,
                                 @RequestParam(required = true) String mch_id,
                                 @RequestParam(required = true) String key,
                                 @RequestParam(required = true) String appid,
                                 @RequestParam(required = true) String spbill_create_ip
    ) {
        JSONObject JsonObject = new JSONObject();
        try {
            body = new String(body.getBytes("UTF-8"), "ISO-8859-1");
            String nonce_str = UUIDHexGenerator.generate();//随机字符串
            String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String code = PayUtil.createCode(8);
            String out_trade_no = mch_id + today + code;//商户订单号

            String openid = openId;//用户标识
            PaymentDto paymentPo = new PaymentDto();
            paymentPo.setAppid(appid);
            paymentPo.setMch_id(mch_id);
            paymentPo.setNonce_str(nonce_str);
            String newbody = new String(body.getBytes("ISO-8859-1"), "UTF-8");//以utf-8编码放入paymentPo，微信支付要求字符编码统一采用UTF-8字符编码
            paymentPo.setBody(newbody);
            paymentPo.setOut_trade_no(out_trade_no);
            paymentPo.setTotal_fee(total_fee);
            paymentPo.setSpbill_create_ip(spbill_create_ip);
            paymentPo.setNotify_url(NOTIFY_URL);
            paymentPo.setTrade_type(TRADE_TYPE);
            paymentPo.setOpenid(openid);
            // 把请求参数打包成数组
            Map<String, Object> sParaTemp = new HashMap();
            sParaTemp.put("appid", paymentPo.getAppid());
            sParaTemp.put("mch_id", paymentPo.getMch_id());
            sParaTemp.put("nonce_str", paymentPo.getNonce_str());
            sParaTemp.put("body", paymentPo.getBody());
            sParaTemp.put("out_trade_no", paymentPo.getOut_trade_no());
            sParaTemp.put("total_fee", paymentPo.getTotal_fee());
            sParaTemp.put("spbill_create_ip", paymentPo.getSpbill_create_ip());
            sParaTemp.put("notify_url", paymentPo.getNotify_url());
            sParaTemp.put("trade_type", paymentPo.getTrade_type());
            sParaTemp.put("openid", paymentPo.getOpenid());
            // 除去数组中的空值和签名参数
            Map sPara = PayUtil.paraFilter(sParaTemp);
            String prestr = PayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名
            String mysign = PayUtil.sign(prestr, "&key=" + key, "utf-8").toUpperCase();
            paymentPo.setSign(mysign);
            //打包要发送的xml
            String respXml = XmlUtil.messageToXML(paymentPo);
            // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
            respXml = respXml.replace("__", "_");
            String param = respXml;
            //String result = SendRequestForUrl.sendRequest(url, param);//发起请求
            String result = PayUtil.httpRequest(URL, "POST", param);
            System.out.println("请求微信预支付接口，返回 result：" + result);
            // 将解析结果存储在Map中
            Map map = new HashMap();
            InputStream in = new ByteArrayInputStream(result.getBytes());
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            for (Element element : elementList) {
                map.put(element.getName(), element.getText());
            }

            // 统一下单 保存下单数据
//            Map<String, String> resMap = xcxPayment(out_trade_no, total_fee, openId);
            // 返回信息
            String return_code = map.get("return_code").toString();//返回状态码
            String return_msg = map.get("return_msg").toString();//返回信息

            System.out.println("请求微信预支付接口，返回 code：" + return_code);
            System.out.println("请求微信预支付接口，返回 msg：" + return_msg);
            if ("SUCCESS".equals(return_code)) {
                // 业务结果
                String prepay_id = map.get("prepay_id").toString();//返回的预付单信息
                String nonceStr = UUIDHexGenerator.generate();
                JsonObject.put("nonceStr", nonceStr);
                JsonObject.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                JsonObject.put("timeStamp", timeStamp + "");
                String stringSignTemp = "appId=" + appid + "&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
                //再次签名
                String paySign = PayUtil.sign(stringSignTemp, "&key=" + key, "utf-8").toUpperCase();
                JsonObject.put("paySign", paySign);
                JsonObject.put("data", map);
                return ResultJSON.success(JsonObject);
            } else {
                return ResultJSON.success(map);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(ex.getMessage());
        }
    }

    public Map<String, String> xcxPayment(String orderNum, String money, String openId) throws Exception {
        log.info("【小程序支付】 统一下单开始, 订单编号=" + orderNum);
        SortedMap<String, String> resultMap = new TreeMap<String, String>();
        resultMap.put("订单号", orderNum);
        resultMap.put("支付金额", money + "");
        resultMap.put("用户openId", openId);
        resultMap.put("return_code", "SUCCESS");
        resultMap.put("result_code", "SUCCESS");
        resultMap.put("return_msg", "下单成功");
        resultMap.put("支付时间", new Date() + "");


        log.info("订单号:" + orderNum);
        log.info("支付金额:" + money);
        log.info("用户openId:" + money);

        //生成支付金额，开发环境处理支付金额数到0.01、0.02、0.03元
        //        double payAmount = PayUtil.getPayAmountByEnv(PROJECT_ENV, money);
        //添加或更新支付记录(参数跟进自己业务需求添加)
//        if(flag < 0){
//            resultMap.put("returnCode", "FAIL");
//            resultMap.put("returnMsg", "此订单已支付！");
//            log.info("【小程序支付】 此订单已支付！");
//        }else if(flag == 0){
//            resultMap.put("returnCode", "FAIL");
//            resultMap.put("returnMsg", "支付记录生成或更新失败！");
//            LOGGER.info("【小程序支付】 支付记录生成或更新失败！");
//        }else{
//            Map<String,String> resMap = this.xcxUnifieldOrder(orderNum, PayConfig.TRADE_TYPE_JSAPI, payAmount,openId);
//            if(PayConstant.SUCCESS.equals(resMap.get("return_code")) && PayConstant.SUCCESS.equals(resMap.get("result_code"))){
//                resultMap.put("appId", PayConfig.XCX_APP_ID);
//                resultMap.put("timeStamp", PayUtil.getCurrentTimeStamp());
//                resultMap.put("nonceStr", PayUtil.makeUUID(32));
//                resultMap.put("package", "prepay_id="+resMap.get("prepay_id"));
//                resultMap.put("signType", "MD5");
//                resultMap.put("sign", PayUtil.createSign(resultMap,PayConfig.XCX_KEY));
//                resultMap.put("returnCode", "SUCCESS");
//                resultMap.put("returnMsg", "OK");
//                log.info("【小程序支付】统一下单成功，返回参数:"+resultMap);
//            }else{
//                resultMap.put("returnCode", resMap.get("return_code"));
//                resultMap.put("returnMsg", resMap.get("return_msg"));
//                log.info("【小程序支付】统一下单失败，失败原因:"+resMap.get("return_msg"));
//            }
//        }
        return resultMap;
    }


    /**
     * 预支付时填写的 notify_url ，支付成功后的回调接口
     *
     * @param request
     */
    @PostMapping("/weixin/paycallback.do")
    @ResponseBody
    public void paycallback(HttpServletRequest request) {
        try {
            Map<String, Object> dataMap = XmlUtil.parseXML(request);
            System.out.println(JSON.toJSONString(dataMap));
            //{"transaction_id":"4200000109201805293331420304","nonce_str":"402880e963a9764b0163a979a16e0002","bank_type":"CFT","openid":"oXI6G5Jc4D44y2wixgxE3OPwpDVg","sign":"262978D36A3093ACBE4B55707D6EA7B2","fee_type":"CNY","mch_id":"1491307962","cash_fee":"10","out_trade_no":"14913079622018052909183048768217","appid":"wxa177427bc0e60aab","total_fee":"10","trade_type":"JSAPI","result_code":"SUCCESS","time_end":"20180529091834","is_subscribe":"N","return_code":"SUCCESS"}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}