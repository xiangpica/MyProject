package myapplication.mobiletrain.com.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import javax.xml.transform.Result;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment implements ActionBar.TabListener, View.OnClickListener {

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;
    private View inflate;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }

        // Select either the default item (0) or the last selected item.
        selectItem(mCurrentSelectedPosition);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        mDrawerListView = (ListView) inflater.inflate(
//                R.layout.fragment_navigation_drawer, container, false);

        inflate = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        this.inflate.findViewById(R.id.linear2).setOnClickListener(this);

        this.inflate.findViewById(R.id.linear4).setOnClickListener(this);

        this.inflate.findViewById(R.id.linear5).setOnClickListener(this);

        this.inflate.findViewById(R.id.linear6).setOnClickListener(this);

        this.inflate.findViewById(R.id.linear7).setOnClickListener(this);
        this.inflate.findViewById(R.id.linear8).setOnClickListener(this);

//        inflate.setOnClickListener(new View.OnClickListener() {
        onClick(this.inflate);
        return this.inflate;
    }
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.linear2:
                        Intent i1 = new Intent();
                        i1.setClass(getActivity(),MainActivity2.class);
                        startActivity(i1);
                        Toast.makeText(getActivity(), "我的收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.linear4:
                        Intent i2 = new Intent(getActivity(),MainActivity3.class);
                        getActivity().startActivity(i2);
                        Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.linear5:
                        Intent i3 = new Intent(getActivity(),MainActivity4.class);
                        getActivity().startActivity(i3);
                        Toast.makeText(getActivity(), "意见反馈", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.linear6:
                        Intent i4 = new Intent(getActivity(),MainActivity5.class);
                        getActivity().startActivity(i4);
                        Toast.makeText(getActivity(), "检查新版本", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.linear7:
                        Intent i5 = new Intent(getActivity(),MainActivity6.class);
                        getActivity().startActivity(i5);
                        Toast.makeText(getActivity(), "关于我们", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.linear8:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("提示")
                                .setMessage("是否确定退出")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //点击取消关闭对话框
                                        dialog.cancel();

                                    }
                                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //点击确定退出app
                                getActivity().finish();
                            }
                        }).setCancelable(false).show();
                        break;
                }
            }
//        });
//        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                selectItem(position);
//                switch (position){
//                    case 0:
//                        Intent i1 = new Intent();
//                        i1.setClass(getActivity(),MainActivity2.class);
//                        startActivity(i1);
//                        Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1:
//                        Intent i2 = new Intent(getActivity(),MainActivity3.class);
//                        getActivity().startActivity(i2);
//                        Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
//                        Intent i3 = new Intent(getActivity(),MainActivity4.class);
//                        getActivity().startActivity(i3);
//                        Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 3:
//                        Intent i4 = new Intent(getActivity(),MainActivity5.class);
//                        getActivity().startActivity(i4);
//                        Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 4:
//                        Intent i5 = new Intent(getActivity(),MainActivity6.class);
//                        getActivity().startActivity(i5);
//                        Toast.makeText(getActivity(), "5", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 5:
//                        Intent i6 = new Intent(getActivity(),MainActivity7.class);
//                        getActivity().startActivity(i6);
//                        Toast.makeText(getActivity(), "6", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 6:
//                     AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                    builder.setTitle("提示")
//                            .setMessage("是否确定退出")
//                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    //点击取消关闭对话框
//                                    dialog.cancel();
//
//                                }
//                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //点击确定退出app
//                            getActivity().finish();
//                        }
//                    }).setCancelable(false).show();
//                    break;
//                }
//            }
//        });
//        mDrawerListView.setAdapter(new ArrayAdapter<String>(
//                getActionBar().getThemedContext(),
//                android.R.layout.simple_list_item_2,
//                android.R.id.text1,
//                new String[]{
//                        getString(R.string.title_section1),
//                        getString(R.string.title_section2),
//                        getString(R.string.title_section3),
//                        getString(R.string.title_section4),
//                        getString(R.string.title_section5),
//                        getString(R.string.title_section6),
//                        getString(R.string.title_section7),
//                }));

//        mDrawerListView.setAdapter(new ArrayAdapter<Integer>(
//                getActionBar().getThemedContext(),
//                R.layout.layout_menu,
//                R.id.imageView,
//                new int[]{
//                        R.drawable.drawer_ic_collections,
//                        R.drawable.drawer_ic_settings
//                        ,R.drawable.drawer_ic_feedback
//                        ,R.drawable.drawer_ic_update
//                        ,R.drawable.drawer_ic_about
//                }));
//        View v = inflater.inflate(R.layout.layout_menu, container, false);
//        v.findViewById(R.id.imageView).setOnClickListener((View.OnClickListener) this);

//        inflate.setItemChecked(mCurrentSelectedPosition, true);
//        DrawerLayout drawerLayout=(DrawerLayout) getActivity().findViewById(R.id.navigation_drawer);
//        drawerLayout.closeDrawers();
//        return inflate;
//    }



    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        Resources r = getResources();
        Drawable drawable = r.getDrawable(R.color.checked_color);
        actionBar.setBackgroundDrawable(drawable);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText(R.string.actiontext).setTabListener(this));


        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        //开关按钮
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).commit();
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                //同步开关状态
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }



    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        if (mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.global, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
        switch (item.getItemId()){
            case R.id.action_appstore:

                Intent i1 = new Intent(getActivity(), APPStoreActivity.class);
                getActivity().startActivity(i1);
                Toast.makeText(getActivity(),"appstore",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_history:
                Intent i2 = new Intent(getActivity(), HistoryActivity.class);
                getActivity().startActivity(i2);
                Toast.makeText(getActivity(),"history",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Intent i3 = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(i3);
                Toast.makeText(getActivity(),"search",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_download:
                Intent i4 = new Intent(getActivity(), DownLoadActivity.class);
                getActivity().startActivity(i4);
                Toast.makeText(getActivity(),"download",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return mDrawerToggle.onOptionsItemSelected(item);
        }

//        if (item.getItemId() == R.id.action_example) {
//            Toast.makeText(getActivity(), "点击我吧", Toast.LENGTH_SHORT).show();
//            return true;
//        }

//        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }
}