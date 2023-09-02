#set(tableComment = isNotBlank(table.getComment()) ? table.getComment() : '$$TABLE_NAME$$')
#set(entityVarName = firstCharToLowerCase(table.buildEntityClassName()))
package #(packageConfig.servicePackage);

import com.ejobsg.common.core.constant.FrontConstants;
import com.ejobsg.common.core.utils.DateUtils;
import com.ejobsg.common.core.utils.bean.PageReq;
import #(serviceConfig.buildSuperClassImport());
import #(packageConfig.entityPackage).#(table.buildEntityClassName());

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.constant.SqlOperator;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.query.SqlOperators;

import cn.hutool.core.map.MapUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static #(packageConfig.entityPackage).table.#(table.buildEntityClassName())TableDef.#(table.buildEntityClassName());

/**
 * #(tableComment) 服务接口
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
public interface #(table.buildServiceClassName()) extends #(serviceConfig.buildSuperClassName())<#(table.buildEntityClassName())> {

    /**
     * 默认查询对象
     *
     * @param #(entityVarName) #(tableComment)
     * @return 查询对象
     */
    default QueryWrapper defaultQueryWrapper(#(table.buildEntityClassName()) #(entityVarName)) {
        SqlOperators operators = SqlOperators.of()
            .set(#(table.buildEntityClassName()).XXXX.getName(), SqlOperator.LIKE);
        QueryWrapper wrapper = QueryWrapper.create(#(entityVarName), operators).from(#(table.buildEntityClassName()));

        Map<String, Object> params = #(entityVarName).getParams();
        if (params.containsKey(FrontConstants.BEGIN_TIME)) {
            wrapper.and(#(table.buildEntityClassName()).CreateTime.ge(DateUtils.parseDateToLocalDateTimeWithBeginTime(MapUtil.getStr(params, FrontConstants.BEGIN_TIME))));
        }
        if (params.containsKey(FrontConstants.END_TIME)) {
            wrapper.and(#(table.buildEntityClassName()).CreateTime.le(DateUtils.parseDateToLocalDateTimeWithEndTime(MapUtil.getStr(params, FrontConstants.END_TIME))));
        }

        return wrapper;
    }

    /**
     * 查询#(tableComment)
     *
     * @param id #(tableComment)主键
     * @return #(tableComment)
     */
    #(table.buildEntityClassName()) select#(table.buildEntityClassName())ById(Serializable id);

    /**
     * 查询#(tableComment)列表
     *
     * @param #(entityVarName) #(tableComment)
     * @return #(tableComment)集合
     */
    List<#(table.buildEntityClassName())> select#(table.buildEntityClassName())List(#(table.buildEntityClassName()) #(entityVarName));

    /**
     * 查询#(tableComment)分页列表
     *
     * @param page     分页请求
     * @param #(entityVarName) #(tableComment)
     * @return #(tableComment)集合
     */
    Page<#(table.buildEntityClassName())> select#(table.buildEntityClassName())Page(PageReq<#(table.buildEntityClassName())> page, #(table.buildEntityClassName()) #(entityVarName));

    /**
     * 新增#(tableComment)
     *
     * @param #(entityVarName) #(tableComment)
     * @return 结果
     */
    int insert#(table.buildEntityClassName())(#(table.buildEntityClassName()) #(entityVarName));

    /**
     * 修改#(tableComment)
     *
     * @param #(entityVarName) #(tableComment)
     * @return 结果
     */
    int update#(table.buildEntityClassName())(#(table.buildEntityClassName()) #(entityVarName));

    /**
     * 批量删除#(tableComment)
     *
     * @param ids 需要删除的#(tableComment)主键集合
     * @return 结果
     */
    int delete#(table.buildEntityClassName())ByIds(Serializable[] ids);

    /**
     * 删除#(tableComment)信息
     *
     * @param id #(tableComment)主键
     * @return 结果
     */
    int delete#(table.buildEntityClassName())ById(Serializable id);

}
