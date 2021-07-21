package sg.edu.rp.c346.id20011066.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button btnFilter;
    Spinner spinnerYear;
    ListView lvSongs;

    Song song;

    ArrayList<Song> alSong;
    ArrayAdapter<Song> aaSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        DBHelper dbHelper = new DBHelper(ShowActivity.this);

        btnFilter = findViewById(R.id.btnFilter);
        spinnerYear = findViewById(R.id.spinnerYear);
        lvSongs = findViewById(R.id.lvSongs);

        Intent i = getIntent();


        alSong = new ArrayList<Song>();
        alSong.addAll(dbHelper.getAllSongs());
        aaSong = new ArrayAdapter<>(ShowActivity.this, android.R.layout.simple_list_item_1, alSong);
        lvSongs.setAdapter(aaSong);
        aaSong.notifyDataSetChanged();

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song1 = alSong.get(position);
                Intent intent = new Intent(ShowActivity.this, EditActivity.class);
                intent.putExtra("song", song1);
                startActivity(intent);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper1 = new DBHelper(ShowActivity.this);
                alSong.clear();
                alSong.addAll(dbHelper1.getAllSongsbyStars("5"));
                aaSong.notifyDataSetChanged();
            }
        });
    }
}