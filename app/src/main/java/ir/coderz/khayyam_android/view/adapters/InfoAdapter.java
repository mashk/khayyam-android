package ir.coderz.khayyam_android.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ir.coderz.khayyam_android.R;
import ir.coderz.khayyam_android.model.entities.information.Edition;
import ir.coderz.khayyam_android.model.entities.information.Info;
import ir.coderz.khayyam_android.model.entities.information.Language;

/**
 * Created by sajad on 7/2/15.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoHolder> {
    Context context;
    private Info info;
    private List<Edition> edition;

    public InfoAdapter(Context context) {
        this.context = context;
        info = new Info();
        edition = new ArrayList<>();
    }

    public void setInfo(Info info) {
        this.info = info;
        edition.clear();
        for (Language language : info.getLanguages()) {
            edition.addAll(language.getEditions());
        }
        notifyDataSetChanged();
    }

    public String getEditorPath(int position) {
        return edition.get(position).getUrl();
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.info_item, parent, false);
        InfoHolder infoHolder = new InfoHolder(itemView);

        return infoHolder;
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {
        holder.setName(edition.get(position).getBy());
    }

    @Override
    public int getItemCount() {
        return edition.size();
    }
}
