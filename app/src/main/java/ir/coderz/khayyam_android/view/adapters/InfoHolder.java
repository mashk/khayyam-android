package ir.coderz.khayyam_android.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ir.coderz.khayyam_android.R;

/**
 * Created by sajad on 7/2/15.
 */
public class InfoHolder extends RecyclerView.ViewHolder {

    TextView editorNameTextView;

    public InfoHolder(View itemView) {
        super(itemView);

        editorNameTextView = (TextView) itemView.findViewById(R.id.editor_name);
    }

    public void setName(String name) {
        editorNameTextView.setText(name);
    }

    public String getName() {
        return editorNameTextView.getText().toString();
    }
}
