package top.hlfpersonal.result;

import java.util.List;

/**
 * @program: test1024-parent
 * @description: 分页结果
 * @author: feng
 * @create: 2019-03-24 19:38
 **/
public class PageResult<T> {
    private long size;
    private List<T> data;

    public PageResult(long size, List<T> data) {
        this.size = size;
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
