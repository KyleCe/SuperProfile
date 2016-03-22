package com.product.kyle.testforgradle;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.product.kyle.testforgradle.data.CData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FourthFragment extends Fragment {

    private Context context;
    private static Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fourth, container, false);//关联布局文件

        context = FourthFragment.this.getActivity();

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
                // create email text input dialog
                showDialog(context);
            }
        });

//        return listView;
        return rootView;
    }


    private String emailContent;

    /**
     * @param context
     */
    private void showDialog(Context context) {

        dialog = new Dialog(context, R.style.selectorDialog);
        dialog.setContentView(R.layout.share_dialog_view);

        EditText contentInput = (EditText) dialog.findViewById(R.id.tv_long_click_email_content);

        // clear input history
        emailContent = "";

        // check if the item clicked is local me or not
        contentInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                emailContent = s.toString();
            }
        });

        dialog.findViewById(R.id.tv_long_click_send_email).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(emailContent)) return;

//                        // send email
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL, new String[]{CData.EMAIL_ADD});
                        i.putExtra(Intent.EXTRA_SUBJECT,  "bug report from user of Super Profile");
                        i.putExtra(Intent.EXTRA_TEXT,emailContent);
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        } finally {
                            dialog.dismiss();
                        }
                    }
                });

        dialog.findViewById(R.id.tv_share_cancle).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    public void testOfTextViewOnClick() {

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


    private List<String> getData() {

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