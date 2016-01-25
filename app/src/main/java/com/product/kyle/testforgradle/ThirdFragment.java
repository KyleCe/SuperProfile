package com.product.kyle.testforgradle;

//import android.app.Activity;
//import android.net.Uri;
//import android.os.Bundle;
//import android.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ThirdFragment#  newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_third, container, false);//关联布局文件

        ////spinner test
        Spinner mySpinner=(Spinner) rootView.findViewById(R.id.spinner_eye_protect_work);
//createFromResouce将返回ArrayAdapter<CharSequence>，具有三个参数：
//第一个是conetxt，也就是application的环境，可以设置为this，也可以通过getContext()获取.
//第二个参数是从data source中的array ID，也就是我们在strings中设置的ID号；
//第三个参数是spinner未展开的UI格式

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.work_time,android.R.layout.simple_spinner_item);
//                getActivity(), R.array.work_time,android.R.style.Widget_Holo_Light_Spinner);
        adapter.setDropDownViewResource(R.layout.my_spinner_dropdown);
//        adapter.setDropDownViewResource(android.R.style.Widget_Holo_Light_Spinner);
        mySpinner.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {

//                        Toast.makeText(getActivity(), R.string.eye_protect_work + position + id +"分钟"

                        ////debug remain
//                        Toast.makeText(getActivity(), getResources().getString(R.string.eye_protect_work) +"分钟"
//                                , Toast.LENGTH_SHORT).show();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                        Toast.makeText(getActivity(), "Spinner1: unselected"
                                , Toast.LENGTH_SHORT).show();
                    }
                });


        Spinner mySpinnerRest=(Spinner) rootView.findViewById(R.id.spinner_eye_protect_rest);

        ArrayAdapter<CharSequence> adapter_rest = ArrayAdapter.createFromResource(
                getActivity(), R.array.rest_time,android.R.layout.simple_spinner_item);
        adapter_rest.setDropDownViewResource(R.layout.my_spinner_dropdown);
//        adapter_rest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerRest.setAdapter(adapter_rest);



//        myButton = (Button)rootView.findViewById(R.id.mybutton);//根据rootView找到button

        //设置按键监听事件
//        myButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Toast.makeText(ButtonFragment.this.getActivity(), "button is click!", Toast.LENGTH_SHORT).show();
//            }
//        });


        return rootView;
    }
}
