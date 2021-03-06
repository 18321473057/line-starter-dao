package com.line.base.linestarterdaotest.mybatis.controller;

import com.line.base.linestarterdaotest.mybatis.po.TestPo;
import com.line.base.linestarterdaotest.mybatis.service.IMybatisService;
import com.line.base.linestarterdaotest.mybatis.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: yangcs
 * @Date: 2020/10/23 13:39
 * @Description:
 */
@Controller
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private IMybatisService mybatisService;
//    @Autowired
//    private ISwitchoverService switchoverService;

    /**
     * 通用mapper
     */
    @RequestMapping("/tk/sltone")
    @ResponseBody
    public TestPo selectOneByTKMapper(@RequestParam String id) {
        return mybatisService.selectOneByTKMapper(id);
    }

    /**
     * 通用mapper
     */
    @RequestMapping("/tk/instone")
    public int instOneByTKMapper(@RequestParam String id) {
        return mybatisService.instOneByTKMapper(id);
    }

    /**
     * 分页助手
     */

    @RequestMapping("/list/page")
    @ResponseBody
    public List<TestVo> listPage(TestVo vo) {
        return mybatisService.listPage(vo);
    }

    @RequestMapping("/test")
    public String test(@RequestParam String type) {
        if ("slave".equals(type)) {
            return mybatisService.selectAnySlave();
        } else if ("all".equals(type)) {
            return null;
//            return switchoverService.selectAnyAll();
        } else {
            return mybatisService.selectAnyMaster();
        }
    }

}
