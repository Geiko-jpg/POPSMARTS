package android.example.com.popsmarts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3500;

    Animation topAnim, bottomAnim, shadowAnim;
    ImageView image, shadowimage;
    TextView logo, slogan, shadowlogo, shadowslogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        shadowAnim = AnimationUtils.loadAnimation(this, R.anim.shadow_animation);

        image = findViewById(R.id.imageView);
        shadowimage = findViewById(R.id.imageView2);
        logo = findViewById(R.id.textView);
        shadowlogo = findViewById(R.id.textView3);
        slogan = findViewById(R.id.textView2);
        shadowslogan = findViewById(R.id.textView4);

        image.setAnimation(topAnim);
        shadowimage.setAnimation(shadowAnim);
        logo.setAnimation(bottomAnim);
        shadowlogo.setAnimation(shadowAnim);
        slogan.setAnimation(bottomAnim);
        shadowslogan.setAnimation(shadowAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, StartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}