package weibo.genew.com.weibo.dao;

/**
 * Created by Administrator on 2016/3/5.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ָ����ʵ����ֶ������ݿ�����еĶ�Ӧ��ϵ
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column
{
    /**
     * ���ݿ��б���
     * @return
     */
    String value() default "";
}
