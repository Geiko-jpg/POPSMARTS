package android.example.com.popsmarts;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class QBBackgroundMSetter extends Service {
    // - - > DECLARATION
    private MediaPlayer backgroundMusic;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        backgroundMusic = MediaPlayer.create(this, R.raw.quizbeeg);
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(100, 100);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        backgroundMusic.start();
        return START_STICKY;
    }

    public void onStart(Intent intent, int startId) {
    }

    public IBinder onUnBind(Intent arg0) {
        return null;
    }
    public void onStop() {
    }
    public void onPause() {
    }

    @Override
    public void onDestroy() {
        backgroundMusic.stop();
        backgroundMusic.release();
    }
}
