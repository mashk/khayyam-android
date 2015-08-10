package ir.coderz.khayyam.view.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ir.coderz.khayyam.KhayyamApp;
import ir.coderz.khayyam.R;
import ir.coderz.khayyam.Util;
import ir.coderz.khayyam.domain.GetInfoUseCase;
import ir.coderz.khayyam.injector.HasRepoComponent;
import ir.coderz.khayyam.injector.component.DaggerRepoComponent;
import ir.coderz.khayyam.injector.component.RepoComponent;
import ir.coderz.khayyam.injector.module.RepoModule;
import ir.coderz.khayyam.model.Preference;
import ir.coderz.khayyam.view.adapters.InfoAdapter;

public class MainActivity extends AppCompatActivity implements HasRepoComponent<RepoComponent>, NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigation)
    NavigationView navigation;
    @Bind(R.id.appbar)
    AppBarLayout appBarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.refresher)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.background)
    ImageView backgroundView;

    @Inject
    GetInfoUseCase infoUseCase;
    @Inject
    Preference preference;

    private ActionBarDrawerToggle drawerToggle;
    private InfoAdapter infoAdapter;
    private RepoComponent repoComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeToolbar();
        initializeRecycler();
        initializeDependency();
        initializeRefresher();
        getInfo();

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

    private void getInfo() {
        refreshLayout.measure(1,1);
        refreshLayout.setRefreshing(true);
        infoUseCase.execute().subscribe(
                info -> {
                    infoAdapter.setInfo(info);
                },
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
                    preference.resetAll();
                    repoComponent.injectMain(this);
                    getInfo();
                }
        );
    }

    private void initializeDependency() {
        KhayyamApp khayyamApp = (KhayyamApp) getApplication();
        repoComponent = DaggerRepoComponent.builder()
                .repoModule(new RepoModule("info", this))
                .appComponent(khayyamApp.getComponent())
                .build();
        repoComponent.injectMain(this);

    }

    private void initializeRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        infoAdapter = new InfoAdapter(this);
        infoAdapter.setRecyclerClickListener
                (
                        position -> {
                            Intent intent = new Intent(this, PoemListActivity.class);
                            intent.putExtra(Util.EDITOR_NAME, infoAdapter.getEditorName(position));
                            intent.putExtra(Util.EDITOR_URL, infoAdapter.getEditorPath(position));
                            startActivity(intent);
                        }
                );
        recycler.setAdapter(infoAdapter);
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        collapsingToolbar.setTitle(getString(R.string.title_activity_main));
        drawerToggle = new ActionBarDrawerToggle
                (
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.drawer_open,
                        R.string.drawer_close
                );
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigation.setNavigationItemSelectedListener(this);

        Picasso
                .with(this)
                .load("file:///android_asset/wallpaper.jpg")
                .fit()
                .centerCrop()
                .into(backgroundView);

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_about: {
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public RepoComponent getComponent() {
        return repoComponent;
    }
}
