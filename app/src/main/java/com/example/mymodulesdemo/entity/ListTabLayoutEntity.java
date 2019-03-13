package com.example.mymodulesdemo.entity;

import com.example.libbase.console.SNResponseEntity;

import java.util.List;

/**
 * @author ChenQiuE
 * Date：2019/3/13 14:26
 * Email：1077503420@qq.com
 */
public class ListTabLayoutEntity extends SNResponseEntity {
    private List<ItemsEntity> data;

    public List<ItemsEntity> getData() {
        return data;
    }

    public class ItemsEntity{
        private String courseId;
        private String id;
        private String name;
        private String order;
        private String parentChapterId;
        private String userControlSetTop;
        private String visible;

        public String getCourseId() {
            return courseId;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getOrder() {
            return order;
        }

        public String getParentChapterId() {
            return parentChapterId;
        }

        public String getUserControlSetTop() {
            return userControlSetTop;
        }

        public String getVisible() {
            return visible;
        }
    }
}
