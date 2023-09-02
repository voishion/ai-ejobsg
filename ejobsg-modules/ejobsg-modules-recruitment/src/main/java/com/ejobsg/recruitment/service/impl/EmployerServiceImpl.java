package com.ejobsg.recruitment.service.impl;

import com.ejobsg.common.core.utils.StringUtils;
import com.ejobsg.common.core.utils.bean.PageReq;
import com.ejobsg.common.security.utils.SecurityUtils;
import com.ejobsg.recruitment.domain.entity.Employer;
import com.ejobsg.recruitment.mapper.EmployerMapper;
import com.ejobsg.recruitment.service.EmployerService;
import com.google.common.collect.Lists;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 雇主 服务接口实现
 *
 * @author lilu
 * @since 2023-09-02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployerServiceImpl extends ServiceImpl<EmployerMapper, Employer> implements EmployerService {

    @Override
    public Employer selectEmployerById(Serializable id) {
        return this.getById(id);
    }

    @Override
    public List<Employer> selectEmployerList(Employer employer) {
        return this.list(defaultQueryWrapper(employer));
    }

    @Override
    public Page<Employer> selectEmployerPage(PageReq<Employer> page, Employer employer) {
        QueryWrapper wrapper = defaultQueryWrapper(employer);
        if (StringUtils.isNotBlank(page.getOrderBy())) {
            wrapper.orderBy(page.getOrderBy());
        }
        return this.page(page.getPage(), wrapper);
    }

    @Override
    public int insertEmployer(Employer employer) {
        employer.setCreateBy(SecurityUtils.getUsername());
        return this.save(employer) ? 1 : 0;
    }

    @Override
    public int updateEmployer(Employer employer) {
        employer.setUpdateBy(SecurityUtils.getUsername());
        return this.updateById(employer) ? 1 : 0;
    }

    @Override
    public int deleteEmployerByIds(Serializable[] ids) {
        return this.removeByIds(Lists.newArrayList(ids)) ? ids.length : 0;
    }

    @Override
    public int deleteEmployerById(Serializable id) {
        return this.removeById(id) ? 1 : 0;
    }

}
