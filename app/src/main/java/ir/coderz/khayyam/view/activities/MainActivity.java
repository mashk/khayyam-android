package ir.coderz.khayyam.view.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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

public class MainActivity extends AppCompatActivity implements HasRepoComponent<RepoComponent> {


    @Bind(R.id.drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigation)
    NavigationView navigation;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.recycler)
    RecyclerView recycler;

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
        getInfo();

    }

    private void getInfo() {
        infoUseCase.execute().subscribe(
                info -> {
                    infoAdapter.setInfo(info);
                },
                throwable -> Log.v("Error", throwable.getMessage())
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
