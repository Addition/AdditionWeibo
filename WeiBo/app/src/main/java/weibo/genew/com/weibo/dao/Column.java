package weibo.genew.com.weibo.dao;

/**
 * Created by Administrator on 2016/3/5.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定了实体的字段与数据库表中列的对应关系
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column
{
    /**
     * 数据库中表名
     * @return
     */
    String value() default "";
}
