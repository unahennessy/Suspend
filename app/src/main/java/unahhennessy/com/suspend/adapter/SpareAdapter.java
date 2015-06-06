package unahhennessy.com.suspend.adapter;
/**
 * Created by unahe_000 on 01/06/2015.
 */

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by unahe_000 on 28/05/2015.
 */
public class SpareAdapter extends BaseAdapter {

    private static final String TAG = "SpareAdapter";
    @Override
    public int getCount() {
        this.log("entered getCount() within SpareAdapter.java");
        return 0;
    }

    @Override
    public Object getItem(int position) {
        this.log("entered getItem() within SpareAdapter.java");

        return null;
    }

    @Override
    public long getItemId(int position) {
        this.log("entered getItemId() within SpareAdapter.java");

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.log("entered getView() within SpareAdapter.java");
        return null;
    }

    private void log(String msg)
    {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(SpareAdapter.TAG, msg);

    }



}
