package com.ejobsg.recruitment.controller;

import com.ejobsg.common.core.utils.poi.ExcelUtil;
import com.ejobsg.common.core.web.domain.AjaxResult;
import com.ejobsg.common.core.web.page.TableDataInfo;
import com.ejobsg.common.log.annotation.Log;
import com.ejobsg.common.log.enums.BusinessType;
import com.ejobsg.common.security.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import com.ejobsg.recruitment.domain.entity.Employer;
import com.ejobsg.recruitment.service.EmployerService;
import org.springframework.web.bind.annotation.RestController;
import com.ejobsg.common.core.web.controller.BaseController;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 雇主控制器
 *
 * @author lilu
 * @since 2023-09-02
 */
@Slf4j
@ApiSort(1)
@Api(tags = "雇主接口")
@RequiredArgsConstructor
@RestController
@RequestMapping("/employer")
public class EmployerController extends BaseController {

    private final EmployerService employerService;

    @ApiOperation("查询雇主列表")
    @ApiOperationSupport(order = 1, author = "lilu")
    @RequiresPermissions("recruitment:employer:list")
    @GetMapping("/list")
    public TableDataInfo list(@ApiParam("雇主") Employer employer) {
        return getDataTable(employerService.selectEmployerPage(loadPage(), employer));
    }

    @ApiOperation("导出雇主列表")
    @ApiOperationSupport(order = 2, author = "lilu")
    @RequiresPermissions("recruitment:employer:export")
    @Log(title = "雇主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@ApiParam("雇主") Employer employer, HttpServletResponse response) {
        List<Employer> list = employerService.selectEmployerList(employer);
        ExcelUtil<Employer> util = new ExcelUtil<>(Employer.class);
        util.exportExcel(response, list, "雇主数据");
    }

    @ApiOperation("获取雇主详细信息")
    @ApiOperationSupport(order = 3, author = "lilu")
    @RequiresPermissions("recruitment:employer:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable @ApiParam("雇主主键") Serializable id) {
        return success(employerService.selectEmployerById(id));
    }

    @ApiOperation("新增雇主")
    @ApiOperationSupport(order = 4, author = "lilu")
    @RequiresPermissions("recruitment:employer:add")
    @Log(title = "雇主", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @ApiParam("雇主") Employer employer) {
        return toAjax(employerService.insertEmployer(employer));
    }

    @ApiOperation("修改雇主")
    @ApiOperationSupport(order = 5, author = "lilu")
    @RequiresPermissions("recruitment:employer:edit")
    @Log(title = "雇主", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("雇主") Employer employer) {
        return toAjax(employerService.updateEmployer(employer));
    }

    @ApiOperation("删除雇主")
    @ApiOperationSupport(order = 6, author = "lilu")
    @RequiresPermissions("recruitment:employer:remove")
    @Log(title = "参数配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable @ApiParam("雇主主键列表") Serializable[] ids) {
        return toAjax(employerService.deleteEmployerByIds(ids));
    }

}
