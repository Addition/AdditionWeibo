package weibo.genew.com.weibo.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/5.
 */
public interface Dao<M>
{
    /**
     * 增加
     * @param m	实体
     * @return
     */
    long insert(M m);
    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Serializable id); // int long String(Serializable id)
    /**
     * 修改
     * @param m 实体
     * @return
     */
    int update(M m);
    /**
     * 查询
     * @return
     */
    List<M> findAll();
}
