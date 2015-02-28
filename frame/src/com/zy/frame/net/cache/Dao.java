package com.zy.frame.net.cache;

import java.util.List;

/**
 * Created by Administrator on 2015/2/27.
 */
public interface Dao {
    /**
     * 向数据库中插入一条数据
     *
     * @param object
     * @return
     */
    public long insert(Object object);

    /**
     * 根据ID删除一条数据
     *
     * @param _id
     * @return
     */
    public long delete(String _id);

    /**
     * 更新某一条目的数据
     *
     * @param object
     * @return
     */
    public long update(Object object);

    /**
     * 根据ID查询某一条目的数据
     *
     * @param _id
     * @return
     */
    public Object query(String _id);


    /**
     * 查询一组数据集合
     * @return
     */
    public List<Object> query();

    /**
     * 缓存是否过期
     * @return true 过期，false 没有过期
     */
    public boolean isExpire();

}
