package com.ejobsg.recruitment.domain.entity;

import com.ejobsg.common.core.annotation.Excel;
import com.ejobsg.common.core.web.domain.BaseEntityFX;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * $$TABLE_NAME$$ 实体类
 *
 * @author lilu
 * @since 2023-09-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "$$TABLE_NAME$$", description = "$$TABLE_NAME$$")
@Table(value = "face_compare_request_log")
public class FaceCompareRequestLog extends BaseEntityFX implements Serializable {

    /**
     * 主键ID
     */
    @Id(keyType = KeyType.Auto)
    @ApiModelProperty(value = "主键ID", notes = "")
    @Excel(sort = 1, name = "主键ID")
    private Long id;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", notes = "")
    @Excel(sort = 1, name = "用户名称")
    private String userName;

    /**
     * 用户中文名
     */
    @ApiModelProperty(value = "用户中文名", notes = "")
    @Excel(sort = 1, name = "用户中文名")
    private String fullName;

    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号", notes = "")
    @Excel(sort = 1, name = "应用编号")
    private String appId;

    /**
     * 请求唯一编号
     */
    @ApiModelProperty(value = "请求唯一编号", notes = "")
    @Excel(sort = 1, name = "请求唯一编号")
    private String requestId;

    /**
     * 请求类型
     */
    @ApiModelProperty(value = "请求类型", notes = "")
    @Excel(sort = 1, name = "请求类型")
    private String requestType;

    /**
     * 请求地址
     */
    @ApiModelProperty(value = "请求地址", notes = "")
    @Excel(sort = 1, name = "请求地址")
    private String requestUrl;

    /**
     * 请求头，JSON
     */
    @ApiModelProperty(value = "请求头，JSON", notes = "")
    @Excel(sort = 1, name = "请求头，JSON")
    private String requestHeader;

    /**
     * 请求体，JSON
     */
    @ApiModelProperty(value = "请求体，JSON", notes = "")
    @Excel(sort = 1, name = "请求体，JSON")
    private String requestBody;

    /**
     * 请求图片，JSON
     */
    @ApiModelProperty(value = "请求图片，JSON", notes = "")
    @Excel(sort = 1, name = "请求图片，JSON")
    private String requestImages;

    /**
     * 请求结果[0-未知，1-业务成功，2-业务失败，3-异常]
     */
    @ApiModelProperty(value = "请求结果[0-未知，1-业务成功，2-业务失败，3-异常]", notes = "")
    @Excel(sort = 1, name = "请求结果[0-未知，1-业务成功，2-业务失败，3-异常]")
    private Integer requestStatus;

    /**
     * 响应结果，JSON
     */
    @ApiModelProperty(value = "响应结果，JSON", notes = "")
    @Excel(sort = 1, name = "响应结果，JSON")
    private String responseResult;

    /**
     * 异常消息
     */
    @ApiModelProperty(value = "异常消息", notes = "")
    @Excel(sort = 1, name = "异常消息")
    private String exceptionMsg;

    /**
     * 请求耗时，毫秒
     */
    @ApiModelProperty(value = "请求耗时，毫秒", notes = "")
    @Excel(sort = 1, name = "请求耗时，毫秒")
    private Long elapsedTime;

    /**
     * 禁用标识
     */
    @ApiModelProperty(value = "禁用标识", notes = "")
    @Excel(sort = 1, name = "禁用标识")
    private Integer disabled;

    /**
     * 删除标识
     */
    @Column(isLogicDelete = true)
    @ApiModelProperty(value = "删除标识", notes = "")
    @Excel(sort = 1, name = "删除标识")
    private Integer deleted;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", notes = "")
    @Excel(sort = 1, name = "创建时间")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", notes = "")
    @Excel(sort = 1, name = "更新时间")
    private LocalDateTime gmtModified;

}
