package weibo.genew.com.weibo.dao;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2016/3/8.
 */

@Target({FIELD})
@Retention(RUNTIME)
public @interface JoinColumn
{
    /**
     * (Optional) The name of the foreign key column.
     * The table in which it is found depends upon the
     * context.
     * <ul>
     * <li>If the join is for a OneToOne or ManyToOne
     *  mapping using a foreign key mapping strategy,
     * the foreign key column is in the table of the
     * source entityClass or embeddable.
     * <li> If the join is for a unidirectional OneToMany mapping
     * using a foreign key mapping strategy, the foreign key is in the
     * table of the target entityClass.
     * <li> If the join is for a ManyToMany mapping or for a OneToOne
     * or bidirectional ManyToOne/OneToMany mapping using a join
     * table, the foreign key is in a join table.
     * <li> If the join is for an element collection, the foreign
     * key is in a collection table.
     *</ul>
     *
     * <p> Default (only applies if a single join column is used):
     * The concatenation of the following: the name of the
     * referencing relationship property or field of the referencing
     * entityClass or embeddable class; "_"; the name of the referenced
     * primary key column.
     * If there is no such referencing relationship property or
     * field in the entityClass, or if the join is for an element collection,
     * the join column name is formed as the
     * concatenation of the following: the name of the entityClass; "_";
     * the name of the referenced primary key column.
     */
    String name() default "";

    /**
     * (Optional) The name of the column referenced by this foreign
     * key column.
     * <ul>
     * <li> When used with entityClass relationship mappings other
     * than the cases described here, the referenced column is in the
     * table of the target entityClass.
     * <li> When used with a unidirectional OneToMany foreign key
     * mapping, the referenced column is in the table of the source
     * entityClass.
     * <li> When used inside a <code>JoinTable</code> annotation,
     * the referenced key column is in the entityClass table of the owning
     * entityClass, or inverse entityClass if the join is part of the inverse
     * join definition.
     * <li> When used in a <code>CollectionTable</code> mapping, the
     * referenced column is in the table of the entityClass containing the
     * collection.
     * </ul>
     *
     * <p> Default (only applies if single join column is being
     * used): The same name as the primary key column of the
     * referenced table.
     */
    String referencedColumnName() default "";

    /**
     * (Optional) Whether the property is a unique key.  This is a
     * shortcut for the <code>UniqueConstraint</code> annotation at
     * the table level and is useful for when the unique key
     * constraint is only a single field. It is not necessary to
     * explicitly specify this for a join column that corresponds to a
     * primary key that is part of a foreign key.
     */
    boolean unique() default false;

    /** (Optional) Whether the foreign key column is nullable. */
    boolean nullable() default true;

    /**
     * (Optional) Whether the column is included in
     * SQL INSERT statements generated by the persistence
     * provider.
     */
    boolean insertable() default true;

    /**
     * (Optional) Whether the column is included in
     * SQL UPDATE statements generated by the persistence
     * provider.
     */
    boolean updatable() default true;

    /**
     * (Optional) The SQL fragment that is used when
     * generating the DDL for the column.
     * <p> Defaults to the generated SQL for the column.
     */
    String columnDefinition() default "";

    /**
     * (Optional) The name of the table that contains
     * the column. If a table is not specified, the column
     * is assumed to be in the primary table of the
     * applicable entityClass.
     *
     * <p> Default:
     * <ul>
     * <li> If the join is for a OneToOne or ManyToOne mapping
     * using a foreign key mapping strategy, the name of the table of
     * the source entityClass or embeddable.
     * <li> If the join is for a unidirectional OneToMany mapping
     * using a foreign key mapping strategy, the name of the table of
     * the target entityClass.
     * <li> If the join is for a ManyToMany mapping or
     * for a OneToOne or bidirectional ManyToOne/OneToMany mapping
     * using a join table, the name of the join table.
     * <li> If the join is for an element collection, the name of the collection table.
     * </ul>
     */
    String table() default "";

}