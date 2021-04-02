package com.line.base.linestarterdaotest.mybatis.dao;

import com.line.base.linestarterdaodatasource.mybatis.basedao.BaseDao;
import com.line.base.linestarterdaotest.mybatis.po.TestPo;
import com.line.base.linestarterdaotest.mybatis.vo.TestVo;

import java.util.List;

/**
 *
 * @Author: yangcs
 * @Date: 2020/10/26 15:00
 * @Description:
 */
public interface MybatisMapper extends BaseDao<TestPo> {

    String selectAnyMaster();

    String selectAnySlave();

    List<TestVo> listPage(TestVo vo);
}
