package com.product.kyle.testforgradle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.product.kyle.testforgradle.utils.MapActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FourthFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fourth, container, false);//关联布局文件

        TextView tv = (TextView) rootView.findViewById(R.id.tv_on_fourth_fragment);//根据rootView找到button

        //设置按键监听事件
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(FourthFragment.this.getActivity(), "forth is clicked!", Toast.LENGTH_SHORT).show();
            }
        });

//        listView = new ListView(getActivity());
//        listView.setAdapter(new ArrayAdapter<String>(getActivity()
//                , R.layout.my_spinner_dropdown,getData()));
////                , android.R.layout.simple_expandable_list_item_1,getData()));


        //// list view with hasp map
//        SimpleAdapter adapter = new SimpleAdapter(getActivity(),getDataMap(),R.layout.list_view_test,
//                new String[]{"title","info","img"},
//                new int[]{R.id.title,R.id.info,R.id.img});
//        listView.setAdapter(adapter);


        TextView tv_test = (TextView) rootView.findViewById(R.id.tv_map_api_test);
        tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });

//        return listView;
        return rootView;
    }

    public void testOfTextViewOnClick(){

        Toast.makeText(FourthFragment.this.getActivity(), "text view clicked", Toast.LENGTH_SHORT).show();
    }

    public void onBackPressed() {
        Toast.makeText(FourthFragment.this.getActivity(), "back is clicked!", Toast.LENGTH_SHORT).show();

    }



    private ListView listView;
//    //private List<String> data = new ArrayList<String>();
//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//
//         setContentView(listView);
//    }



    private List<String> getData(){

        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }

    //// for list view with pic
    private List<Map<String, Object>> getDataMap() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "G1");
        map.put("info", "google 1");
        map.put("img", R.drawable.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G2");
        map.put("info", "google 2");
        map.put("img", R.drawable.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("img", R.drawable.ic_launcher);
        list.add(map);

        return list;
    }
}