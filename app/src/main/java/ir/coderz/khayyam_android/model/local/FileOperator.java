package ir.coderz.khayyam_android.model.local;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

/**
 * Created by sajad on 7/9/15.
 */
public class FileOperator {
    Context context;

    @Inject
    public FileOperator(Context context) {
        this.context = context;
    }

    public void save(String file, String content) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes("UTF8"));
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String load(String file) {
        StringBuffer content = new StringBuffer("");
        try {
            FileInputStream fileInputStream = context.openFileInput(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF8");
            int b;
            while ((b = inputStreamReader.read()) != -1) {
                content.append((char) b);
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
