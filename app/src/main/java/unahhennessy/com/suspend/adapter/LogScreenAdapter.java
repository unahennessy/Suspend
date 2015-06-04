package unahhennessy.com.suspend.adapter;

/**
 * Created by unahe_000 on 01/06/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import unahhennessy.com.suspend.R;

public class LogScreenAdapter   extends BaseAdapter
{
    // Log screen adapter to display logs
  private Context mContext;
  private List<String> mValuestoBeShown;
  
  public LogScreenAdapter(Context paramContext, List<String> paramList)
  {
    this.mContext = paramContext;
    this.mValuestoBeShown = paramList;
  }
  
  public int getCount()
  {
    if (this.mValuestoBeShown != null) {
      return this.mValuestoBeShown.size();
    }
    return 0;
  }
  
  public Object getItem(int paramInt)
  {
    if ((this.mValuestoBeShown != null) && (this.mValuestoBeShown.size() > paramInt)) {
      return this.mValuestoBeShown.get(paramInt);
    }
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return 0L;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ViewHolder localViewHolder = null;
    if (paramView == null)
    {
      paramView = ((LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.log_list_item, null);
      localViewHolder = new ViewHolder();
      localViewHolder.log_text = ((TextView)paramView.findViewById(R.id.text_log));
      paramView.setTag(localViewHolder);
    }
    for (;;)
    {localViewHolder = (ViewHolder)paramView.getTag();
      localViewHolder.log_text.setText(getItem(paramInt).toString());

      return paramView;

    }
  }
  
  class ViewHolder
  {
    TextView log_text;
    
    ViewHolder() {}
  }
}
