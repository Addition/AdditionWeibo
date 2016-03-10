package weibo.genew.com.weibo.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/5.
 */
public interface Dao<M>
{
    /**
     * ����
     * @param m	ʵ��
     * @return
     */
    long insert(M m);
    /**
     * ɾ��
     * @param id
     * @return
     */
    int delete(Serializable id); // int long String(Serializable id)
    /**
     * �޸�
     * @param m ʵ��
     * @return
     */
    int update(M m);
    /**
     * ��ѯ
     * @return
     */
    List<M> findAll();
}
