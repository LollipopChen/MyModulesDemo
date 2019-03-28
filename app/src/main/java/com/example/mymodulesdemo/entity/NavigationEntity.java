package com.example.mymodulesdemo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.libbase.console.SNResponseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 体系数据
 * @author ChenQiuE
 * Date：2019/3/25 15:52
 * Email：qiue.chen@supernovachina.com
 */
public class NavigationEntity extends SNResponseEntity implements Serializable {
    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public class DataEntity implements Serializable {
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

    public static class ItemEntity implements Parcelable {
        private String courseId;
        private String id;
        private String name;
        private String order;
        private String parentChapterId;
        private String userControlSetTop;
        private String visible;

        protected ItemEntity(Parcel in) {
            courseId = in.readString();
            id = in.readString();
            name = in.readString();
            order = in.readString();
            parentChapterId = in.readString();
            userControlSetTop = in.readString();
            visible = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(courseId);
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(order);
            dest.writeString(parentChapterId);
            dest.writeString(userControlSetTop);
            dest.writeString(visible);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ItemEntity> CREATOR = new Creator<ItemEntity>() {
            @Override
            public ItemEntity createFromParcel(Parcel in) {
                return new ItemEntity(in);
            }

            @Override
            public ItemEntity[] newArray(int size) {
                return new ItemEntity[size];
            }
        };

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
