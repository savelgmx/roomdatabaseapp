package fb.fandroid.adv.roomdatabaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fb.fandroid.adv.roomdatabaseapp.database.Album;
import fb.fandroid.adv.roomdatabaseapp.database.MusicDao;

public class MainActivity extends AppCompatActivity {
    private Button mAddBtn;
    private Button mGetBtn;

/*
    В таблицы Album, Songs, AlbumSongs добавить генерацию данных, примерно 3 записи.
            (см. пример генерации в методе MainActivity#createAlbums()) по кнопке Add.
    В MusicDao - query, insert, update, delete методы для таблиц Songs и AlbumSongs по примеру методов для таблицы Album.
    При нажатии на кнопку Get показывать тост с содержимым всех 3х таблиц.(3*3 записей)
    Изучить реализацию методов query()/insert()/update()/delete() в MusicProvider
    По аналогии добавить в MusicProvider работу с таблицами Songs и AlbumSongs.
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MusicDao musicDao = ((AppDelegate) getApplicationContext()).getMusicDatabase().getMusicDao();

        mAddBtn = (findViewById(R.id.add));
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicDao.insertAlbums(createAlbums());
            }
        });

        mGetBtn = findViewById(R.id.get);
        mGetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(musicDao.getAlbums());
            }
        });

    }

    private List<Album> createAlbums() {
        List<Album> albums = new ArrayList<>(10);
        for (int i = 0; i < 30; i++) {
            albums.add(new Album(i, "album " + i, "release" + System.currentTimeMillis()));
        }

        return albums;
    }

    private void showToast(List<Album> albums) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, size = albums.size(); i < size; i++) {
            builder.append(albums.get(i).toString()).append("\n");
        }

        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();

    }
}
