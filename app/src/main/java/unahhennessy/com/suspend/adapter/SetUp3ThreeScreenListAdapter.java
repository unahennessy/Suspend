package unahhennessy.com.suspend.adapter;
/**
 * Created by unahe_000 on 01/06/2015 ${PACKAGE_NAME} Suspend.
 *
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Vector;

import unahhennessy.com.suspend.R;

public class SetUp3ThreeScreenListAdapter  extends BaseAdapter
{
    // this adapter is used by the NotifyListScreen.java class to fill the setup3_list_item.xml screen with items that have
    // come in while SuspendOn was set. It is used to fill the screen after SuspendOff screen reached.
  private Context mContext;
  private Vector mName;
  private Vector mNumber;
  private static final String TAG = "SetUp3ListAdapter";
  
  public SetUp3ThreeScreenListAdapter(Context paramContext, Vector paramVector1, Vector paramVector2)
  {
    this.log("entered SetUp3ThreeScreenListAdapter() within SetUp3ThreeScreenListAdapter.java");
   
    this.mContext = paramContext;
    this.mName = paramVector1;
    this.mNumber = paramVector2;
  }
  
  public int getCount()
  {this.log("entered getCount() within SetUp3ThreeScreenListAdapter.java");
    if ((this.mName != null) && (this.mName.size() > 0)) {
      return this.mName.size();
    }
    return 0;
  }
  
  public Object getItem(int paramInt)
  {this.log("entered getItem() within SetUp3ThreeScreenListAdapter.java");
    if ((this.mName != null) && (this.mName.size() > 0)) {
      return this.mName.get(paramInt);
    }
    return null;
  }
  
  public long getItemId(int paramInt)
  {
      this.log("entered getItemId() within SetUp3ThreeScreenListAdapter.java");
      
      return 0L;
  }
  
    
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
      this.log("entered getView() within SetUp3ThreeScreenListAdapter.java");
    ViewHolder mViewHolder = null;
        if (paramView == null)
        {
          paramView = ((LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.setup3_list_items, null);
          mViewHolder = new ViewHolder();
          mViewHolder.name = ((TextView)paramView.findViewById(R.id.text_name));
          mViewHolder.num = ((TextView)paramView.findViewById(R.id.text_num));
          mViewHolder.arrow = ((ImageView)paramView.findViewById(R.id.arrow));
          paramView.setTag(mViewHolder);

        }
        for (;;)
        {
          mViewHolder.name.setText(this.mName.get(paramInt).toString());
          mViewHolder.num.setText(this.mNumber.get(paramInt).toString());
          mViewHolder = (ViewHolder)paramView.getTag();
          mViewHolder.arrow.setVisibility(View.VISIBLE);
          return paramView;
        }

  }
  
  public boolean isEnabled(int paramInt)
  {
     this.log("entered isEnabled() within SetUp3ThreeScreenListAdapter.java");

    return super.isEnabled(paramInt);
  }
  
  class ViewHolder
  {
    ImageView arrow;
    TextView name;
    TextView num;
    
    ViewHolder() {}
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
        Log.i(SetUp3ThreeScreenListAdapter.TAG, msg);

    }



}
