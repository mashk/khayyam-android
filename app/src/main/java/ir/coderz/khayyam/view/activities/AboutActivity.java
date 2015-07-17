package ir.coderz.khayyam.view.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import ir.coderz.khayyam.R;

public class AboutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String khayyamAndroidProjectURL = "https://github.com/mashk/khayyam-android";
    String mashURL = "https://github.com/mashk";
    String coderzURL = "http://coderz.ir";

    @Bind(R.id.about_nav)
    NavigationView aboutNav;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        aboutNav.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.go_to_mask: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mashURL));
                startActivity(intent);
                break;
            }
            case R.id.go_to_khayyam_android: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(khayyamAndroidProjectURL));
                startActivity(intent);
                break;
            }
            case R.id.go_to_khayyam_licence: {
                break;
            }
            case R.id.go_to_open_source_licence: {
                break;
            }
            case R.id.go_to_contributors: {
                break;
            }
            case R.id.go_to_coderz: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(coderzURL));
                startActivity(intent);
                break;
            }
        }
        return false;
    }
}
