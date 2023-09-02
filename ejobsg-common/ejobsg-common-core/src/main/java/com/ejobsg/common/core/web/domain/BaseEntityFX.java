package com.ejobsg.common.core.web.domain;

import com.ejobsg.common.core.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mybatisflex.annotation.Column;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类[Mybatis-Flex]
 *
 * @author lilu
 */
@Getter
@Setter
public class BaseEntityFX implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    @JsonIgnore
    @Column(ignore = true)
    private String searchValue;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者", notes = "")
    @Excel(sort = 1000, name = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间，格式：yyyy-MM-dd HH:mm:ss", notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(onInsertValue = "now()")
    @Excel(sort = 1001, name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss", width = 24)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者", notes = "")
    @Excel(sort = 1002, name = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间，格式：yyyy-MM-dd HH:mm:ss", notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    @Excel(sort = 1003, name = "更新时间", dateFormat = "yyyy-MM-dd HH:mm:ss", width = 24)
    private LocalDateTime updateTime;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    @JsonIgnore
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

}
