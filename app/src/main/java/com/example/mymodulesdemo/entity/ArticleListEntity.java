package com.example.mymodulesdemo.entity;

import com.example.libbase.console.SNResponseEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author ChenQiuE
 * Date：2019/3/11 17:10
 * Email：1077503420@qq.com
 */
public class ArticleListEntity extends SNResponseEntity {
    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public class DataEntity{
        private int curPage;
        @SerializedName("datas")
        private List<ItemEntity> dataList;

        public int getCurPage() {
            return curPage;
        }

        public List<ItemEntity> getDataList() {
            return dataList;
        }
    }

    public class ItemEntity{
        private String apkLink;
        private String author;
        private String chapterId;
        private String chapterName;
        private String collect;
        private String courseId;
        private String desc;
        private String envelopePic;
        private String fresh;
        private String id;
        private String link;
        private String niceDate;
        private String origin;
        private String projectLink;
        private String publishTime;
        private String superChapterId;
        private String superChapterName;
        private List<TagsEntity> tags;
        private String title;
        private String type;
        private String userId;
        private String visible;
        private String zan;

        public String getApkLink() {
            return apkLink;
        }

        public String getAuthor() {
            return author;
        }

        public String getChapterId() {
            return chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public String getCollect() {
            return collect;
        }

        public String getCourseId() {
            return courseId;
        }

        public String getDesc() {
            return desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public String getFresh() {
            return fresh;
        }

        public String getId() {
            return id;
        }

        public String getLink() {
            return link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public String getSuperChapterId() {
            return superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String getUserId() {
            return userId;
        }

        public String getVisible() {
            return visible;
        }

        public String getZan() {
            return zan;
        }
    }

    public class TagsEntity {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
