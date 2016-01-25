package com.product.kyle.testforgradle.utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.product.kyle.testforgradle.R;

public class TestingAlgorithm extends Activity {

    public static void quick_sort_example(){
        Character[] a = {'Q','U','I','C','K','S','O','R','T','E','X','A','M','P','L','E'};
        quick_sort(a,0,a.length - 1);
        show(a);
    }

    public static void quick_sort(Comparable[] a,int lo, int hi){
        if(hi <= lo) return;
        int j = partition(a,lo,hi);
        quick_sort(a, lo, j-1);
        quick_sort(a, j+ 1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi){
        int i = lo,j = hi+1;
        Comparable v = a[lo];
        while(true){
            while(less(a[++i], v)) if(i == hi) break;
            while(less(v, a[--j])) if(j == lo) break;
            if(i >= j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }

    // shwo less exch function code
    public static void exch(Comparable[] a, int m, int n) {
        Comparable t = a[m];
        a[m] = a[n];
        a[n] = t;
    }


    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void show(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            sop(a[i]);
        }
    }

    public static void sop(Object o) {
        System.out.println(o.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_algorithm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_testing_algorithm, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }
}
