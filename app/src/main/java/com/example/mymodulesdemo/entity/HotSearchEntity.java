package com.example.mymodulesdemo.entity;

import com.example.libbase.console.SNResponseEntity;

import java.util.List;

/**
 * 搜索 热门词
 * @author ChenQiuE
 * Date：2019/3/20 10:39
 * Email：1077503420@qq.com
 */
public class HotSearchEntity extends SNResponseEntity {
    private List<ItemEntity> data;

    public List<ItemEntity> getData() {
        return data;
    }

    public class ItemEntity{
        private String id;
        private String link;
        private String name;
        private String order;
        private String visible;

        public String getId() {
            return id;
        }

        public String getLink() {
            return link;
        }

        public String getName() {
            return name;
        }

        public String getOrder() {
            return order;
        }

        public String getVisible() {
            return visible;
        }
    }
}
