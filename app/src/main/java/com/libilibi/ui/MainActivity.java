package com.libilibi.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.libilibi.R;
import com.libilibi.base.RxAppCompatBaseActivity;
import com.libilibi.common.AppConfig;

import butterknife.BindView;

public class MainActivity extends RxAppCompatBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.main_container)
    FrameLayout mainContainer;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_navigation_drawer);
        setSupportActionBar(mToolbar);
        ActionBar actionB = getSupportActionBar();
        if (actionB != null) {
            actionB.setDisplayShowTitleEnabled(false);
        }
        mToggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.post(new Runnable() {
            @Override
            public void run() {
                mToggle.syncState();
            }
        });
        mDrawer.addDrawerListener(mToggle);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //初始化Fragment
        initFragments();
    }

    private void initFragments() {
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_action_game) {
            if (AppConfig.getAppThemeType() == AppConfig.APP_HTEME_PINK) {
                AppConfig.setAppThemeType(AppConfig.APP_HTEME_DARK);
                recreate();
            } else {
                AppConfig.setAppThemeType(AppConfig.APP_HTEME_PINK);
                recreate();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
