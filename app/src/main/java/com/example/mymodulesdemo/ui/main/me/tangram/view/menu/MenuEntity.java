package com.example.mymodulesdemo.ui.main.me.tangram.view.menu;

/**
 * @author ChenQiuE
 * @date 2019/5/23
 */
public class MenuEntity {
    private String id;
    private String thumb;
    private String title;
    private String type;

    @Override public String toString() {
        return "MenuEntity{" +
                "id='" + id + '\'' +
                ", thumb='" + thumb + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
