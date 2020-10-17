package utils;

import lombok.Data;

@Data
public class Page {

    /**
     * 总条数
     */
    private int totalCount;
    /**
     * 起始页页数
     */
    private int start;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 每页显示的条数
     */
    private int pageSize;
    /**
     * 当前页
     */
    private int currentPage;

}
