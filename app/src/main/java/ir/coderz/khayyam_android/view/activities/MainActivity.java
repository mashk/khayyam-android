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

import butterknife.Bind;
import butterknife.ButterKnife;
import ir.coderz.khayyam_android.R;
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


    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

        recycler.setLayoutManager(new LinearLayoutManager(this));
        InfoAdapter infoAdapter = new InfoAdapter(this);
        recycler.setAdapter(infoAdapter);

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
