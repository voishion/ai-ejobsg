package com.ejobsg.recruitment.service;

import cn.hutool.core.map.MapUtil;
import com.ejobsg.common.core.constant.FrontConstants;
import com.ejobsg.common.core.utils.DateUtils;
import com.ejobsg.common.core.utils.bean.PageReq;
import com.ejobsg.recruitment.domain.entity.Employer;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.constant.SqlOperator;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.query.SqlOperators;
import com.mybatisflex.core.service.IService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static com.ejobsg.recruitment.domain.entity.table.EmployerTableDef.Employer;

/**
 * 雇主 服务接口
 *
 * @author lilu
 * @since 2023-09-02
 */
public interface EmployerService extends IService<Employer> {

    /**
     * 默认查询对象
     *
     * @param employer 雇主
     * @return 查询对象
     */
    default QueryWrapper defaultQueryWrapper(Employer employer) {
        SqlOperators operators = SqlOperators.of()
            .set(Employer.Name.getName(), SqlOperator.LIKE)
            .set(Employer.Leader.getName(), SqlOperator.LIKE)
            .set(Employer.Phone.getName(), SqlOperator.LIKE);
        QueryWrapper wrapper = QueryWrapper.create(employer, operators).from(Employer);

        Map<String, Object> params = employer.getParams();
        if (params.containsKey(FrontConstants.BEGIN_TIME)) {
            wrapper.and(Employer.CreateTime.ge(DateUtils.parseDateToLocalDateTimeWithBeginTime(MapUtil.getStr(params, FrontConstants.BEGIN_TIME))));
        }
        if (params.containsKey(FrontConstants.END_TIME)) {
            wrapper.and(Employer.CreateTime.le(DateUtils.parseDateToLocalDateTimeWithEndTime(MapUtil.getStr(params, FrontConstants.END_TIME))));
        }

        return wrapper;
    }

    /**
    * 查询雇主
    *
    * @param id 雇主主键
    * @return 雇主
    */
    Employer selectEmployerById(Serializable id);

    /**
    * 查询雇主列表
    *
    * @param employer 雇主
    * @return 雇主集合
    */
    List<Employer> selectEmployerList(Employer employer);

    /**
    * 查询雇主分页列表
    *
    * @param page     分页请求
    * @param employer 雇主
    * @return 雇主集合
    */
    Page<Employer> selectEmployerPage(PageReq<Employer> page, Employer employer);

    /**
    * 新增雇主
    *
    * @param employer 雇主
    * @return 结果
    */
    int insertEmployer(Employer employer);

    /**
    * 修改雇主
    *
    * @param employer 雇主
    * @return 结果
    */
    int updateEmployer(Employer employer);

    /**
    * 批量删除雇主
    *
    * @param ids 需要删除的雇主主键集合
    * @return 结果
    */
    int deleteEmployerByIds(Serializable[] ids);

    /**
    * 删除雇主信息
    *
    * @param id 雇主主键
    * @return 结果
    */
    int deleteEmployerById(Serializable id);

}
