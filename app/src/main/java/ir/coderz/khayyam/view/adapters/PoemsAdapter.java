package ir.coderz.khayyam.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ir.coderz.khayyam.R;
import ir.coderz.khayyam.model.entities.poem.Poem;

/**
 * Created by sajad on 7/6/15.
 */
public class PoemsAdapter extends RecyclerView.Adapter<PoemItemHolder> {
    LayoutInflater inflater;
    Context context;

    List<Poem> poems;

    RecyclerClickListener recyclerClickListener;

    public Poem getPoemAt(int position){
        return poems.get(position);
    }

    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener) {
        this.recyclerClickListener = recyclerClickListener;
    }

    public void setPoems(List<Poem> poems) {
        this.poems = poems;
        notifyDataSetChanged();
    }

    public PoemsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        poems = new ArrayList<>();
    }

    @Override
    public PoemItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.poem_item, parent, false);
        PoemItemHolder poemItemHolder = new PoemItemHolder(view,recyclerClickListener);
        return poemItemHolder;
    }

    @Override
    public void onBindViewHolder(PoemItemHolder holder, int position) {
        holder.setPoem(poems.get(position).getOne());
    }

    @Override
    public int getItemCount() {
        return poems.size();
    }


}
