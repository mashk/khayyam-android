package ir.coderz.khayyam_android.view.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ir.coderz.khayyam_android.R;
import ir.coderz.khayyam_android.domain.GetInfoUseCase;
import ir.coderz.khayyam_android.injector.component.DaggerRepoCompnent;
import ir.coderz.khayyam_android.injector.module.RepoModule;
import ir.coderz.khayyam_android.view.adapters.InfoAdapter;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.drawer)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigation)
    NavigationView navigation;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler)
    RecyclerView recycler;

    @Inject
    GetInfoUseCase infoUseCase;

    private ActionBarDrawerToggle drawerToggle;
    private InfoAdapter infoAdapter;

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
                info -> infoAdapter.setInfo(info)
        );
    }

    private void initializeDependency() {
        DaggerRepoCompnent.builder()
                .repoModule(new RepoModule(""))
                .build().inject(this);

    }

    private void initializeRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        infoAdapter = new InfoAdapter(this);
        recycler.setAdapter(infoAdapter);
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);

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
}
