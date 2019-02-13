package fb.fandroid.adv.roomdatabaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fb.fandroid.adv.roomdatabaseapp.database.Album;
import fb.fandroid.adv.roomdatabaseapp.database.AlbumSong;
import fb.fandroid.adv.roomdatabaseapp.database.MusicDao;
import fb.fandroid.adv.roomdatabaseapp.database.Song;

public class MainActivity extends AppCompatActivity {
    private Button mAddBtn;
    private Button mGetBtn;


    /*
        В таблицы Album, Songs, AlbumSongs добавить генерацию данных, примерно 3 записи.
                (см. пример генерации в методе MainActivity#createAlbums()) по кнопке Add.
        В MusicDao - query, insert, update, delete методы для таблиц Songs и AlbumSongs по примеру методов для таблицы Album.+++
        При нажатии на кнопку Get показывать тост с содержимым всех 3х таблиц.(3*3 записей)+++
        Изучить реализацию методов query()/insert()/update()/delete() в MusicProvider +++
        По аналогии добавить в MusicProvider работу с таблицами Songs и AlbumSongs.
    */
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MusicDao musicDao = ((AppDelegate) getApplicationContext()).getMusicDatabase().getMusicDao();

        mAddBtn = (findViewById(R.id.add));
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Roomdatabaseapp","MainActivity insertAlbums(createAlbums) called");

                musicDao.insertAlbums(createAlbums());
                musicDao.insertSongs(createSongs());
                musicDao.insertAlbumSongs(createAlbumSongs());

            }
        });

        mGetBtn = findViewById(R.id.get);
        mGetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(musicDao.getAlbums(),musicDao.getSongs(),musicDao.getAlbumSongs());
            }
        });

    }
    private List<Album> createAlbums() {
        List<Album> albums = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            albums.add(new Album(i, "album " + i, "release" + System.currentTimeMillis()));
        }

        return albums;
    }
    private List<Song> createSongs(){

        List<Song> songs =new ArrayList<>(3);
        for (int i=0;i<3;i++){
            songs.add(new Song(i,"song "+i,"duration"+System.currentTimeMillis()));
        }
        return songs;
    }

    private List<AlbumSong> createAlbumSongs(){
        List<AlbumSong> albumSongs = new ArrayList<>(3);
        final MusicDao musicDao = ((AppDelegate) getApplicationContext()).getMusicDatabase().getMusicDao();
        for (int i=0;i<3;i++){
            //сначала выдергиваем album_id из таблицы Album  и song_id из таблицы Song
            //и только потом добавляем их
            //для Album

            int albumId=musicDao.getAlbumIdWithId(i);
            int songId=musicDao.getSongIdWithId(i);
            Log.d("Roomdatabaseapp","albumId="+albumId+" songId="+songId);

            albumSongs.add(new AlbumSong(i, albumId, songId)); //id,album_d,song_id

        }
        return albumSongs;
    }


    private void showToast(List<Album> albums,List<Song> songs,List<AlbumSong> albumSongs) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, size = albums.size(); i < size; i++) {
            builder.append(albums.get(i).toString()).append("\n");
        }
        for (int i=0,size=songs.size();i<size;i++)  {
            builder.append(songs.get(i).toString()).append("\n");
        }

        for (int i = 0, size = albumSongs.size(); i < size; i++) {
            builder.append(albumSongs.get(i).toString()).append("\n");
        }
        Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();

    }
}
