package ir.coderz.khayyam_android.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ir.coderz.khayyam_android.R;
import ir.coderz.khayyam_android.Util;
import ir.coderz.khayyam_android.domain.GetPoemsUseCase;
import ir.coderz.khayyam_android.injector.component.DaggerRepoCompnent;
import ir.coderz.khayyam_android.injector.module.RepoModule;
import ir.coderz.khayyam_android.view.adapters.PoemsAdapter;

public class PoemListActivity extends AppCompatActivity {

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler)
    RecyclerView recycler;


    @Inject
    GetPoemsUseCase getPoemsUseCase;
    private PoemsAdapter poemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_list);
        ButterKnife.bind(this);
        initializeToolbar();
        initializeRecycler();
        initializeDependency();
        getPoems();
    }

    private void getPoems() {
        getPoemsUseCase.execute().subscribe
                (
                        poems -> poemsAdapter.setPoems(poems),
                        throwable -> Log.v("Error", throwable.getMessage())
                );
    }

    private void initializeDependency() {
        DaggerRepoCompnent.builder()
                .repoModule(new RepoModule(getIntent().getStringExtra(Util.EDITOR_URL)))
                .build().injectPoemList(this);

    }

    private void initializeRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        poemsAdapter = new PoemsAdapter(this);
        poemsAdapter.setRecyclerClickListener(
                position -> {
                    Intent intent = new Intent(this, PoemViewer.class);
                    intent.putExtras(Util.poemToBundle(poemsAdapter.getPoemAt(position)));
                    startActivity(intent);
                }
        );
        recycler.setAdapter(poemsAdapter);


    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra(Util.EDITOR_NAME));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_poem_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
