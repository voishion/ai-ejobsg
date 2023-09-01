package com.ejobsg.common.core.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mybatisflex.annotation.Column;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 *
 * @author lilu
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
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
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间，格式：yyyy-MM-dd HH:mm:ss", notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者", notes = "")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间，格式：yyyy-MM-dd HH:mm:ss", notes = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", notes = "")
    private String remark;

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
