package com.product.kyle.testforgradle;


//import android.os.Bundle;
//import android.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.product.kyle.testforgradle.utils.SPUtils;
//import android.widget.ToggleButton.OnCheckedChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileDetailFragment extends Fragment {

    //volume max
    protected int vMax;
    protected int brightnessMax = 255;

    final static int RINGER_MODE_NORMAL = 1;
    final static int RINGER_MODE_SILENT = 2;
    final static int RINGER_MODE_VIBRATE = 3;
    //repeat mode
    final static int REPEAT_MODE_EVERYDAY = 1;
    final static int REPEAT_MODE_WORKDAY = 2;
    final static int REPEAT_MODE_WEEKEND = 3;


    int DEFAULT_VOLUME = 50;//%计算
    int DEFAULT_BRIGHTNESS = (int) ((30 / 100.0) * 255);
    boolean DEFAULT_BRIGHTNESS_AUTO = true;
    boolean DEFAULT_WIFI = true;
    boolean DEFAULT_BLUETOOTH = false;
    int DEFAULT_RINGER_MODE = RINGER_MODE_NORMAL;
    int DEFAULT_HOUR = 18;
    int DEFAULT_MINUTE = 0;
    int DEFAULT_HOUR_STOP = 23;
    int DEFAULT_MINUTE_STOP = 0;


    String KEY_DEFAULT_VOLUME = "KEY_DEFAULT_VOLUME";
    String KEY_USER_VOLUME = "KEY_USER_VOLUME";
    String KEY_DEFAULT_RING_MODE = "KEY_DEFAULT_RING_MODE";
    String KEY_USER_RING_MODE = "KEY_USER_RING_MODE";
    String KEY_USER_MUTE = "KEY_USER_MUTE";
    String KEY_USER_VIBRATE = "KEY_USER_VIBRATE";
    String KEY_DEFAULT_WIFI = "KEY_DEFAULT_WIFI";
    String KEY_USER_WIFI = "KEY_USER_WIFI";
    String KEY_DEFAULT_GPRS = "KEY_DEFAULT_GPRS";
    String KEY_USER_GPRS = "KEY_USER_GPRS";
    String KEY_DEFAULT_BLUETOOTH = "KEY_DEFAULT_BLUETOOTH";
    String KEY_USER_BLUETOOTH = "KEY_USER_BLUETOOTH";
    String KEY_DEFAULT_BRIGHTNESS = "KEY_DEFAULT_BRIGHTNESS";
    String KEY_USER_BRIGHTNESS = "KEY_USER_BRIGHTNESS";
    String KEY_DEFAULT_BRIGHTNESS_AUTO = "KEY_DEFAULT_BRIGHTNESS_AUTO";
    String KEY_USER_BRIGHTNESS_AUTO = "KEY_USER_BRIGHTNESS_AUTO";
    String KEY_DEFAULT_HOUR = "KEY_DEFAULT_HOUR";
    String KEY_DEFAULT_MINUTE = "KEY_DEFAULT_MINUTE";
    String KEY_DEFAULT_HOUR_STOP = "KEY_DEFAULT_HOUR_STOP";
    String KEY_DEFAULT_MINUTE_STOP = "KEY_DEFAULT_MINUTE_STOP";
    String KEY_USER_HOUR = "KEY_USER_HOUR";
    String KEY_USER_MINUTE = "KEY_USER_MINUTE";
    String KEY_USER_HOUR_STOP = "KEY_USER_HOUR_STOP";
    String KEY_USER_MINUTE_STOP = "KEY_USER_MINUTE_STOP";
    String KEY_USER_TRIGGER_PERIOD_ON = "KEY_USER_TRIGGER_PERIOD_ON";
    String KEY_DEFAULT_REPEAT_MODE = "KEY_DEFAULT_REPEAT_MODE";
    String KEY_USER_REPEAT_MODE = "KEY_USER_REPEAT_MODE";


    protected int default_volume = 0;
    protected int user_set_volume = 0;//setVolume
    protected int default_ringer_mode = 0;
    protected int user_ringer_mode = 0;
    protected boolean default_wifi = DEFAULT_WIFI;
    protected boolean user_wifi = false;
    protected boolean default_gprs = false;
    protected boolean user_gprs = false;
    protected boolean default_bluetooth = DEFAULT_BLUETOOTH;
    protected boolean user_bluetooth = false;
    protected boolean user_mute = false;
    protected int default_brightness = DEFAULT_BRIGHTNESS;
    protected boolean default_brightness_auto = true;//
    protected boolean default_trigger_period = false;//
    protected int user_brightness = default_brightness;
    protected boolean user_brightness_auto = false;
    protected boolean user_trigger_period_on = false;

    protected int default_hour = 0;
    protected int default_minute = 0;
    protected int default_hour_stop = 0;
    protected int default_minute_stop = 0;
    protected int user_start_hour = 0;
    protected int user_start_minute = 0;
    protected int user_stop_hour = 0;
    protected int user_stop_minute = 0;

    protected int default_repeat_mode = REPEAT_MODE_EVERYDAY;
    protected int user_repeat_mode = default_repeat_mode;

    final static int NOT_SET = -1;

    protected int getCurrentVolume = 0;

    private SeekBar volumeSeekbar = null;
    private SeekBar brightnessSeekbar = null;
    private ToggleButton brightness_toggle = null;
    private AudioManager audioManager = null;

    private Button save_change_button = null;
    private Button reset_setting_button = null;
    private Button wallpaper_picker = null;
    private Button start_time = null;
    private Button stop_time = null;

    private ToggleButton trigger_period_toggle = null;

    private ToggleButton mute_toggle_button = null;
    private ToggleButton ring_mode_normal = null;
    private ToggleButton ring_mode_silent = null;
    private ToggleButton ring_mode_vibrate = null;
    private ToggleButton Wifi_toggle = null;
    private ToggleButton bluetooth_toggle = null;
    private ToggleButton GPRS_toggle = null;
    private ToggleButton repeat_mode_everyday_toggle = null;
    private ToggleButton repeat_mode_workday_toggle = null;
    private ToggleButton repeat_mode_weekend_toggle = null;


    //SP definition
    SharedPreferences preferences;

    //Vibrator
    Vibrator vibrator;
    //dialog
    AlertDialog.Builder builder;

    public ProfileDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //layout
        View rootView = inflater.inflate(R.layout.fragment_profile_detail, container, false);//关联布局文件
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        getControlItem();//get control view item by findViewById

        InitialParamByTabId(preferences.getInt("ProfileTabId", NOT_SET));

        setDefaultParams();

        setControlItemState();

        setOnViewItemListeners();

//        ITelephony phone = ITelephony.Stub.asInterface(ServiceManager.getService("phone"));

//        页面上现有ProgressBar控件progressBar，请用书写线程以10秒的的时间完成其进度
//        显示工作。（9分）
//        final ProgressBar progressBar = null;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int progressBarMax = progressBar.getMax();
//                try{
//                    while(progressBarMax!=progressBar.getProgress())
//                    {
//                        int stepProgress = progressBarMax/10;
//                        int currentProgress =progressBar.getProgress();
//                        progressBar.setProgress(currentProgress+stepProgress);
//                        Thread.sleep(1000);
//                    }
//
//                }catch (InterruptedException e) {
//                    //TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();

    }

    private void InitialParamByTabId(int Id) {
        switch (Id) {
            case 1:
                KEY_DEFAULT_VOLUME = "TAB1_KEY_DEFAULT_VOLUME";
                KEY_USER_VOLUME = "TAB1_KEY_USER_VOLUME";
                KEY_DEFAULT_RING_MODE = "TAB1_KEY_DEFAULT_RING_MODE";
                KEY_USER_RING_MODE = "TAB1_KEY_USER_RING_MODE";
                KEY_USER_MUTE = "TAB1_KEY_USER_MUTE";
                KEY_USER_VIBRATE = "TAB1_KEY_USER_VIBRATE";
                KEY_DEFAULT_WIFI = "TAB1_KEY_DEFAULT_WIFI";
                KEY_USER_WIFI = "TAB1_KEY_USER_WIFI";
                KEY_DEFAULT_GPRS = "TAB1_KEY_DEFAULT_GPRS";
                KEY_USER_GPRS = "TAB1_KEY_USER_GPRS";
                KEY_DEFAULT_BLUETOOTH = "TAB1_KEY_DEFAULT_BLUETOOTH";
                KEY_USER_BLUETOOTH = "TAB1_KEY_USER_BLUETOOTH";
                KEY_DEFAULT_BRIGHTNESS = "TAB1_KEY_DEFAULT_BRIGHTNESS";
                KEY_USER_BRIGHTNESS = "TAB1_KEY_USER_BRIGHTNESS";
                KEY_DEFAULT_BRIGHTNESS_AUTO = "TAB1_KEY_DEFAULT_BRIGHTNESS_AUTO";
                KEY_USER_BRIGHTNESS_AUTO = "TAB1_KEY_USER_BRIGHTNESS_AUTO";
                KEY_DEFAULT_HOUR = "TAB1_KEY_DEFAULT_HOUR";
                KEY_DEFAULT_MINUTE = "TAB1_KEY_DEFAULT_MINUTE";
                KEY_DEFAULT_HOUR_STOP = "TAB1_KEY_DEFAULT_HOUR_STOP";
                KEY_DEFAULT_MINUTE_STOP = "TAB1_KEY_DEFAULT_MINUTE_STOP";
                KEY_USER_HOUR = "TAB1_KEY_USER_HOUR";
                KEY_USER_MINUTE = "TAB1_KEY_USER_MINUTE";
                KEY_USER_HOUR_STOP = "TAB1_KEY_USER_HOUR_STOP";
                KEY_USER_MINUTE_STOP = "TAB1_KEY_USER_MINUTE_STOP";
                KEY_USER_TRIGGER_PERIOD_ON = "TAB1_KEY_USER_TRIGGER_PERIOD_ON";
                KEY_DEFAULT_REPEAT_MODE = "TAB1_KEY_DEFAULT_REPEAT_MODE";
                KEY_USER_REPEAT_MODE = "TAB1_KEY_USER_REPEAT_MODE";

                DEFAULT_VOLUME = 50;//%计算
                DEFAULT_BRIGHTNESS = (int) ((30 / 100.0) * 255);
                DEFAULT_BRIGHTNESS_AUTO = true;
                DEFAULT_WIFI = true;
                DEFAULT_BLUETOOTH = false;
                DEFAULT_RINGER_MODE = RINGER_MODE_NORMAL;
                DEFAULT_HOUR = 18;
                DEFAULT_MINUTE = 0;
                DEFAULT_HOUR_STOP = 23;
                DEFAULT_MINUTE_STOP = 0;
                break;
            case 2:
                KEY_DEFAULT_VOLUME = "TAB2_KEY_DEFAULT_VOLUME";
                KEY_USER_VOLUME = "TAB2_KEY_USER_VOLUME";
                KEY_DEFAULT_RING_MODE = "TAB2_KEY_DEFAULT_RING_MODE";
                KEY_USER_RING_MODE = "TAB2_KEY_USER_RING_MODE";
                KEY_USER_MUTE = "TAB2_KEY_USER_MUTE";
                KEY_USER_VIBRATE = "TAB2_KEY_USER_VIBRATE";
                KEY_DEFAULT_WIFI = "TAB2_KEY_DEFAULT_WIFI";
                KEY_USER_WIFI = "TAB2_KEY_USER_WIFI";
                KEY_DEFAULT_GPRS = "TAB2_KEY_DEFAULT_GPRS";
                KEY_USER_GPRS = "TAB2_KEY_USER_GPRS";
                KEY_DEFAULT_BLUETOOTH = "TAB2_KEY_DEFAULT_BLUETOOTH";
                KEY_USER_BLUETOOTH = "TAB2_KEY_USER_BLUETOOTH";
                KEY_DEFAULT_BRIGHTNESS = "TAB2_KEY_DEFAULT_BRIGHTNESS";
                KEY_USER_BRIGHTNESS = "TAB2_KEY_USER_BRIGHTNESS";
                KEY_DEFAULT_BRIGHTNESS_AUTO = "TAB2_KEY_DEFAULT_BRIGHTNESS_AUTO";
                KEY_USER_BRIGHTNESS_AUTO = "TAB2_KEY_USER_BRIGHTNESS_AUTO";
                KEY_DEFAULT_HOUR = "TAB2_KEY_DEFAULT_HOUR";
                KEY_DEFAULT_MINUTE = "TAB2_KEY_DEFAULT_MINUTE";
                KEY_DEFAULT_HOUR_STOP = "TAB2_KEY_DEFAULT_HOUR_STOP";
                KEY_DEFAULT_MINUTE_STOP = "TAB2_KEY_DEFAULT_MINUTE_STOP";
                KEY_USER_HOUR = "TAB2_KEY_USER_HOUR";
                KEY_USER_MINUTE = "TAB2_KEY_USER_MINUTE";
                KEY_USER_HOUR_STOP = "TAB2_KEY_USER_HOUR_STOP";
                KEY_USER_MINUTE_STOP = "TAB2_KEY_USER_MINUTE_STOP";
                KEY_USER_TRIGGER_PERIOD_ON = "TAB2_KEY_USER_TRIGGER_PERIOD_ON";
                KEY_DEFAULT_REPEAT_MODE = "TAB2_KEY_DEFAULT_REPEAT_MODE";
                KEY_USER_REPEAT_MODE = "TAB2_KEY_USER_REPEAT_MODE";

                DEFAULT_VOLUME = 255;//%计算
                DEFAULT_BRIGHTNESS = (int) ((30 / 100.0) * 255);
                DEFAULT_BRIGHTNESS_AUTO = false;
                DEFAULT_WIFI = false;
                DEFAULT_BLUETOOTH = false;
                DEFAULT_RINGER_MODE = RINGER_MODE_SILENT;
                DEFAULT_HOUR = 6;
                DEFAULT_MINUTE = 0;
                DEFAULT_HOUR_STOP = 9;
                DEFAULT_MINUTE_STOP = 0;
                break;

            case 3:
                KEY_DEFAULT_VOLUME = "TAB3_KEY_DEFAULT_VOLUME";
                KEY_USER_VOLUME = "TAB3_KEY_USER_VOLUME";
                KEY_DEFAULT_RING_MODE = "TAB3_KEY_DEFAULT_RING_MODE";
                KEY_USER_RING_MODE = "TAB3_KEY_USER_RING_MODE";
                KEY_USER_MUTE = "TAB3_KEY_USER_MUTE";
                KEY_USER_VIBRATE = "TAB3_KEY_USER_VIBRATE";
                KEY_DEFAULT_WIFI = "TAB3_KEY_DEFAULT_WIFI";
                KEY_USER_WIFI = "TAB3_KEY_USER_WIFI";
                KEY_DEFAULT_GPRS = "TAB3_KEY_DEFAULT_GPRS";
                KEY_USER_GPRS = "TAB3_KEY_USER_GPRS";
                KEY_DEFAULT_BLUETOOTH = "TAB3_KEY_DEFAULT_BLUETOOTH";
                KEY_USER_BLUETOOTH = "TAB3_KEY_USER_BLUETOOTH";
                KEY_DEFAULT_BRIGHTNESS = "TAB3_KEY_DEFAULT_BRIGHTNESS";
                KEY_USER_BRIGHTNESS = "TAB3_KEY_USER_BRIGHTNESS";
                KEY_DEFAULT_BRIGHTNESS_AUTO = "TAB3_KEY_DEFAULT_BRIGHTNESS_AUTO";
                KEY_USER_BRIGHTNESS_AUTO = "TAB3_KEY_USER_BRIGHTNESS_AUTO";
                KEY_DEFAULT_HOUR = "TAB3_KEY_DEFAULT_HOUR";
                KEY_DEFAULT_MINUTE = "TAB3_KEY_DEFAULT_MINUTE";
                KEY_DEFAULT_HOUR_STOP = "TAB3_KEY_DEFAULT_HOUR_STOP";
                KEY_DEFAULT_MINUTE_STOP = "TAB3_KEY_DEFAULT_MINUTE_STOP";
                KEY_USER_HOUR = "TAB3_KEY_USER_HOUR";
                KEY_USER_MINUTE = "TAB3_KEY_USER_MINUTE";
                KEY_USER_HOUR_STOP = "TAB3_KEY_USER_HOUR_STOP";
                KEY_USER_MINUTE_STOP = "TAB3_KEY_USER_MINUTE_STOP";
                KEY_USER_TRIGGER_PERIOD_ON = "TAB3_KEY_USER_TRIGGER_PERIOD_ON";
                KEY_DEFAULT_REPEAT_MODE = "TAB3_KEY_DEFAULT_REPEAT_MODE";
                KEY_USER_REPEAT_MODE = "TAB3_KEY_USER_REPEAT_MODE";

                DEFAULT_VOLUME = 255;//%计算
                DEFAULT_BRIGHTNESS = (int) ((30 / 100.0) * 255);
                DEFAULT_BRIGHTNESS_AUTO = false;
                DEFAULT_WIFI = false;
                DEFAULT_BLUETOOTH = false;
                DEFAULT_RINGER_MODE = RINGER_MODE_SILENT;
                DEFAULT_HOUR = 6;
                DEFAULT_MINUTE = 0;
                DEFAULT_HOUR_STOP = 9;
                DEFAULT_MINUTE_STOP = 0;
                break;

            case 4:
                KEY_DEFAULT_VOLUME = "TAB4_KEY_DEFAULT_VOLUME";
                KEY_USER_VOLUME = "TAB4_KEY_USER_VOLUME";
                KEY_DEFAULT_RING_MODE = "TAB4_KEY_DEFAULT_RING_MODE";
                KEY_USER_RING_MODE = "TAB4_KEY_USER_RING_MODE";
                KEY_USER_MUTE = "TAB4_KEY_USER_MUTE";
                KEY_USER_VIBRATE = "TAB4_KEY_USER_VIBRATE";
                KEY_DEFAULT_WIFI = "TAB4_KEY_DEFAULT_WIFI";
                KEY_USER_WIFI = "TAB4_KEY_USER_WIFI";
                KEY_DEFAULT_GPRS = "TAB4_KEY_DEFAULT_GPRS";
                KEY_USER_GPRS = "TAB4_KEY_USER_GPRS";
                KEY_DEFAULT_BLUETOOTH = "TAB4_KEY_DEFAULT_BLUETOOTH";
                KEY_USER_BLUETOOTH = "TAB4_KEY_USER_BLUETOOTH";
                KEY_DEFAULT_BRIGHTNESS = "TAB4_KEY_DEFAULT_BRIGHTNESS";
                KEY_USER_BRIGHTNESS = "TAB4_KEY_USER_BRIGHTNESS";
                KEY_DEFAULT_BRIGHTNESS_AUTO = "TAB4_KEY_DEFAULT_BRIGHTNESS_AUTO";
                KEY_USER_BRIGHTNESS_AUTO = "TAB4_KEY_USER_BRIGHTNESS_AUTO";
                KEY_DEFAULT_HOUR = "TAB4_KEY_DEFAULT_HOUR";
                KEY_DEFAULT_MINUTE = "TAB4_KEY_DEFAULT_MINUTE";
                KEY_DEFAULT_HOUR_STOP = "TAB4_KEY_DEFAULT_HOUR_STOP";
                KEY_DEFAULT_MINUTE_STOP = "TAB4_KEY_DEFAULT_MINUTE_STOP";
                KEY_USER_HOUR = "TAB4_KEY_USER_HOUR";
                KEY_USER_MINUTE = "TAB4_KEY_USER_MINUTE";
                KEY_USER_HOUR_STOP = "TAB4_KEY_USER_HOUR_STOP";
                KEY_USER_MINUTE_STOP = "TAB4_KEY_USER_MINUTE_STOP";
                KEY_USER_TRIGGER_PERIOD_ON = "TAB4_KEY_USER_TRIGGER_PERIOD_ON";
                KEY_DEFAULT_REPEAT_MODE = "TAB4_KEY_DEFAULT_REPEAT_MODE";
                KEY_USER_REPEAT_MODE = "TAB4_KEY_USER_REPEAT_MODE";

                DEFAULT_VOLUME = 255;//%计算
                DEFAULT_BRIGHTNESS = (int) ((30 / 100.0) * 255);
                DEFAULT_BRIGHTNESS_AUTO = false;
                DEFAULT_WIFI = false;
                DEFAULT_BLUETOOTH = false;
                DEFAULT_RINGER_MODE = RINGER_MODE_SILENT;
                DEFAULT_HOUR = 6;
                DEFAULT_MINUTE = 0;
                DEFAULT_HOUR_STOP = 9;
                DEFAULT_MINUTE_STOP = 0;
                break;
            default:
                //not set
                break;
        }
    }


    private void getControlItem() {
        save_change_button = (Button) getActivity().findViewById(R.id.save_change_button);
        reset_setting_button = (Button) getActivity().findViewById(R.id.reset_setting_button);
        wallpaper_picker = (Button) getActivity().findViewById(R.id.wallpaper_picker);
        bluetooth_toggle = (ToggleButton) getActivity().findViewById(R.id.bluetooth_toggle);
        start_time = (Button) getActivity().findViewById(R.id.start_time);
        stop_time = (Button) getActivity().findViewById(R.id.stop_time);

        mute_toggle_button = (ToggleButton) getActivity().findViewById(R.id.mute_toggle_button);
        ring_mode_normal = (ToggleButton) getActivity().findViewById(R.id.ring_mode_normal);
        ring_mode_silent = (ToggleButton) getActivity().findViewById(R.id.ring_mode_silent);
        ring_mode_vibrate = (ToggleButton) getActivity().findViewById(R.id.ring_mode_vibrate);
        Wifi_toggle = (ToggleButton) getActivity().findViewById(R.id.Wifi_toggle);
        GPRS_toggle = (ToggleButton) getActivity().findViewById(R.id.GPRS_toggle);

        volumeSeekbar = (SeekBar) getActivity().findViewById(R.id.volume);
        brightnessSeekbar = (SeekBar) getActivity().findViewById(R.id.brightness);
        brightness_toggle = (ToggleButton) getActivity().findViewById(R.id.brightness_toggle);
        trigger_period_toggle = (ToggleButton) getActivity().findViewById(R.id.trigger_period_toggle);

        //repeat mode
        repeat_mode_everyday_toggle = (ToggleButton) getActivity().findViewById(R.id.repeat_mode_everyday_toggle);
        repeat_mode_workday_toggle = (ToggleButton) getActivity().findViewById(R.id.repeat_mode_workday_toggle);
        repeat_mode_weekend_toggle = (ToggleButton) getActivity().findViewById(R.id.repeat_mode_weekend_toggle);

        //Manager and service
        preferences = getActivity().getSharedPreferences("SuperProfileSP", Context.MODE_PRIVATE);

        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        vMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);

    }

    private void setDefaultParams() {
        default_volume = (int) ((DEFAULT_VOLUME / 100.0) * vMax);
        default_ringer_mode = DEFAULT_RINGER_MODE;
        default_wifi = DEFAULT_WIFI;//default turn on wifi
        default_gprs = true;
        default_bluetooth = DEFAULT_BLUETOOTH;
        default_brightness = DEFAULT_BRIGHTNESS;
        default_brightness_auto = DEFAULT_BRIGHTNESS_AUTO;
        default_hour = DEFAULT_HOUR;
        default_minute = DEFAULT_MINUTE;
        default_hour_stop = DEFAULT_HOUR_STOP;
        default_minute_stop = DEFAULT_MINUTE_STOP;

//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putInt("FIRST_LAUNCH_FLAG", 1);
//        editor.commit();
        if (1 == preferences.getInt("FIRST_LAUNCH_FLAG", NOT_SET)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("FIRST_LAUNCH_FLAG", 2);

            //put default
            editor.putInt(KEY_DEFAULT_VOLUME, default_volume);
            editor.putInt(KEY_DEFAULT_RING_MODE, default_ringer_mode);//ring mode
            editor.putBoolean(KEY_DEFAULT_WIFI, default_wifi);
            editor.putBoolean(KEY_DEFAULT_GPRS, default_gprs);
            editor.putBoolean(KEY_DEFAULT_BLUETOOTH, default_bluetooth);
            editor.putInt(KEY_DEFAULT_BRIGHTNESS, default_brightness);
            editor.putBoolean(KEY_DEFAULT_BRIGHTNESS_AUTO, default_brightness_auto);
            editor.putBoolean(KEY_USER_MUTE, default_volume == 0);
            editor.putInt(KEY_DEFAULT_HOUR, default_hour);
            editor.putInt(KEY_DEFAULT_MINUTE, default_minute);
            editor.putInt(KEY_DEFAULT_HOUR_STOP, default_hour_stop);
            editor.putInt(KEY_DEFAULT_MINUTE_STOP, default_minute_stop);
            editor.putInt(KEY_DEFAULT_REPEAT_MODE, default_repeat_mode);

            //remove user set
            editor.remove(KEY_USER_VOLUME);
            editor.remove(KEY_USER_RING_MODE);
            editor.remove(KEY_USER_WIFI);
            editor.remove(KEY_USER_GPRS);
            editor.remove(KEY_USER_BLUETOOTH);
            editor.remove(KEY_USER_BRIGHTNESS);
            editor.remove(KEY_USER_BRIGHTNESS_AUTO);
            editor.remove(KEY_USER_MUTE);
            editor.remove(KEY_USER_HOUR);
            editor.remove(KEY_USER_MINUTE);
            editor.remove(KEY_USER_HOUR_STOP);
            editor.remove(KEY_USER_MINUTE_STOP);
            editor.remove(KEY_USER_REPEAT_MODE);
            editor.commit();
        }
    }

    private void setControlItemState() {

        bluetooth_toggle.setChecked(preferences.getBoolean(KEY_USER_BLUETOOTH, default_bluetooth));

        Wifi_toggle.setChecked(preferences.getBoolean(KEY_USER_WIFI, default_wifi));

        volumeSeekbar.setMax(vMax);
        getCurrentVolume = preferences.getInt(KEY_USER_VOLUME, default_volume);
        volumeSeekbar.setProgress(getCurrentVolume);

        mute_toggle_button.setChecked(preferences.getBoolean(KEY_USER_MUTE, false));

        GPRS_toggle.setChecked(preferences.getBoolean(KEY_USER_GPRS, false));

        brightnessSeekbar.setMax(brightnessMax);
        brightnessSeekbar.setProgress(preferences.getInt(KEY_USER_BRIGHTNESS, default_brightness));
        brightness_toggle.setChecked(preferences.getBoolean(KEY_USER_BRIGHTNESS_AUTO, default_brightness_auto));

        //time picker state set
        user_start_hour = preferences.getInt(KEY_USER_HOUR, default_hour);
        user_start_minute = preferences.getInt(KEY_USER_MINUTE, default_minute);
        user_stop_hour = preferences.getInt(KEY_USER_HOUR_STOP, default_hour_stop);
        user_stop_minute = preferences.getInt(KEY_USER_MINUTE_STOP, default_minute_stop);
        //Button text set
        start_time.setText(StandardTimeFormat(user_start_hour, user_start_minute));
        stop_time.setText(StandardTimeFormat(user_stop_hour, user_stop_minute));
        trigger_period_toggle.setChecked(preferences.getBoolean(KEY_USER_TRIGGER_PERIOD_ON, default_trigger_period));

        //initial ring mode state
        switch (preferences.getInt(KEY_USER_RING_MODE, default_ringer_mode)) {
            case RINGER_MODE_NORMAL:
                ring_mode_normal.setChecked(true);
                ring_mode_silent.setChecked(false);
                ring_mode_vibrate.setChecked(false);
                break;
            case RINGER_MODE_SILENT:
                ring_mode_normal.setChecked(false);
                ring_mode_silent.setChecked(true);
                ring_mode_vibrate.setChecked(false);
                break;
            case RINGER_MODE_VIBRATE:
                ring_mode_normal.setChecked(false);
                ring_mode_silent.setChecked(false);
                ring_mode_vibrate.setChecked(true);
                break;
            default:
                break;
        }

        //initial repeat mode state
        switch (preferences.getInt(KEY_USER_REPEAT_MODE, default_repeat_mode)) {
            case REPEAT_MODE_EVERYDAY:
                repeat_mode_everyday_toggle.setChecked(true);
                repeat_mode_workday_toggle.setChecked(false);
                repeat_mode_weekend_toggle.setChecked(false);
                break;
            case REPEAT_MODE_WORKDAY:
                repeat_mode_everyday_toggle.setChecked(false);
                repeat_mode_workday_toggle.setChecked(true);
                repeat_mode_weekend_toggle.setChecked(false);
                break;
            case REPEAT_MODE_WEEKEND:
                repeat_mode_everyday_toggle.setChecked(false);
                repeat_mode_workday_toggle.setChecked(false);
                repeat_mode_weekend_toggle.setChecked(true);
                break;
        }

    }

    private String StandardTimeFormat(int hour, int minute) {
        return (hour >= 10 ? String.valueOf(hour) : "0" + String.valueOf(hour))
                + ":" + (minute >= 10 ? String.valueOf(minute) : "0" + String.valueOf(minute));
    }

    private void setOnViewItemListeners() {

        //volume seekBar
        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                mute_toggle_button.setChecked((user_set_volume = progress) == 0);
            }
        });
        //brightness seekBar
        brightnessSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                user_brightness = progress;
            }
        });
        brightness_toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                user_brightness_auto = isChecked;
            }
        });

        //reset setting
        reset_setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                builder = new AlertDialog.Builder(getActivity())
                builder = new AlertDialog.Builder(
                        new ContextThemeWrapper(getActivity(), android.R.style.Theme_Holo_Light))
                        .setTitle(getResources().getString(R.string.reset_setting_confirm_title))
                        .setMessage(getResources().getString(R.string.reset_setting_confirm));
                setPositiveButtonOfReset(builder);
                setNegativeButtonOfReset(builder).create().show();

            }
        });

        //button set Listener
        save_change_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder = new AlertDialog.Builder(
                        new ContextThemeWrapper(getActivity(), android.R.style.Theme_Holo_Light))
                        .setTitle(getResources().getString(R.string.save_change_confirm_title))
                        .setMessage(getResources().getString(R.string.save_change_confirm));
                setPositiveButton(builder);
                setNegativeButton(builder).create().show();
            }
        });

        //mute_toggle_button
        mute_toggle_button.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                if (isChecked) {
                    user_mute = true;
                    volumeSeekbar.setProgress(0);
                } else {
                    if (!(ring_mode_vibrate.isChecked()) && !(ring_mode_silent.isChecked())) {
                        volumeSeekbar.setProgress(preferences.getInt(KEY_USER_VOLUME, getCurrentVolume));
                    }
                }

            }
        });

        //GPRS toggle
        GPRS_toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                user_gprs = isChecked;
            }
        });

        //bluetooth_toggle
        bluetooth_toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                user_bluetooth = isChecked;
            }
        });

        ring_mode_normal.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                if (isChecked) {
                    user_ringer_mode = RINGER_MODE_NORMAL;
                    ring_mode_silent.setChecked(false);
                    ring_mode_vibrate.setChecked(false);
                }
            }
        });
        ring_mode_silent.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                if (isChecked) {
                    user_ringer_mode = RINGER_MODE_SILENT;
                    ring_mode_normal.setChecked(false);
                    ring_mode_vibrate.setChecked(false);
                }
                //for volume seekBar
                if (isChecked) {
                    user_mute = true;
                    volumeSeekbar.setProgress(0);
                    mute_toggle_button.setChecked(true);
                } else {
                    volumeSeekbar.setProgress(preferences.getInt(KEY_USER_VOLUME, getCurrentVolume));
                }
            }
        });
        ring_mode_vibrate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                if (isChecked) {
                    vibrator.vibrate(200);//vibrate to alert
                    user_ringer_mode = RINGER_MODE_VIBRATE;
                    ring_mode_normal.setChecked(false);
                    ring_mode_silent.setChecked(false);
                }
                //for volume seekBar
                if (isChecked) {
                    user_mute = true;
                    volumeSeekbar.setProgress(0);
                    mute_toggle_button.setChecked(true);
                } else {
                    volumeSeekbar.setProgress(preferences.getInt(KEY_USER_VOLUME, getCurrentVolume));
                }
            }
        });

        //repeat mode toggle
        repeat_mode_everyday_toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                if (isChecked) {
                    user_repeat_mode = REPEAT_MODE_EVERYDAY;
                    repeat_mode_workday_toggle.setChecked(false);
                    repeat_mode_weekend_toggle.setChecked(false);
                }
            }
        });
        //repeat mode toggle
        repeat_mode_workday_toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                if (isChecked) {
                    user_repeat_mode = REPEAT_MODE_WORKDAY;
                    repeat_mode_everyday_toggle.setChecked(false);
                    repeat_mode_weekend_toggle.setChecked(false);
                }
            }
        });
        //repeat mode toggle
        repeat_mode_weekend_toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                if (isChecked) {
                    user_repeat_mode = REPEAT_MODE_WEEKEND;
                    repeat_mode_everyday_toggle.setChecked(false);
                    repeat_mode_workday_toggle.setChecked(false);
                }
            }
        });

        Wifi_toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                user_wifi = isChecked;
            }
        });

//button set Listener
        wallpaper_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //生成一个设置壁纸的请求
                try {
                    final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
                    Intent chooser = Intent.createChooser(pickWallpaper, "chooser_wallpaper");

                    //发送设置壁纸的请求
                    getActivity().startActivityForResult(chooser,1001);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //start time Time picker
        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new TimePickerDialog(getActivity(),
                        // 绑定监听器
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                trigger_period_toggle.setChecked(true);
                                user_trigger_period_on = true;
                                user_start_hour = hourOfDay;
                                user_start_minute = minute;
                                start_time.setText(StandardTimeFormat(user_start_hour, user_start_minute));
                                Toast.makeText(getActivity(), "Start time:" + StandardTimeFormat(user_start_hour, user_start_minute), Toast.LENGTH_SHORT).show();
                            }
                        }, user_start_hour, user_start_minute, true).show();
            }
        });
        //stop time time picker
        stop_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new TimePickerDialog(getActivity(),
                        // 绑定监听器
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                trigger_period_toggle.setChecked(true);
                                user_trigger_period_on = true;
                                user_stop_hour = hourOfDay;
                                user_stop_minute = minute;
                                stop_time.setText(StandardTimeFormat(user_stop_hour, user_stop_minute));
                                Toast.makeText(getActivity(), "Stop time:" + StandardTimeFormat(user_stop_hour, user_stop_minute), Toast.LENGTH_SHORT).show();
                            }
                        }, user_stop_hour, user_stop_minute, true).show();
            }
        });


        trigger_period_toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton source,
                                         boolean isChecked) {
                if (isChecked) {
                    user_trigger_period_on = true;
                }
            }
        });
    }

    public void onSaveButtonConfirm() {

        //问题症结所在，this is a milestone
        //get toggle state
        user_set_volume = volumeSeekbar.getProgress();
        user_ringer_mode = ring_mode_normal.isChecked() ? RINGER_MODE_NORMAL :
                (ring_mode_silent.isChecked() ? RINGER_MODE_SILENT : RINGER_MODE_VIBRATE);
        user_wifi = Wifi_toggle.isChecked();
        user_gprs = GPRS_toggle.isChecked();
        user_bluetooth = bluetooth_toggle.isChecked();
        user_trigger_period_on = trigger_period_toggle.isChecked();

        //save user setting preferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_USER_VOLUME, user_set_volume);
        editor.putBoolean(KEY_USER_VIBRATE, true);
        editor.putInt(KEY_USER_RING_MODE, user_ringer_mode);
        editor.putBoolean(KEY_USER_BLUETOOTH, user_bluetooth);
        editor.putBoolean(KEY_USER_MUTE, user_set_volume == 0 || user_mute);
        editor.putBoolean(KEY_USER_WIFI, user_wifi);
        editor.putBoolean(KEY_USER_GPRS, user_gprs);
        editor.putInt(KEY_USER_BRIGHTNESS, user_brightness);
        editor.putBoolean(KEY_USER_BRIGHTNESS_AUTO, user_brightness_auto);
        editor.putInt(KEY_USER_HOUR, user_start_hour);
        editor.putInt(KEY_USER_MINUTE, user_start_minute);
        editor.putInt(KEY_USER_HOUR_STOP, user_stop_hour);
        editor.putInt(KEY_USER_MINUTE_STOP, user_stop_minute);
        editor.putInt(KEY_USER_REPEAT_MODE, user_repeat_mode);
        editor.putBoolean(KEY_USER_TRIGGER_PERIOD_ON, user_trigger_period_on);
        editor.commit();
    }

    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder) {
        return builder.setPositiveButton("确认", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //PositiveButton dispose
                onSaveButtonConfirm();
                BackToParentFragment();
            }
        });
    }

    private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {
        return builder.setNegativeButton("取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //NegativeButton dispose
                //discard user change
            }
        });
    }

    //reset all preferences in this fragment
    private void ResetSetting() {
        SharedPreferences.Editor editor = preferences.edit();

        //remove user set

        editor.remove(KEY_USER_VOLUME);
        editor.remove(KEY_USER_RING_MODE);
        editor.remove(KEY_USER_WIFI);
        editor.remove(KEY_USER_GPRS);
        editor.remove(KEY_USER_BLUETOOTH);
        editor.remove(KEY_USER_BRIGHTNESS);
        editor.remove(KEY_USER_BRIGHTNESS_AUTO);
        editor.remove(KEY_USER_MUTE);
        editor.remove(KEY_USER_HOUR);
        editor.remove(KEY_USER_MINUTE);
        editor.remove(KEY_USER_HOUR_STOP);
        editor.remove(KEY_USER_MINUTE_STOP);
        editor.remove(KEY_USER_TRIGGER_PERIOD_ON);
        editor.commit();

//        //reset first launch flag
//        editor.putInt("FIRST_LAUNCH_FLAG", 1);
//        editor.commit();

    }

    private AlertDialog.Builder setPositiveButtonOfReset(AlertDialog.Builder builder) {
        return builder.setPositiveButton("确认", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //PositiveButton dispose
                Log.d("sure", "sure");
                ResetSetting();
                BackToParentFragment();
            }
        });
    }

    private AlertDialog.Builder setNegativeButtonOfReset(AlertDialog.Builder builder) {
        return builder.setNegativeButton("取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //NegativeButton dispose
                //discard user change
            }
        });
    }

    private void BackToParentFragment() {
        this.getFragmentManager().popBackStack();
//        this.onDestroy();
    }

    // onBackPressed
    public void onBackPressed() {
        //这里写返回逻辑
//        getChildFragmentManager().beginTransaction().replace(this, ).commit();

        // // FIXME: 2016/1/27  NullPointerException
//        Toast.makeText(ProfileDetailFragment.this.getActivity().getApplicationContext(), "to back press", Toast.LENGTH_SHORT).show();
        Log.d("tobackpressFragmet", " ");
    }
}
