#set(tableComment = isNotBlank(table.getComment()) ? table.getComment() : 'TABLE_NAME')
#set(entityVarName = firstCharToLowerCase(table.buildEntityClassName()))
package #(packageConfig.servicePackage);

import com.ejobsg.common.core.utils.bean.PageReq;
import #(serviceConfig.buildSuperClassImport());
import #(packageConfig.entityPackage).#(table.buildEntityClassName());

import com.mybatisflex.core.paginate.Page;

import java.io.Serializable;
import java.util.List;

/**
 * #(tableComment) 服务接口
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
public interface #(table.buildServiceClassName()) extends #(serviceConfig.buildSuperClassName())<#(table.buildEntityClassName())> {

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
