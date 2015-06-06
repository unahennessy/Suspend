package unahhennessy.com.suspend.adapter;

/**
 * Created by unahe_000 on 01/06/2015.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import unahhennessy.com.suspend.R;

public class LogScreenAdapter  extends BaseAdapter
{
  
    // Log screen adapter to display logs
  private Context mContext;
  private List<String> mValuesToBeShown;
  private static final String TAG = "LogScreenAdapter";
  public long getItemId(int paramInt)
    {
        return 0L;
    }
    
  public LogScreenAdapter(Context paramContext, List<String> paramList)
  {
      this.log("entered LogScreenAdapter() within LogScreenAdapter.java");
    this.mContext = paramContext;
    this.mValuesToBeShown = paramList;
  }
  
  public int getCount()
  {
      this.log("entered getCount() within LogScreenAdapter.java");
    if (this.mValuesToBeShown != null) {
      return this.mValuesToBeShown.size();
    }
    return 0;
  }
  
  public Object getItem(int paramInt)
  {
      this.log("entered getItem() within LogScreenAdapter.java");
    if ((this.mValuesToBeShown != null) && (this.mValuesToBeShown.size() > paramInt))
    {
      return this.mValuesToBeShown.get(paramInt);
    }
    else return null;
  }
  
  
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    this.log("entered getView() within LogScreenAdapter.java");
    ViewHolder mViewHolder = null;
    if (paramView == null)
    {
      paramView = ((LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.log_list_item, null);
      mViewHolder = new ViewHolder();
      mViewHolder.log_text = ((TextView)paramView.findViewById(R.id.text_log));
      paramView.setTag(mViewHolder);
    }
    for (;;)
    {mViewHolder = (ViewHolder)paramView.getTag();
      mViewHolder.log_text.setText(getItem(paramInt).toString());

      return paramView;

    }
  }
  
  class ViewHolder
  {
      
    TextView log_text;
    
    ViewHolder()
    {}
  }

    private void log(String msg)
    {
        //log(msg) to show TAG in every method
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(LogScreenAdapter.TAG, msg);

    } 
}
