package ir.coderz.khayyam.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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
import ir.coderz.khayyam.KhayyamApp;
import ir.coderz.khayyam.R;
import ir.coderz.khayyam.Util;
import ir.coderz.khayyam.domain.GetPoemsUseCase;
import ir.coderz.khayyam.injector.HasRepoComponent;
import ir.coderz.khayyam.injector.component.DaggerRepoComponent;
import ir.coderz.khayyam.injector.component.RepoComponent;
import ir.coderz.khayyam.injector.module.RepoModule;
import ir.coderz.khayyam.model.Preference;
import ir.coderz.khayyam.view.adapters.PoemsAdapter;

public class PoemListActivity extends AppCompatActivity implements HasRepoComponent<RepoComponent> {

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appBarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.refresher)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recycler)
    RecyclerView recycler;


    @Inject
    GetPoemsUseCase getPoemsUseCase;
    @Inject
    Preference preference;
    private PoemsAdapter poemsAdapter;
    private RepoComponent repoComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_list);
        ButterKnife.bind(this);
        initializeToolbar();
        initializeRecycler();
        initializeRefresher();
        initializeDependency();
        getPoems();
    }

    AppBarLayout.OnOffsetChangedListener onAppBarOffsetChangeListener
            = (appBarLayout1, i) ->
            refreshLayout.setEnabled(i == 0);

    @Override
    protected void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(onAppBarOffsetChangeListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(onAppBarOffsetChangeListener);
    }

    private void getPoems() {
        refreshLayout.measure(1, 1);
        refreshLayout.setRefreshing(true);
        getPoemsUseCase.execute().subscribe
                (
                        poems -> poemsAdapter.setPoems(poems),
                        throwable ->
                        {
                            refreshLayout.setRefreshing(false);
                            Log.v("Error", throwable.getMessage());
                        }
                        ,
                        () -> refreshLayout.setRefreshing(false)

                );
    }

    private void initializeRefresher() {
        refreshLayout.setOnRefreshListener(
                () -> {
                    preference.resetField(getIntent().getStringExtra(Util.EDITOR_URL));
                    repoComponent.injectPoemList(this);
                    getPoems();
                }
        );
    }

    private void initializeDependency() {
        KhayyamApp khayyamApp = (KhayyamApp) getApplication();
        repoComponent = DaggerRepoComponent.builder()
                .repoModule(new RepoModule(getIntent().getStringExtra(Util.EDITOR_URL), this))
                .appComponent(khayyamApp.getComponent())
                .build();
        repoComponent.injectPoemList(this);

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

    @Override
    public RepoComponent getComponent() {
        return repoComponent;
    }
}
