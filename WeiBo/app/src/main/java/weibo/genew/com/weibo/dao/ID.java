package weibo.genew.com.weibo.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识主键
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ID
{
    /**
     * 数据库中表名
     * @return
     */
    String value();
    /**
     * 是否自增
     * @return
     */
    boolean autoincrement();
}
