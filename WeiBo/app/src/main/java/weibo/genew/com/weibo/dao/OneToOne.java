package weibo.genew.com.weibo.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/3/8.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OneToOne
{
    /**
     * (Optional) The entityClass class that is the target of
     * the association.
     *
     * <p> Defaults to the type of the field or property
     * that stores the association.
     */
    Class targetEntity() default void.class;

    /**
     * (Optional) The operations that must be cascaded to
     * the target of the association.
     *
     * <p> By default no operations are cascaded.
     */
    CascadeType[] cascade() default {};

    /**
     * (Optional) Whether the association is optional. If set
     * to false then a non-null relationship must always exist.
     */
    boolean optional() default true;

    /** (Optional) The field that owns the relationship. This
     * element is only specified on the inverse (non-owning)
     * side of the association.
     */
    String mappedBy() default "";


    /**
     * (Optional) Whether to apply the remove operation to entities that have
     * been removed from the relationship and to cascade the remove operation to
     * those entities.
     * @since Java Persistence 2.0
     */
    boolean orphanRemoval() default false;
}
