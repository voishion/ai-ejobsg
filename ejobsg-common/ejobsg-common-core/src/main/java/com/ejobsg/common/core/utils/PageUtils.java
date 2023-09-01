package com.ejobsg.common.core.utils;

import com.ejobsg.common.core.utils.bean.PageReq;
import com.github.pagehelper.PageHelper;
import com.ejobsg.common.core.utils.sql.SqlUtil;
import com.ejobsg.common.core.web.page.PageDomain;
import com.ejobsg.common.core.web.page.TableSupport;
import com.mybatisflex.core.paginate.Page;
import org.apache.poi.ss.formula.functions.T;

/**
 * 分页工具类
 *
 * @author lilu
 */
public class PageUtils extends PageHelper
{
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }

    /**
     * 加载请求分页数据
     */
    public static <T> PageReq<T> loadPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        return new PageReq<>(Page.of(pageNum, pageSize), orderBy);
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }

}
