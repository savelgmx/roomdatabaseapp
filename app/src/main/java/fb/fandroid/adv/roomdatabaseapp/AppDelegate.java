package fb.fandroid.adv.roomdatabaseapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import fb.fandroid.adv.roomdatabaseapp.database.MusicDatabase;

/**
 * Created by Administrator on 14.12.2018.
 */

public class AppDelegate extends Application {

    private MusicDatabase mMusicDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mMusicDatabase = Room.databaseBuilder(getApplicationContext(), MusicDatabase.class, "music_database")
                .allowMainThreadQueries()
                .build();
    }

    public MusicDatabase getMusicDatabase() {
        return mMusicDatabase;
    }
}
