package ir.coderz.khayyam_android.model.entities.information;

import java.util.List;

/**
 * Created by sajad on 6/29/15.
 */
public class Language {
    private String lang;
    private String title;
    private String direction;
    private List<Edition> editions;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public void setEditions(List<Edition> editions) {
        this.editions = editions;
    }
}
