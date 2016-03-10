package weibo.genew.com.weibo.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/5.
 */
public abstract class DAOSupport<M> implements Dao<M>
{
    protected AbstractDBHelper<M> helper;
    protected SQLiteDatabase db;

    @Override
    public long insert(M m) {
        ContentValues values = new ContentValues();
        try {
            fillColumn(m, values);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return db.insert(getTableName(), null, values);
    }

    @Override
    public int delete(Serializable id) {
        return db.delete(getTableName(), helper.getTableId() + "= ?",
                new String[]{id.toString()});
    }

    public int delete(String whereArgs,String[] whereClause){
        return db.delete(getTableName(), whereArgs, whereClause);
    }

    @Override
    public int update(M m) {
        ContentValues values = new ContentValues();
        try {
            fillColumn(m, values);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return db.update(getTableName(), values, helper.getTableId() + "=?",
                new String[]{getId(m)});
    }

    @Override
    public List<M> findAll() {
        List<M> result = null;
        Cursor cursor = db.query(getTableName(), null, null, null, null, null,
                null);
        if (cursor != null) {
            result = new ArrayList<M>();
            while (cursor.moveToNext()) {
                M m = getInstantce();
                try {
                    fillField(cursor, m);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                result.add(m);
            }
            cursor.close();
        }
        return result;
    }

    public List<M> findByCondition(String[] columns,String selection,String[] selectionArgs,String group,String having,String orderBy,String limit) {
        List<M> result = null;
        Cursor cursor = db.query(getTableName(),  columns, selection, selectionArgs, group, having, orderBy,limit);
        if (cursor != null) {
            result = new ArrayList<M>();
            while (cursor.moveToNext()) {
                M m = getInstantce();
                try {
                    fillField(cursor, m);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                result.add(m);
            }
            cursor.close();
        }
        return result;
    }

    public List<M> findByCondition(String[] columns,String selection,String[] selectionArgs,String orderBy){
        return findByCondition(columns, selection, selectionArgs, null, null, orderBy,null);
    }
    public List<M> findByCondition(String[] columns,String selection,String[] selectionArgs,String orderBy,String limit){
        return findByCondition(columns, selection, selectionArgs, null, null, orderBy, limit);
    }

    private String getTableName() {
        M m = getInstantce();

        TableName tableName = m.getClass().getAnnotation(TableName.class);
        if (tableName != null) {
            return tableName.value();
        }
        return "";
    }

    private void fillColumn(M m, ContentValues values) throws IllegalAccessException {
        Field[] fields = m.getClass().getDeclaredFields();
        for (Field item : fields)
        {
            item.setAccessible(true);
            ID id = item.getAnnotation(ID.class);
            if (id != null) {
                generalContentValue(id.value(), item, m, values);
            }
            Column column = item.getAnnotation(Column.class);
            if (column != null)
            {
                String key = column.value();
                if("".equals(key))
                {
                    key = item.getName();
                }
                generalContentValue(key, item, m, values);
            }

            OneToOne oneToOne = item.getAnnotation(OneToOne.class);
            if(oneToOne != null)
            {
                Class targetEntity = oneToOne.targetEntity();
                Field[] targetEntityFields = targetEntity.getDeclaredFields();
                for (Field entityField : targetEntityFields)
                {
                    entityField.setAccessible(true);
                    ID entityID = entityField.getAnnotation(ID.class);
                    if (entityID != null)
                    {
                        JoinColumn joinColumn = item.getAnnotation(JoinColumn.class);
                        if (null != joinColumn)
                            generalContentValue(joinColumn.name(),entityField,m,values);
                        else
                            generalContentValue(entityField.getName(), entityField, m, values);
                    }
                }
            }
        }
    }

    private void generalContentValue(String key,Field field,M m, ContentValues values) throws IllegalAccessException
    {
        Class type = field.getType();
        System.out.println(field.getName() + " is type of " + type);
        if(type.equals(String.class))
            if(null != field.get(m))
            {
                values.put(key, field.get(m).toString());
            }else{
                values.put(key,"");
            }
        if(type.equals(Integer.class))
            if(null != field.get(m))
            {
                values.put(key,(Integer)field.get(m));
            }else{
                values.put(key,0);
            }

        if(type.equals(Double.class))
            if(null != field.get(m))
            {
                values.put(key,(Double)field.get(m));
            }else{
                values.put(key,0);
            }

        if(type.equals(Long.class))
            if(null != field.get(m))
            {
                values.put(key,(Long)field.get(m));
            }else{
                values.put(key,0);
            }

        if(type.equals(Boolean.class))
            if(null != field.get(m))
            {
                values.put(key,(Boolean)field.get(m));
            }else{
                values.put(key,1);
            }

    }

    private void generalFieldValue(Field field,Cursor cursor, M m,int index) throws IllegalAccessException
    {
        Class type = field.getType();
        System.out.println(field.getName() + " is type of " + type);
        if(type.equals(String.class))
            field.set(m,cursor.getString(index));

        if(type.equals(Integer.class))
            field.set(m,cursor.getInt(index));

        if(type.equals(Double.class))
            field.set(m,cursor.getDouble(index));

        if(type.equals(Long.class))
            field.set(m,cursor.getLong(index));

        if(type.equals(Boolean.class))
            field.set(m,cursor.getInt(index) == 0);
    }

    private void fillField(Cursor cursor, M m) throws IllegalAccessException {
        Field[] fields = m.getClass().getDeclaredFields();
        for (Field item : fields) {
            item.setAccessible(true);
            ID id = item.getAnnotation(ID.class);
            if (id != null) {
                int columnIndex = cursor.getColumnIndex(id.value());
                generalFieldValue(item,cursor,m,columnIndex);
            }
            Column column = item.getAnnotation(Column.class);
            if (column != null) {
                int columnIndex = cursor.getColumnIndex(column.value());
                generalFieldValue(item,cursor,m,columnIndex);
            }
        }
    }

    private String getId(M m) {
        Field[] fields = m.getClass().getDeclaredFields();
        for (Field item : fields) {
            item.setAccessible(true);
            ID id = item.getClass().getAnnotation(ID.class);
            if (id != null) {
                try {
                    return item.get(m).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private M getInstantce() {
        Class clazz = getClass();
        Type superclass = clazz.getGenericSuperclass();
        if(superclass != null && superclass instanceof ParameterizedType){
            Type[] type = ((ParameterizedType) superclass).getActualTypeArguments();
            try {
                return (M) ((Class)type[0]).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
