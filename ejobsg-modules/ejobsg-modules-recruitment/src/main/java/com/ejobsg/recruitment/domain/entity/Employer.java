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

/**
 * 雇主 实体类
 *
 * @author lilu
 * @since 2023-09-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "雇主", description = "雇主")
@Table(value = "biz_employer")
public class Employer extends BaseEntityFX implements Serializable {

    /**
     * 雇员编号
     */
    @Id(keyType = KeyType.Auto)
    @ApiModelProperty(value = "雇员编号", notes = "")
    @Excel(sort = 1, name = "雇员编号")
    private Long id;

    /**
     * 雇主姓名
     */
    @ApiModelProperty(value = "雇主姓名", notes = "")
    @Excel(sort = 2, name = "雇主姓名")
    private String name;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人", notes = "")
    @Excel(sort = 3, name = "负责人")
    private String leader;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", notes = "")
    @Excel(sort = 4, name = "联系电话")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", notes = "")
    @Excel(sort = 5, name = "邮箱", width = 24)
    private String email;

    /**
     * 状态（0-正常 1-停用）
     */
    @ApiModelProperty(value = "状态（0-正常 1-停用）", notes = "")
    @Excel(sort = 6, name = "状态", readConverterExp = "0=正常,1=停用")
    private Integer status;

    /**
     * 删除标志（0-存在 1-删除）
     */
    @Column(isLogicDelete = true)
    @ApiModelProperty(value = "删除标志（0-存在 1-删除）", notes = "")
    private Integer deleted;

}
