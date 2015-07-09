package ir.coderz.khayyam.model.entities.information;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajad on 6/29/15.
 */
public class Info {
    List<Language> languages;

    public Info() {
        languages = new ArrayList<>();
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
