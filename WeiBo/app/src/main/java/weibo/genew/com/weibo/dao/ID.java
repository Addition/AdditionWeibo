package weibo.genew.com.weibo.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ��ʶ����
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ID
{
    /**
     * ���ݿ��б���
     * @return
     */
    String value();
    /**
     * �Ƿ�����
     * @return
     */
    boolean autoincrement();
}
