package ir.coderz.khayyam.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ir.coderz.khayyam.R;

/**
 * Created by sajad on 7/6/15.
 */
public class PoemItemHolder extends RecyclerView.ViewHolder {
    private TextView poemText;

    public PoemItemHolder(View itemView, RecyclerClickListener recyclerClickListener) {
        super(itemView);
        poemText = (TextView) itemView.findViewById(R.id.poem_item);

        itemView.setOnClickListener(
                view -> recyclerClickListener.onItemClick(getAdapterPosition())
        );
    }

    public void setPoem(String text) {
        poemText.setText(text);
    }
}
