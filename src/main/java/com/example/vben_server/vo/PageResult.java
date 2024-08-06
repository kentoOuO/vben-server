package com.example.vben_server.vo;

import lombok.Data;
import java.util.List;

@Data
public class PageResult<T> {

    /** 总记录数 **/
    private Long total;

    /** 数据列表 **/
    private List<T> items;
}
