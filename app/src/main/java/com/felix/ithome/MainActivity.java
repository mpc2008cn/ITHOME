package com.felix.ithome;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final int COUNT = 8;//标签数量
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //信封小图标
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //左侧导航栏
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //多标签
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        /*由viewpager来设定
        tabLayout.addTab(tabLayout.newTab().setText("最新"));
        tabLayout.addTab(tabLayout.newTab().setText("排行榜"));
        tabLayout.addTab(tabLayout.newTab().setText("安卓"));
        tabLayout.addTab(tabLayout.newTab().setText("手机"));
        tabLayout.addTab(tabLayout.newTab().setText("数码"));
        */

        ViewPager viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyViewPagerAdapter(this));
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.test_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_info:
                switchToInfo();
                break;
            case R.id.nav_circle:
                switchToCircle();
                break;
            case R.id.nav_account:
                switchToAccount();
                break;
            case R.id.nav_settings:
                switchToSettings();
                break;
            case R.id.nav_about:
                switchToAbout();
                break;
            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchToInfo() {
        getSupportFragmentManager().beginTransaction().replace(R.id.appbar, Fragment);
        toolbar.setTitle(R.string.navigation_about);
    }

    private  class MyViewPagerAdapter extends PagerAdapter {
        private AppCompatActivity activity;

        public MyViewPagerAdapter(MainActivity activity) {
            this.activity=activity;
        }


        @Override
        public CharSequence getPageTitle(int pos){
            switch (pos){
                case 0:
                    return "最新";
                case 1:
                    return "排行榜";
                case 2:
                    return "安卓";
                case 3:
                    return "手机";
                case 4:
                    return "数码";
                case 5:
                    return "苹果";
                case 6:
                    return "WP";
                case 7:
                    return "Windows";
                default:
                    return "最新";
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container,int pos){
            TextView tv=new TextView(activity);
            tv.setText("@ViewPager:"+pos);
            tv.setTextSize(30.0f);
            tv.setGravity(Gravity.CENTER);
            container.addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container,int position,Object object){
            container.removeView((View)object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public int getCount(){
            return COUNT;
        }
    }
}
