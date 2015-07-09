package ir.coderz.khayyam;

import android.os.Bundle;

import ir.coderz.khayyam.model.entities.poem.Poem;

/**
 * Created by sajad on 7/6/15.
 */
public class Util {
    public static final String EDITOR_NAME = "editorName";
    public static final String EDITOR_URL = "editorURL";
    private static final String ONE = "one";
    private static final String TWO = "two";
    private static final String THREE = "three";
    private static final String FOUR = "four";

    public static Bundle poemToBundle(Poem poem) {
        Bundle bundle = new Bundle();
        bundle.putString(ONE, poem.getOne());
        bundle.putString(TWO, poem.getTwo());
        bundle.putString(THREE, poem.getThree());
        bundle.putString(FOUR, poem.getFour());
        return bundle;
    }

    public static Poem bundleToPoem(Bundle bundle) {
        Poem poem = new Poem();
        poem.setOne(bundle.getString(ONE));
        poem.setTwo(bundle.getString(TWO));
        poem.setThree(bundle.getString(THREE));
        poem.setFour(bundle.getString(FOUR));
        return poem;
    }
}
