package org.news.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.widget.Toolbar;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.umeng.analytics.MobclickAgent;

import org.news.R;
import org.news.utils.SystemBarTintManager;

/**
 * Created by virgil on 2016/5/25.
 */
public class BaseActivity extends Activity {
    protected Toolbar toolbar;
    SystemBarTintManager mTintManager;
    protected final int CURRENT_VERSION = Build.VERSION.SDK_INT;
    protected final int VERSION_KITKAT = Build.VERSION_CODES.KITKAT;
    protected final int VERSION_LOLLIPOP = Build.VERSION_CODES.LOLLIPOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用tintManager设置状态栏的颜色
        mTintManager = new SystemBarTintManager(this);
        if (isNavBarTransparent()) {
            mTintManager.setStatusBarTintEnabled(true);
            if (isHasNavigationBar()) {
                mTintManager.setNavigationBarTintEnabled(true);
            } else {
                mTintManager.setNavigationBarTintEnabled(false);
            }
        }
        mTintManager.setTintColor(getResources().getColor(R.color.dark_primary_color));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no_anim, R.anim.activity_slide_out);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 是否将导航栏以及状态栏设为透明（API大于19小与21)
     *
     * @return
     */
    protected boolean isNavBarTransparent() {
        return CURRENT_VERSION >= VERSION_KITKAT && VERSION_LOLLIPOP > CURRENT_VERSION;
    }

    /**
     * 根据int得出对应的图标状态
     *
     * @param state
     * @return 图标状态，默认为汉堡型
     */
    protected MaterialMenuDrawable.IconState intToState(int state) {
        switch (state) {
            case 0:
                return MaterialMenuDrawable.IconState.BURGER;
            case 1:
                return MaterialMenuDrawable.IconState.ARROW;
            case 2:
                return MaterialMenuDrawable.IconState.X;
            case 3:
                return MaterialMenuDrawable.IconState.CHECK;
            default:
                return MaterialMenuDrawable.IconState.BURGER;
        }
    }


    //通过是否有物理按键判断是否有虚拟按键
    protected boolean isHasNavigationBar() {
        boolean hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        return !(hasBackKey && hasMenuKey);
    }

    /**
     * 获得当前系统版本号
     *
     * @return
     */
    protected int getVersionNumber() {
        return Build.VERSION.SDK_INT;
    }

    protected int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    protected int getScreenHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeArray = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray typedArray = obtainStyledAttributes(typedValue.data, textSizeArray);
        int actionBarSize = typedArray.getDimensionPixelSize(indexOfAttrTextSize, -1);
        typedArray.recycle();
        return actionBarSize;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void replaceFragment(int viewId, Fragment fragment) {
        getFragmentManager().beginTransaction().replace(viewId, fragment).commit();
    }

    /*
    *获取状态栏高度
    * @return
     */
    protected int getStatusBarHeight() {
        int res = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            res = getResources().getDimensionPixelSize(resourceId);
        }
        return res;
    }

    /*
    *获取导航栏高度
     */
    protected int getNavigationHeight() {
        int res = 0;
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            res = getResources().getDimensionPixelSize(resourceId);
        }
        return res;
    }

    protected static int dp2px(float dpValue) {
        return (int) (dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    protected static int px2dp(float pxValue) {
        return (int) (pxValue / Resources.getSystem().getDisplayMetrics().density);
    }
}
