package org.news.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.news.R;

import java.util.Random;

/**
 * Created by virgil on 2016/5/23.
 */
public class WelcomeActivity extends Activity {
    ImageView mBackgroundImage;
    TextView mTitleText;
    TextView mVersionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        View decorView = getWindow().getDecorView();
        int uiOptions = decorView.getSystemUiVisibility();
        int newOptions = uiOptions;
        // 隐藏导航栏
        newOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(newOptions);
        mBackgroundImage = (ImageView) findViewById(R.id.image_background);
        int drawable = R.drawable.pic_background_1;
        Random random = new Random();
        int num = random.nextInt(4);
        switch (num) {
            case 0:
                break;
            case 1:
                drawable = R.drawable.pic_background_2;
                break;
            case 2:
                drawable = R.drawable.pic_background_3;
                break;
            case 3:
                drawable = R.drawable.pic_background_4;
                break;
        }
        mBackgroundImage.setImageDrawable(getResources().getDrawable(drawable));
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.image_welcome);
        mBackgroundImage.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //open index after animation end
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.activity_slide_in, R.anim.no_anim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mTitleText = (TextView) findViewById(R.id.title_text);
        mVersionText = (TextView) findViewById(R.id.version_text);

    }

    @Override
    public void finish() {
        mBackgroundImage.destroyDrawingCache();
        super.finish();
    }
}
