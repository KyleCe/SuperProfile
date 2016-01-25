package com.product.kyle.testforgradle;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.product.kyle.testforgradle.utils.JPushUtil;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

//import com.avast.android.dialogs.fragment.SimpleDialogFragment;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class MainActivity extends FragmentActivity
        implements View.OnClickListener {
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentList;
    //    private ImageView image;
    private TextView view1, view2, view3, view4;
    private int currIndex;//当前页卡编号
    private int bmpW;//横线图片宽度
    private int offset;//图片移动的偏移量

    ////标题栏
    private ImageButton imageButtonMenu;

    //图片、文字（底部标题栏），用于设置按下效果
    private ImageView messageImage;
    private ImageView contactsImage;
    private ImageView newsImage;
    private ImageView settingImage;
    private TextView messageText;
    private TextView contactsText;
    private TextView newsText;
    private TextView settingText;
    //layout
    private View messageLayout;
    private View contactsLayout;
    private View newsLayout;
    private View settingLayout;


    //处理Fragment界面的back
    private Fragment btFragment;
    private Fragment secondFragment;
    private Fragment thirdFragment;
    private Fragment fourthFragment;

    //onKeyDown parameter
    private Fragment fg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);//自定义自己的标题栏
//        getActionBar().setDisplayShowHomeEnabled(false);//此为隐藏标题栏图标
        setContentView(R.layout.activity_main);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);


        //code for note


        //各组件初始化
        InitViews();
        InitImage();
        InitViewPager();
//        VolumnControl();

        //airplane mode try
        boolean isEnabled = Settings.System.getInt(
                this.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 1;

        //// Jpush test
        testJpush();
    }

    /**
     * test Jpush
     */
    private void testJpush() {
        //        String udid =  JpushUtil.getImei(getApplicationContext(), "");
//        Toast.makeText(this,udid,Toast.LENGTH_SHORT).show();
//
//        String appKey = JpushUtil.getAppKey(getApplicationContext());
//        if (null == appKey) appKey = "AppKey异常";
//
//        String versionName =  JpushUtil.GetVersion(getApplicationContext());

//        registerMessageReceiver();


        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this.getApplication());            // 初始化 JPush


//        JPushUtil.setPushTag(this, ""
//                + getInstance().getUserId());

//        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);     		// 初始化 JPush
    }


    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void InitViews() {
        messageLayout = findViewById(R.id.message_layout);
        contactsLayout = findViewById(R.id.contacts_layout);
        newsLayout = findViewById(R.id.news_layout);
        settingLayout = findViewById(R.id.setting_layout);
        messageImage = (ImageView) findViewById(R.id.message_image);
        contactsImage = (ImageView) findViewById(R.id.contacts_image);
        newsImage = (ImageView) findViewById(R.id.news_image);
        settingImage = (ImageView) findViewById(R.id.setting_image);
        messageText = (TextView) findViewById(R.id.message_text);
        contactsText = (TextView) findViewById(R.id.contacts_text);
        newsText = (TextView) findViewById(R.id.news_text);
        settingText = (TextView) findViewById(R.id.setting_text);

        imageButtonMenu = (ImageButton) findViewById(R.id.imageButtonMenu);

//        SimpleDialogFragment.createBuilder(this, getSupportFragmentManager())
//                .setTitle(R.string.shareTitle)
//                .setMessage(R.string.shareMessage)
//                .setPositiveButtonText(R.string.str_ok)
//                .setNegativeButtonText(R.string.str_no).show();


        imageButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // share to others
                new AlertDialog.Builder(
                        new ContextThemeWrapper(MainActivity.this, android.R.style.Theme_Holo_Light))
                        .setTitle(R.string.shareTitle)
                        .setMessage(R.string.shareMessage)
                        .setNegativeButton(R.string.str_no,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialoginterface, int i) {
                                        //do something
                                    }
                                })
                        .setPositiveButton(R.string.str_ok,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // do something
                                    }
                                })
                        .show();
            }
        });

//        messageLayout.setOnClickListener(new txListener(0));
//        contactsLayout.setOnClickListener(new txListener(1));
//        newsLayout.setOnClickListener(new txListener(2));
//        settingLayout.setOnClickListener(new txListener(3));

        messageLayout.setOnClickListener(this);
        contactsLayout.setOnClickListener(this);
        newsLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);

        setTabSelection(0);

    }


//    messageLayout; contactsLayout;newsLayout;settingLayout;
//    public class txListener implements View.OnClickListener{
//        private int index=0;
//
//        public txListener(int i) {
//            index =i;
//        }
//        @Override
//        public void onClick(View v) {
//            // TODO Auto-generated method stub
//            mPager.setCurrentItem(index);
//            setTabSelection(index);
//        }
//    }


    /*
     * 初始化图片的位移像素
     */
    public void InitImage() {
        //comment
//        image = (ImageView)findViewById(R.id.message_image);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (screenW / 4 - bmpW) / 2;

        //imageview设置平移，使下划线平移到初始位置（平移一个offset）
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
//        image.setImageMatrix(matrix);
    }

    /*
     * 初始化ViewPager
     */
    public void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.viewpager);
        fragmentList = new ArrayList<Fragment>();
        Fragment btFragment = new ProfileFragment();
        Fragment secondFragment = new SecondFragment();
        Fragment thirdFragment = new ThirdFragment();
        Fragment fourthFragment = new FourthFragment();
        fragmentList.add(btFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);
        fragmentList.add(fourthFragment);

        //给ViewPager设置适配器
//        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        mPager.setCurrentItem(0);//设置当前显示标签页为第一页
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器

        //onKeyDown
//        mPager.setCurrentItem(arg0.getPosition());
//        fg = mAdapter.getItem(arg0.getPosition());

        //back press try
//        FragmentManager fragmentManager = new FragmentManager() {
//        };
//        FragmentTransaction tx = fragmentManager.beginTransation();
//        tx.replace( R.id.fragment, new FourthFragment() ).addToBackStack( "tag" ).commit();
//        //back press try
//        //You need to add the following line for this solution to work; thanks skayred
//        btFragment.getView().setFocusableInTouchMode(true);
//
//        btFragment.getView().setOnKeyListener( new View.OnKeyListener()
//        {
//            @Override
//            public boolean onKey( View v, int keyCode, KeyEvent event )
//            {
//                if( keyCode == KeyEvent.KEYCODE_BACK )
//                {
//                    return true;
//                }
//                return false;
//            }
//        } );

    }


    public class MyOnPageChangeListener implements OnPageChangeListener {
        private int one = offset * 2 + bmpW;//两个相邻页面的偏移量

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
//            Toast.makeText(MainActivity.this,"onPageScrolled",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            Animation animation = new TranslateAnimation(currIndex * one, arg0 * one, 0, 0);//平移动画
            currIndex = arg0;
            animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
            animation.setDuration(200);//动画持续时间0.2秒

            //设置按下效果
            setTabSelection(currIndex);
            // to comment cursor
//            image.startAnimation(animation);//是用ImageView来显示动画的

//            int i = currIndex + 1;
//            Toast.makeText(MainActivity.this, "您选择了第" + i + "个页卡", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
//        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                messageImage.setImageResource(R.drawable.message_selected);
                messageText.setTextColor(Color.WHITE);
//                if (messageFragment == null) {
//                    // 如果MessageFragment为空，则创建一个并添加到界面上
//                    messageFragment = new MessageFragment();
//                    transaction.add(R.id.content, messageFragment);
//                } else {
//                    // 如果MessageFragment不为空，则直接将它显示出来
//                    transaction.show(messageFragment);
//                }
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                contactsImage.setImageResource(R.drawable.contacts_selected);
                contactsText.setTextColor(Color.WHITE);
//                if (contactsFragment == null) {
//                    // 如果ContactsFragment为空，则创建一个并添加到界面上
//                    contactsFragment = new ContactsFragment();
//                    transaction.add(R.id.content, contactsFragment);
//                } else {
//                    // 如果ContactsFragment不为空，则直接将它显示出来
//                    transaction.show(contactsFragment);
//                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                newsImage.setImageResource(R.drawable.news_selected);
                newsText.setTextColor(Color.WHITE);
//                if (newsFragment == null) {
//                    // 如果NewsFragment为空，则创建一个并添加到界面上
//                    newsFragment = new NewsFragment();
//                    transaction.add(R.id.content, newsFragment);
//                } else {
//                    // 如果NewsFragment不为空，则直接将它显示出来
//                    transaction.show(newsFragment);
//                }
                break;
            case 3:
            default:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                settingImage.setImageResource(R.drawable.setting_selected);
                settingText.setTextColor(Color.WHITE);
//                if (settingFragment == null) {
//                    // 如果SettingFragment为空，则创建一个并添加到界面上
//                    settingFragment = new SettingFragment();
//                    transaction.add(R.id.content, settingFragment);
//                } else {
//                    // 如果SettingFragment不为空，则直接将它显示出来
//                    transaction.show(settingFragment);
//                }
                break;
        }
//        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        int index = 0;
        switch (v.getId()) {
            case R.id.message_layout:
                // 当点击了消息tab时，选中第1个tab
                index = 0;
//                setTabSelection(0);
//                Toast.makeText(this,"Tab 1 clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.contacts_layout:
                // 当点击了联系人tab时，选中第2个tab
                index = 1;
//                setTabSelection(1);
//                Toast.makeText(this,"Tab 2 clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.news_layout:
                // 当点击了动态tab时，选中第3个tab
                index = 2;
//                setTabSelection(2);
//                Toast.makeText(this,"Tab 3 clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_layout:
                // 当点击了设置tab时，选中第4个tab
                index = 3;
//                setTabSelection(3);
//                Toast.makeText(this,"Tab 4 clicked",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        mPager.setCurrentItem(index);
        setTabSelection(index);
    }


    //设置标签栏的清空显示样式
    public void clearSelection() {
        messageImage.setImageResource(R.drawable.message_unselected);
        messageText.setTextColor(getResources().getColor(R.color.text_color_on_tab));

        contactsImage.setImageResource(R.drawable.contacts_unselected);
        contactsText.setTextColor(getResources().getColor(R.color.text_color_on_tab));

        newsImage.setImageResource(R.drawable.news_unselected);
        newsText.setTextColor(getResources().getColor(R.color.text_color_on_tab));

        settingImage.setImageResource(R.drawable.setting_unselected);
        settingText.setTextColor(getResources().getColor(R.color.text_color_on_tab));

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        ToBackPressFragment fragment = (ToBackPressFragment)
//                getSupportFragmentManager().findFragmentByTag("TO_BACK_PRESS_FRAGMENT");
//        ToBackPressFragment fragment = (ToBackPressFragment)
//                getChildFragmentManager().findFragmentByTag("TO_BACK_PRESS_FRAGMENT");

//        String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();

//        ButtonFragment fragment = (ButtonFragment) getVisibleFragment();
////                getSupportFragmentManager().findFragmentByTag("tag");
////                getSupportFragmentManager().findFragmentById(R.layout.back_press_fragment);
////        getVisibleFragment();
//        fragment.onBackPressed();
////        if (fragment.isVisible()) {
////        if (getActiveFragment() != null) {
////            fragment.onBackPressed();
////        } else {
////            super.onBackPressed();
////        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Log.d("ActionBar", "OnKey事件");
//        if(fg instanceof ButtonFragment){
//            ButtonFragment.onKeyDown(keyCode, event);
//        }
//        return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            //do something here
            fg = getVisibleFragment();
            if (fg instanceof ProfileFragment) {
                ((ProfileFragment) fg).myOnKeyDown(keyCode);
            } else {
                return super.onKeyDown(keyCode, event);
            }
            return super.onKeyDown(keyCode, event);
        }

        return super.onKeyDown(keyCode, event);
    }


    //get visible fragment
    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }

    //get active fragment var BackStack
    public Fragment getActiveFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        return getSupportFragmentManager().findFragmentByTag(tag);
    }


    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.product.kyle.testforgradle.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!JPushUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
//                setCostomMsg(showMsg.toString());
            }
        }
    }

}