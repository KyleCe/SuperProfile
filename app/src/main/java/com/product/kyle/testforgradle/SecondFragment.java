package com.product.kyle.testforgradle;

//import android.app.Activity;
//import android.net.Uri;
//import android.os.Bundle;
//import android.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;

//import com.product.kyle.testforgradle.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

public class SecondFragment extends Fragment {
    private static final String TAG = "TestFragment";
//    private String hello;// = "hello android";
//    private String defaultHello = "default value";

//    static SecondFragment newInstance(String s) {
//        SecondFragment newFragment = new SecondFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("hello", s);
//        newFragment.setArguments(bundle);
//
//        //bundle还可以在每个标签里传送数据
//
//
//        return newFragment;
//
//    }


    Button start_button = null;
    private CheckBox cb_agreement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "TestFragment-----onCreateView");
//        Bundle args = getArguments();
//        hello = args != null ? args.getString("hello") : defaultHello;


//        Layer layer = new Layer(getActivity());
//        setColorsViews();
//        ScreenAdjuster.setColor(redView, greenView, blueView, 50, 150, 0);
//        container.addView(redView);
//        container.addView(greenView);
//        container.addView(blueView);

        View view = inflater.inflate(R.layout.fragment_second, container, false);

        start_button = (Button) view.findViewById(R.id.start_button);
        cb_agreement = (CheckBox) view.findViewById(R.id.cb_agreement);

        start_button.setEnabled(false);

        cb_agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb_agreement.isChecked())
                {
                    start_button.setEnabled(true);
                }
                else{
                    start_button.setEnabled(false);
                }
            }
        });

        return view;
//        return layer;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
//        Layer layer = new Layer(getActivity());
//        try {
//            layer.setColorsViews();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        setColorsViews();
//        ScreenAdjuster.setColor(redView, greenView, blueView, 50, 150, 0);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), CoverActivity.class);
//                startActivity(intent);
            }
        });

    }

    boolean colorsFirstTime = false;
    Layer view;
    Layer redView;
    Layer greenView;
    Layer blueView;

    public void setColorsViews() {
        if (!colorsFirstTime) {
            view = new Layer(getActivity());
            redView = new Layer(getActivity());
            greenView = new Layer(getActivity());
            blueView = new Layer(getActivity());

            WindowManager localWindowManager = (WindowManager) getActivity()
                    .getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams layoutParams = getActivity().getWindow()
                    .getAttributes();
            layoutParams.format = PixelFormat.TRANSLUCENT;

//                view.setColor(60, 50, 150, 0);
//                redView.setColor(60, 150, 0, 0);
//                greenView.setColor(60, 0, 150, 0);
//                blueView.setColor(60, 0, 0, 150);
            localWindowManager.addView(view, layoutParams);
            localWindowManager.addView(redView, layoutParams);
            localWindowManager.addView(greenView, layoutParams);
            localWindowManager.addView(blueView, layoutParams);
            colorsFirstTime = true;

            Log.d("display", "views added");
        }
    }

    //Screen RGB
    static class ScreenAdjuster {

        public static void setAlpha(Layer view, int alpha) {
            //Handle all conditions
            view.setColor(alpha, 0, 0, 0);
        }

        public static void setContrast(Layer view, int contrast) {
            //Handle all conditions
            view.setColor(contrast, 100, 100, 100);
        }

        public static void setColor(Layer redView, Layer greenView, Layer blueView, int r, int g, int b) {
            //Handle all conditions
            redView.setColor(r, 255, 0, 0);
            greenView.setColor(g, 0, 255, 0);
            blueView.setColor(b, 0, 0, 255);
            Log.d("display", "setting..." + r + " " + g + " " + b);
        }

    }

    class Layer extends View {
        private int a;
        private int b;
        private int g;
        private int r;

        public Layer(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawARGB(this.a, this.r, this.g, this.b);
            Log.d("display", "rendering..");
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
            int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
            this.setMeasuredDimension(parentWidth / 2, parentHeight);
            //Since you are attatching it to the window use window layout params.
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(parentWidth / 2, parentHeight);
            this.setLayoutParams(layoutParams);

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Log.d("display", "filling...");
        }

        public void setColor(int a, int r, int g, int b) {
            this.a = a;
            this.r = r;
            this.g = g;
            this.b = b;
            invalidate();
        }


    }


}