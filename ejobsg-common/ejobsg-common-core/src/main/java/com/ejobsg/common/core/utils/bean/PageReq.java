package com.ejobsg.common.core.utils.bean;

import com.mybatisflex.core.paginate.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lilu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageReq<T> {

    private Page<T> page;

    private String orderBy;

}
