package org.news.ui;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import org.news.R;
import org.news.ui.fragement.NewsListFragment;

import java.util.List;

/**
 * Created by virgil on 2016/5/23.
 */
public class MainActivity extends BaseActivity implements ObservableScrollViewCallbacks {
    private ViewPager mViewPager;
    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private MaterialMenuIconToolbar mMaterialMenu;
    //标识是否点击过一次back退出
    private boolean mIsExit = false;
    private ViewGroup mContent;
    private ViewGroup mDrawer;
    private ViewGroup mMainPage;
    private ViewGroup mAppSetting;
    private ImageView mHeaderImage;
    private PagerSlidingTabStrip mTabs;
    //新闻列表
    private List<NewsListFragment> mFragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initViewPager();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initViews() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mToolBar.setTitle(getResources().getString(R.string.main_activity_title));
        setActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
        mMaterialMenu = new MaterialMenuIconToolbar(this, Color.BLACK, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.toolBar;
            }
        };
        mMaterialMenu.setNeverDrawTouch(true);
        mTintManager.setStatusBarTintEnabled(false);
        //设置侧滑菜单的监听
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mMaterialMenu.setTransformationOffset(MaterialMenuDrawable.AnimationState.BURGER_X, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                mMaterialMenu.animatePressedState(intToState(1));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mMaterialMenu.animatePressedState(intToState(0));
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mContent = (ViewGroup) findViewById(R.id.content);
        mMainPage = (ViewGroup) findViewById(R.id.main_page);
        mDrawer = (ViewGroup) findViewById(R.id.drawer);
        //导航栏透明，需要让出顶部和底部空间
        if (isNavBarTransparent()) {
            mMainPage.setPadding(0, getStatusBarHeight(), 0, getNavigationHeight());
            mDrawer.setPadding(0, 0, 0, getNavigationHeight());
        }
        //侧边栏
        mHeaderImage = (ImageView) findViewById(R.id.header_img);
        mHeaderImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        mAppSetting = (ViewGroup) findViewById(R.id.bottom_drawer);
    }

    public void initViewPager() {
        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.pager);

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
