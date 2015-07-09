package ir.coderz.khayyam.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ir.coderz.khayyam.R;

/**
 * Created by sajad on 7/2/15.
 */
public class InfoHolder extends RecyclerView.ViewHolder {

    TextView editorNameTextView;

    public InfoHolder(View itemView, RecyclerClickListener recyclerClickListener) {
        super(itemView);
        editorNameTextView = (TextView) itemView.findViewById(R.id.editor_name);
        itemView.setOnClickListener(
                view -> recyclerClickListener.onItemClick(getAdapterPosition())
        );
    }


    public void setName(String name) {
        editorNameTextView.setText(name);
    }

    public String getName() {
        return editorNameTextView.getText().toString();
    }
}
