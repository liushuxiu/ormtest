package org.ormtest.step030.entity;

import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * 用户实体助手类
 */
public class UserEntity_Helper {
    /**
     * 将数据集装换为实体对象
     *
     * @param rs 数据集
     * @return
     * @throws Exception
     */
    public UserEntity create(ResultSet rs) throws Exception {
        if (null == rs) {
            return null;
        }

        // 创建新的实体对象
        UserEntity ue = new UserEntity();

        // 获取类的字段数组
        Field[] fArray = ue.getClass().getFields();

        for (Field f : fArray) {
            // 获取字段上注解
            Column annoColumn = f.getAnnotation(Column.class);

            if (annoColumn == null) {
                // 如果注解为空,
                // 则直接跳过...
                continue;
            }

            // 获取列名称
            String colName = annoColumn.name();
            // 从数据库中获取列值
            Object colVal = rs.getObject(colName);

            if (colVal == null) {
                // 如果列值为空,
                // 则直接跳过...
                continue;
            }

            // 设置字段值
            f.set(ue, colVal);
        }

        return ue;
    }
}
