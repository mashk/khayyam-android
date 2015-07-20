package ir.coderz.khayyam.model;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by sajad on 7/9/15.
 */
public class Preference {
    Context context;

    @Inject
    public Preference(Context context) {
        this.context = context;
    }

    private SharedPreferences getSharedPreference() {
        return context.getSharedPreferences("info", Context.MODE_PRIVATE);
    }

    public void writeToPreference(String key, String value) {
        SharedPreferences preferences = getSharedPreference();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getPreference(String key) {
        SharedPreferences preferences = getSharedPreference();
        return preferences.getString(key, "");
    }

    public void resetAll() {
        SharedPreferences preferences = getSharedPreference();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public void resetField(String field) {
        SharedPreferences preferences = getSharedPreference();
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(field);
        editor.commit();
    }
}
