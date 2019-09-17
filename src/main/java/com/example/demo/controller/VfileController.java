package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Utils.StringUtil;
import com.example.demo.api.VfileApi;
import com.example.demo.entity.Vfile;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.example.demo.service.VfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 视频控制器
 */
@RestController
@Slf4j
public class VfileController implements VfileApi {

    @Autowired
    private VfileService service;

    @Override
    public ResultJSON<Vfile> getInfo(String id) {
        try {
            if (StringUtil.isNotEmty(id)){
                Vfile entity = service.getById(id);
                if (null != entity){
                    return ResultJSON.success(entity);
                }
                return ResultJSON.error(CodeMsg.REQUEST_ERROR);
            }
        } catch (Exception ex) {
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return null;
    }

    @Override
    public ResultJSON<List<Vfile>> getvfileList() {
        try {
            List<Vfile> vfiles = service.list(null);
                if (vfiles.size() > 0){
                    return ResultJSON.success(vfiles);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return ResultJSON.error(CodeMsg.SESSION_ERROR);
    }

    /**
     * 获取页面
     * @param param
     * @return
     */
    @Override
    public ResultJSON<IPage<Vfile>> getPage(ReqParam param) {
        try {
            IPage<Vfile> page = new Page<Vfile>(param.getPageNo() + 1, param.getPageSize() + 1);
            QueryWrapper<Vfile> qw = new QueryWrapper<Vfile>();
            qw.like("vname",param.getContext()).or()
            .like("actor",param.getContext()).or()
            .like("context",param.getContext()).or()
            .like("direct",param.getContext());
            page = service.page(page ,qw);
            ((Page<Vfile>) page).setDesc("createTime");
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }
}
