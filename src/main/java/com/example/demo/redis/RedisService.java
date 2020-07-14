package com.example.demo.redis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Utils.StringUtil;
import com.example.demo.entity.mk.MkApartment;
import com.example.demo.entity.mk.MkPointxy;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkApartService;
import com.example.demo.service.mk.MkListingService;
import com.example.demo.service.mk.MkPointXyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/25 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe Redis 业务实现
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "mkPointXyService")
    private MkPointXyService pointXyService;

    @Resource(name = "mkListingService")
    private MkListingService mkListingService;

    @Resource(name = "mkApartService")
    private MkApartService apartService;

    final String GEO_KEY = "cities:locs";

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    /**
     * 删除对应的value
     * @param key
     */
    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }
    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k,Object v){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }

    /**
     * 集合添加
     * @param key
     * @param value
     */
    public void add(String key,Object value){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }

    /**
     * 集合获取
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key,Object value,double scoure){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
    }

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key,double scoure,double scoure1){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    public ResultJSON<?> initia(String name, String x, String y, String fid) {
        try {
            MkPointxy entity = new MkPointxy();
            entity.setName(name);
            entity.setX(x);
            entity.setY(y);
            entity.setFid(fid);
            entity.setCreadDate(new Date());
            boolean f = pointXyService.save(entity);

            Map<String, Point> points = new HashMap<>();
            points.put(fid,new Point((Double.parseDouble(entity.getX())),Double.valueOf(entity.getY())));
            // redisTemplate.boundGeoOps(GEO_KEY).add(points);
            BoundGeoOperations<String, String> geoOps = redisTemplate.boundGeoOps(GEO_KEY);
            geoOps.add(points);
            return ResultJSON.success(geoOps);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        }
    }

    public ResultJSON<?> nearbyfy(String lng, String lat, Integer num) {
        Point point = new Point(lng != null ? Double.parseDouble(lng) : 0.0,lat != null ? Double.parseDouble(lat) : 0.0);

        Metric metric = RedisGeoCommands.DistanceUnit.KILOMETERS;
        //方圆10公里
        num = num == null ? 5 : num;
        Distance distance = new Distance(num, metric);
        //posint 为中心
        Circle circle = new Circle(point, distance);
        //limit 最近的10个位置
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands
                .GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending()
                .limit(10);

        GeoResults<RedisGeoCommands.GeoLocation<String>> radius = redisTemplate.opsForGeo()
                .radius(GEO_KEY, circle, args);

        Map map = new HashMap();

        if (radius != null) {
            radius.forEach(geoLocationGeoResult -> {
                RedisGeoCommands.GeoLocation<String> content = geoLocationGeoResult.getContent();
                //member 名称  如  tianjin
                String name = content.getName();
                // 对应的经纬度坐标
                Point pos = content.getPoint();
                // 距离中心点的距离
                Distance dis = geoLocationGeoResult.getDistance();
                QueryWrapper<MkPointxy> qw = new QueryWrapper<MkPointxy>();
                if (StringUtil.isNotEmty(name)) {
                 Map  entity = mkListingService.getInfo2(Integer.valueOf(name));
                   if (null != entity && entity.size() > 0) {
                       map.put(name,entity);
                   }
                }
            });
        }
        return ResultJSON.success(map);
    }

    public ResultJSON<?> nearbyXq(String lng, String lat, Integer num) {
        Point point = new Point(lng != null ? Double.parseDouble(lng) : 0.0,lat != null ? Double.parseDouble(lat) : 0.0);

        Metric metric = RedisGeoCommands.DistanceUnit.KILOMETERS;
        //方圆10公里
        num = num == null ? 5 : num;
        Distance distance = new Distance(num, metric);
        //posint 为中心
        Circle circle = new Circle(point, distance);
        //limit 最近的10个位置
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands
                .GeoRadiusCommandArgs
                .newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending()
                .limit(10);

        GeoResults<RedisGeoCommands.GeoLocation<String>> radius = redisTemplate.opsForGeo()
                .radius(GEO_KEY, circle, args);

        Map map = new HashMap();

        if (radius != null) {
            radius.forEach(geoLocationGeoResult -> {
                RedisGeoCommands.GeoLocation<String> content = geoLocationGeoResult.getContent();
                //member 名称  如  tianjin
                String name = content.getName();
                // 对应的经纬度坐标
                Point pos = content.getPoint();
                // 距离中心点的距离
                Distance dis = geoLocationGeoResult.getDistance();
//                MkPointxy entity = new MkPointxy();

                QueryWrapper<MkPointxy> qw = new QueryWrapper<MkPointxy>();
                if (StringUtil.isNotEmty(name)) {
//                     = mkListingService.getInfo(Integer.valueOf(name));
                    MkApartment entity = apartService.getById(Integer.valueOf(name));
                    if (null != entity) {
                        map.put(name,entity);
                    }
                }
            });
        }
        return ResultJSON.success(map);
    }


    /**
     * @param blongitude B1经度
     * @param blatitude  B1维度
     * @param ulongitude B2经度
     * @param ulatitude  B2维度
     * @return
     */
    public ResultJSON<?> isNearby(String blongitude, String blatitude, String ulongitude, String ulatitude){
        try {
            GeoOperations geoOperations = redisTemplate.opsForGeo();
            //首先存入客户端上传的经纬度和指定地点的经纬度
            Map<String,Object> map = new HashMap<>();
            if (StringUtil.isNotEmty(blongitude) && StringUtil.isNotEmty(blatitude)) {
                map.put("ZB",new Point(Double.parseDouble(blongitude), Double.parseDouble(blatitude)));
            }

            if (StringUtil.isNotEmty(ulongitude) && StringUtil.isNotEmty(ulatitude)) {
                map.put("GZ",new Point(Double.parseDouble(ulongitude), Double.parseDouble(ulatitude)));
            }

            // 将这些地址数据保存到redis中
            geoOperations.geoAdd("GET_DISTANCE",map);
            // 调用方法,计算之间的距离;
            double value = geoOperations.geoDist("GET_DISTANCE", "ZB", "GZ", RedisGeoCommands.DistanceUnit.METERS).getValue();
            return ResultJSON.success(value < 500);
        } catch (Exception e) {
            return ResultJSON.error("暂时无法定位");
        }
    }
}