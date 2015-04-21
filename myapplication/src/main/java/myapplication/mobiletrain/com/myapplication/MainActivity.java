package myapplication.mobiletrain.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private DrawerLayout drawLayout;
    private ArrayList<Fragment> allFragments;
    private ViewPager viewPagerFragment;
    private ListView list;
    private ViewPager navigation_fragment;
    private View navigation_selected;
    private RadioGroup radiogroup;
    private HorizontalScrollView navigationTab;
    private LinearLayout.LayoutParams layoutParams;
    private DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //ViewPager
        navigation_fragment = (ViewPager) findViewById(R.id.navigation_fragment);
        //滚动条
        navigation_selected = findViewById(R.id.navigation_selected);
        //RadioGroup
        radiogroup = (RadioGroup) findViewById(R.id.navigation_radiogroup);
        //HorizontionScroview
        navigationTab = (HorizontalScrollView) findViewById(R.id.navigation_tab);
        //侧滑菜单
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        allFragments = new ArrayList<Fragment>();
        Fragment f1 = ItemFragment1.newInstance("", "");
        Fragment f2 = ItemFragment2.newInstance("", "");
        Fragment f3 = ItemFragment3.newInstance("", "");
        Fragment f4 = ItemFragment4.newInstance("", "");
        allFragments.add(f1);
        allFragments.add(f2);
        allFragments.add(f3);
        allFragments.add(f4);

        viewPagerFragment = (ViewPager) findViewById(R.id.navigation_fragment);
        viewPagerFragment.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
        viewPagerFragment.setOnPageChangeListener(this);
        //滑块在布局中的属性
        layoutParams = (LinearLayout.LayoutParams) navigation_selected.getLayoutParams();
        //获得屏幕高度
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ((RadioButton) radiogroup.getChildAt(0)).setChecked(true);
        radiogroup.setOnCheckedChangeListener(this);

    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    //positionOffset 滑块距离百分比
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //滑块左右滑动
        layoutParams.setMargins((int) (layoutParams.width * (position + positionOffset)), 0, 0, 0);
        navigation_selected.setLayoutParams(layoutParams);
    }

    @Override
    public void onPageSelected(int position) {
        RadioButton button = (RadioButton) radiogroup.getChildAt(position);
        button.setChecked(true);
        //让radiobutton 滚动到屏幕中心
        int i = button.getLeft() - metrics.widthPixels / 2 + button.getWidth() / 2;
        navigationTab.smoothScrollTo(i, 0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.navigation_tab0:
                navigation_fragment.setCurrentItem(0);
                break;
            case R.id.navigation_tab1:
                navigation_fragment.setCurrentItem(1);
                break;
            case R.id.navigation_tab2:
                navigation_fragment.setCurrentItem(2);
                break;
            case R.id.navigation_tab3:
                navigation_fragment.setCurrentItem(3);
                break;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }
    }

    class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return allFragments.get(position);
        }

        @Override
        public int getCount() {
            return allFragments.size();
        }
    }

}
