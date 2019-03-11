package com.example.mymodulesdemo.entity;

import com.example.libbase.console.SNResponseEntity;

import java.util.List;

/**
 * @author ChenQiuE
 * Date：2019/3/8 11:34
 * Email：1077503420@qq.com
 */
public class BannerEntity extends SNResponseEntity {
    private List<BannerItemEntity> data;

    public List<BannerItemEntity> getData() {
        return data;
    }

    public class BannerItemEntity{
        private String desc;
        private String id;
        private String imagePath;
        private String isVisible;
        private String order;
        private String title;
        private String type;
        private String url;

        public String getDesc() {
            return desc;
        }

        public String getId() {
            return id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getIsVisible() {
            return isVisible;
        }

        public String getOrder() {
            return order;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }
    }
}
