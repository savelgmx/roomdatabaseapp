package fb.fandroid.adv.roomdatabaseapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

/**
 * @author Azret Magometov
 */

@Dao
public interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbums(List<Album> albums);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbum(Album albums);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSongs(List<Song> songs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSong(Song song);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setLinksAlbumSongs(List<AlbumSong> linksAlbumSongs);

    @Query("select * from album")
    List<Album> getAlbums();

    @Query("select * from album")
    Cursor getAlbumsCursor();

    @Query("select * from album where id = :albumId")
    Cursor getAlbumWithIdCursor(int albumId);

    @Query("select * from song")
    List<Song> getSongs();

    @Query("select * from song")
    Cursor getSongsCursor();


    @Query("Select * from AlbumSong where album_id=:albumId and song_id= :songId")
    Cursor getAlbumSongsWithAlbumIdAndSongId(int albumId,int songId);

    @Delete
    void deleteAlbum(Album album);

    //получить список песен переданного id альбома
    @Query("select * from song inner join albumsong on song.id = albumsong.song_id where album_id = :albumId")
    List<Song> getSongsFromAlbum(int albumId);

    //обновить информацию об альбоме
    @Update
    int updateAlbumInfo(Album album);
    //обновить информацию о песне
    @Update
    int updateSongInfo(Song song);
    //обновить информацию о песне в AlbumSongs
    @Update
    int updateAlbumSonginfo(AlbumSong albumSong);


    //удалить альбом по id
    @Query("DELETE FROM album where id = :albumId")
    int deleteAlbumById(int albumId);
    //удалить песни по id
    @Query("DELETE FROM songs where id=:songId")
    int deleteSongById(int songId);
    //удалить песни альбомов по id
    //TODO удалить песни альбомов по id


}
