#set(tableComment = isNotBlank(table.getComment()) ? table.getComment() : 'TABLE_NAME')
#set(permissionPrefix = 'PERMISSION_PREFIX')
#set(entityClassName = table.buildEntityClassName())
#set(entityVarName = firstCharToLowerCase(entityClassName))
#set(serviceVarName = firstCharToLowerCase(table.buildServiceClassName()))
package #(packageConfig.controllerPackage);

import com.ejobsg.common.core.utils.poi.ExcelUtil;
import com.ejobsg.common.core.web.domain.AjaxResult;
import com.ejobsg.common.core.web.page.TableDataInfo;
import com.ejobsg.common.log.annotation.Log;
import com.ejobsg.common.log.enums.BusinessType;
import com.ejobsg.common.security.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import #(packageConfig.entityPackage).#(entityClassName);
import #(packageConfig.servicePackage).#(table.buildServiceClassName());
#if(controllerConfig.restStyle)
import org.springframework.web.bind.annotation.RestController;
#else
import org.springframework.stereotype.Controller;
#end
#if(controllerConfig.superClass != null)
import #(controllerConfig.buildSuperClassImport());
#end
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
#if(withSwagger && swaggerVersion.getName() == "FOX")
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
#end
#if(withSwagger && swaggerVersion.getName() == "DOC")
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
#end
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * #(tableComment)控制器
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
@Slf4j
@ApiSort(1)
#if(withSwagger && swaggerVersion.getName() == "FOX")
@Api(tags = "#(tableComment)接口")
#end
#if(withSwagger && swaggerVersion.getName() == "DOC")
@Tag(name = "#(tableComment)接口")
#end
@RequiredArgsConstructor
#if(controllerConfig.restStyle)
@RestController
#else
@Controller
#end
@RequestMapping("/#(firstCharToLowerCase(entityClassName))")
public class #(table.buildControllerClassName()) #if(controllerConfig.superClass)extends #(controllerConfig.buildSuperClassName()) #end {

    private final #(table.buildServiceClassName()) #(serviceVarName);

    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("查询#(tableComment)列表")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description="查询#(tableComment)列表")
    #end
    @ApiOperationSupport(order = 1, author = "#(javadocConfig.getAuthor())")
    @RequiresPermissions("#(permissionPrefix):list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description="#(tableComment)")#end #(entityClassName) #(entityVarName)) {
        return getDataTable(#(serviceVarName).select#(entityClassName)Page(loadPage(), #(entityVarName)));
    }

    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("导出#(tableComment)列表")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description="导出#(tableComment)列表")
    #end
    @ApiOperationSupport(order = 2, author = "#(javadocConfig.getAuthor())")
    @RequiresPermissions("#(permissionPrefix):export")
    @Log(title = "#(tableComment)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description="#(tableComment)")#end #(entityClassName) #(entityVarName), HttpServletResponse response) {
        List<#(entityClassName)> list = #(serviceVarName).select#(entityClassName)List(#(entityVarName));
        ExcelUtil<#(entityClassName)> util = new ExcelUtil<>(#(entityClassName).class);
        util.exportExcel(response, list, "#(tableComment)数据");
    }

    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("获取#(tableComment)详细信息")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description="获取#(tableComment)详细信息")
    #end
    @ApiOperationSupport(order = 3, author = "#(javadocConfig.getAuthor())")
    @RequiresPermissions("#(permissionPrefix):query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)主键") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description="#(tableComment)主键")#end Serializable id) {
        return success(#(serviceVarName).select#(entityClassName)ById(id));
    }

    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("新增#(tableComment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description="新增#(tableComment)")
    #end
    @ApiOperationSupport(order = 4, author = "#(javadocConfig.getAuthor())")
    @RequiresPermissions("#(permissionPrefix):add")
    @Log(title = "#(tableComment)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description="#(tableComment)")#end #(entityClassName) #(entityVarName)) {
        return toAjax(#(serviceVarName).insert#(entityClassName)(#(entityVarName)));
    }

    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("修改#(tableComment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description="修改#(tableComment)")
    #end
    @ApiOperationSupport(order = 5, author = "#(javadocConfig.getAuthor())")
    @RequiresPermissions("#(permissionPrefix):edit")
    @Log(title = "#(tableComment)", businessType = BusinessType.UPDATE)
    @PostMapping
    public AjaxResult edit(@RequestBody #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description="#(tableComment)")#end #(entityClassName) #(entityVarName)) {
        return toAjax(#(serviceVarName).update#(entityClassName)(#(entityVarName)));
    }

    #if(withSwagger && swaggerVersion.getName() == "FOX")
    @ApiOperation("删除#(tableComment)")
    #end
    #if(withSwagger && swaggerVersion.getName() == "DOC")
    @Operation(description="删除#(tableComment)")
    #end
    @ApiOperationSupport(order = 6, author = "#(javadocConfig.getAuthor())")
    @RequiresPermissions("#(permissionPrefix):remove")
    @Log(title = "参数配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable #if(withSwagger && swaggerVersion.getName() == "FOX")@ApiParam("#(tableComment)主键列表") #end #if(withSwagger && swaggerVersion.getName() == "DOC")@Parameter(description="#(tableComment)主键列表")#end Serializable[] ids) {
        return toAjax(#(serviceVarName).delete#(entityClassName)ByIds(ids));
    }

}
