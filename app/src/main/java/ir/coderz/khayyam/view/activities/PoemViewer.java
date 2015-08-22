package ir.coderz.khayyam.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import ir.coderz.khayyam.R;
import ir.coderz.khayyam.Util;
import ir.coderz.khayyam.model.entities.poem.Poem;

public class PoemViewer extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.poem_view)
    TextView poemView;
    @Bind(R.id.background)
    ImageView backgroundView;
    Poem poem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_viewer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        if (getIntent().getExtras() == null) {
            finish();
        }
        poem = Util.bundleToPoem(getIntent().getExtras());

        poemView.setText(poem.toString());


        Picasso
                .with(this)
                .load("file:///android_asset/wallpaper.jpg")
                .fit()
                .centerCrop()
                .into(backgroundView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poem_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share: {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, poem.toString());
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
