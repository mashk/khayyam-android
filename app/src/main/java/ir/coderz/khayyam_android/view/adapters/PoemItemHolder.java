package ir.coderz.khayyam_android.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ir.coderz.khayyam_android.R;

/**
 * Created by sajad on 7/6/15.
 */
public class PoemItemHolder extends RecyclerView.ViewHolder {
    private TextView poemText;

    public PoemItemHolder(View itemView) {
        super(itemView);
        poemText = (TextView) itemView.findViewById(R.id.poem_item);
    }

    public void setPoem(String text) {
        poemText.setText(text);
    }
}
