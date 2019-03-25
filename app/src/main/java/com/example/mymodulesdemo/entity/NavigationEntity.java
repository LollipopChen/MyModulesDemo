package com.example.mymodulesdemo.entity;

import com.example.libbase.console.SNResponseEntity;

import java.util.List;

/**
 * 体系数据
 * @author ChenQiuE
 * Date：2019/3/25 15:52
 * Email：qiue.chen@supernovachina.com
 */
public class NavigationEntity extends SNResponseEntity {
    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public class DataEntity{
        private List<ItemEntity> children;
        private String courseId;
        private String id;
        private String name;
        private String order;
        private String parentChapterId;
        private String userControlSetTop;
        private String visible;

        public List<ItemEntity> getChildren() {
            return children;
        }

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

    public class ItemEntity{
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
