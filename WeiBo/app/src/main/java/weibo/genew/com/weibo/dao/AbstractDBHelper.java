package weibo.genew.com.weibo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/9.
 */
public abstract class AbstractDBHelper<M> extends SQLiteOpenHelper
{
    private static final String NAME = "my.db";
    private static final int VERSION = 1;
    private static final int OLDVERSION = 2;
    private static final int NEWVERSION = 3;
    protected Class<M> entityClass;

    public AbstractDBHelper(Context context)
    {
        super(context, NAME, null, VERSION);
    }

    protected String getTableName()
    {
        TableName tableName = entityClass.getAnnotation(TableName.class);
        if (tableName != null) {
            return tableName.value();
        }
        return "table_name";
    }

    protected String getTableId()
    {
        Field[] fields = entityClass.getDeclaredFields();
        for (Field item : fields) {
            item.setAccessible(true);
            ID id = item.getAnnotation(ID.class);
            if (id != null) {
                return id.value();
            }
        }
        return "_id";
    }

    protected Map<String,String > getColumsMap()
    {
        Map<String,String> colums = new HashMap<>();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field item : fields) {
            item.setAccessible(true);
            Column column = item.getAnnotation(Column.class);
            if (column != null) {
                Class type = item.getType();
                System.out.println(item.getName() + " is type of " + type);
                String typeString = "text";
                if(type.equals(String.class))
                    typeString = "text";
                if(type.equals(Integer.class))
                    typeString = "int";
                if(type.equals(Double.class))
                    typeString = "double";
                if(type.equals(Long.class))
                    typeString = "bigint";
                if(type.equals(Boolean.class))
                    typeString = "bit";
                if("".equals(column.value()))
                {
                    colums.put(item.getName(),typeString);
                }else{
                    colums.put(column.value(),typeString);
                }
            }

            OneToOne oneToOne = item.getAnnotation(OneToOne.class);
            if(oneToOne != null)
            {
                Class targetEntity = oneToOne.targetEntity();
                Field[] targetEntityFields = targetEntity.getDeclaredFields();
                for (Field entityField : targetEntityFields)
                {
                    entityField.setAccessible(true);
                    ID id = entityField.getAnnotation(ID.class);
                    if (id != null)
                    {
                        Class type = entityField.getType();
                        String typeString = "text";
                        if(type.equals(String.class))
                            typeString = "text";
                        if(type.equals(Integer.class))
                            typeString = "int";
                        if(type.equals(Double.class))
                            typeString = "double";
                        if(type.equals(Long.class))
                            typeString = "bigint";
                        JoinColumn joinColumn = item.getAnnotation(JoinColumn.class);
                        if (null != joinColumn)
                            colums.put(joinColumn.name(), typeString);
                        else
                            colums.put(entityField.getName() + "_id", typeString);
                    }
                }
            }
        }
        return colums;
    }

    /**
     * 创建数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建表
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ");
        builder.append(getTableName());
        builder.append("(");
        builder.append(getTableId());
        builder.append(" integer primary key autoincrement,");
        Map<String, String> colums = getColumsMap();
        for(String columName : colums.keySet())
        {
            builder.append(columName);
            builder.append(" ");
            builder.append(colums.get(columName));
            builder.append(", ");
        }
        String sql = builder.substring(0, builder.length()-2);
        sql += ")";
        Log.i(AbstractDBHelper.NAME, "Creating datebase with sql: " + sql);
        db.execSQL(sql);
        onUpgrade(db, OLDVERSION, NEWVERSION);
    }

    // TODO 新表以及版本维护问题
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case OLDVERSION:

            case NEWVERSION:

                break;
        }
    }
}
