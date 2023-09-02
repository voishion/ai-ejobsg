#set(tableComment = isNotBlank(table.getComment()) ? table.getComment() : '$$TABLE_NAME$$')
#set(entityVarName = firstCharToLowerCase(table.buildEntityClassName()))
#set(isCacheExample = serviceImplConfig.cacheExample)
#set(primaryKey = table.getPrimaryKey())
#set(entityClassName = table.buildEntityClassName())
package #(packageConfig.serviceImplPackage);

import #(serviceImplConfig.buildSuperClassImport());
import com.ejobsg.common.core.utils.StringUtils;
import com.ejobsg.common.core.utils.bean.PageReq;
import com.ejobsg.common.security.utils.SecurityUtils;
import #(packageConfig.entityPackage).#(table.buildEntityClassName());
import #(packageConfig.mapperPackage).#(table.buildMapperClassName());
import #(packageConfig.servicePackage).#(table.buildServiceClassName());
import com.google.common.collect.Lists;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
#if(isCacheExample)
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;

import java.util.Collection;
#end
import java.io.Serializable;
import java.util.List;

import static #(packageConfig.entityPackage).table.#(table.buildEntityClassName())TableDef.#(table.buildEntityClassName());

/**
 * #(tableComment) 服务接口实现
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
@Slf4j
@Service
#if(isCacheExample)
@CacheConfig(cacheNames = "#(firstCharToLowerCase(entityClassName))")
#end
@RequiredArgsConstructor
public class #(table.buildServiceImplClassName()) extends #(serviceImplConfig.buildSuperClassName())<#(table.buildMapperClassName()), #(table.buildEntityClassName())> implements #(table.buildServiceClassName()) {

    @Override
    public #(entityClassName) select#(entityClassName)ById(Serializable id) {
        return this.getById(id);
    }

    @Override
    public List<#(entityClassName)> select#(entityClassName)List(#(entityClassName) #(entityVarName)) {
        return this.list(defaultQueryWrapper(#(entityVarName)));
    }

    @Override
    public Page<#(entityClassName)> select#(entityClassName)Page(PageReq<#(entityClassName)> page, #(entityClassName) #(entityVarName)) {
        QueryWrapper wrapper = defaultQueryWrapper(#(entityVarName));
        if (StringUtils.isNotBlank(page.getOrderBy())) {
            wrapper.orderBy(page.getOrderBy());
        }
        return this.page(page.getPage(), wrapper);
    }

    @Override
    public int insert#(entityClassName)(#(entityClassName) #(entityVarName)) {
        #(entityVarName).setCreateBy(SecurityUtils.getUsername());
        return this.save(#(entityVarName)) ? 1 : 0;
    }

    @Override
    public int update#(entityClassName)(#(entityClassName) #(entityVarName)) {
        #(entityVarName).setUpdateBy(SecurityUtils.getUsername());
        return this.updateById(#(entityVarName)) ? 1 : 0;
    }

    @Override
    public int delete#(entityClassName)ByIds(Serializable[] ids) {
        return this.removeByIds(Lists.newArrayList(ids)) ? ids.length : 0;
    }

    @Override
    public int delete#(entityClassName)ById(Serializable id) {
        return this.removeById(id) ? 1 : 0;
    }

#if(isCacheExample)
    @Override
    @CacheEvict(allEntries = true)
    public boolean remove(QueryWrapper query) {
        return super.remove(query);
    }

    @Override
    @CacheEvict(key = "#id")
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> ids) {
        return super.removeByIds(ids);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean update(#(entityClassName) entity, QueryWrapper query) {
        return super.update(entity, query);
    }

    @Override
    @CacheEvict(key = "#entity.#(primaryKey)")
    public boolean updateById(#(entityClassName) entity, boolean ignoreNulls) {
        return super.updateById(entity, ignoreNulls);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean updateBatch(Collection<#(entityClassName)> entities, int batchSize) {
        return super.updateBatch(entities, batchSize);
    }

    @Override
    @Cacheable(key = "#id")
    public #(entityClassName) getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #query.toSQL()")
    public #(entityClassName) getOne(QueryWrapper query) {
        return super.getOne(query);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #query.toSQL()")
    public <R> R getOneAs(QueryWrapper query, Class<R> asType) {
        return super.getOneAs(query, asType);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #query.toSQL()")
    public Object getObj(QueryWrapper query) {
        return super.getObj(query);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #query.toSQL()")
    public <R> R getObjAs(QueryWrapper query, Class<R> asType) {
        return super.getObjAs(query, asType);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #query.toSQL()")
    public List<Object> objList(QueryWrapper query) {
        return super.objList(query);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #query.toSQL()")
    public <R> List<R> objListAs(QueryWrapper query, Class<R> asType) {
        return super.objListAs(query, asType);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #query.toSQL()")
    public List<#(entityClassName)> list(QueryWrapper query) {
        return super.list(query);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #query.toSQL()")
    public <R> List<R> listAs(QueryWrapper query, Class<R> asType) {
        return super.listAs(query, asType);
    }

    /**
     * @deprecated 无法通过注解进行缓存操作。
     */
    @Override
    @Deprecated
    public List<#(entityClassName)> listByIds(Collection<? extends Serializable> ids) {
        return super.listByIds(ids);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #query.toSQL()")
    public long count(QueryWrapper query) {
        return super.count(query);
    }

    @Override
    @Cacheable(key = "#root.methodName + ':' + #page.getPageSize() + ':' + #page.getPageNumber() + ':' + #query.toSQL()")
    public <R> Page<R> pageAs(Page<R> page, QueryWrapper query, Class<R> asType) {
        return super.pageAs(page, query, asType);
    }

#end
}
