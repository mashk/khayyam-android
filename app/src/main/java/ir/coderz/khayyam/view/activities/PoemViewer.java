package ir.coderz.khayyam.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ir.coderz.khayyam.R;
import ir.coderz.khayyam.Util;
import ir.coderz.khayyam.model.entities.poem.Poem;

public class PoemViewer extends AppCompatActivity {
    @Bind(R.id.poem_view)
    TextView poemView;
    Poem poem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_viewer);
        ButterKnife.bind(this);
        if (getIntent().getExtras() == null) {
            finish();
        }
        poem = Util.bundleToPoem(getIntent().getExtras());

        poemView.setText(poem.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poem_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
