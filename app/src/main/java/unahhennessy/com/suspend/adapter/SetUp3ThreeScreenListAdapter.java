package unahhennessy.com.suspend.adapter;

import android.content.Context;
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
  private Context mContext;
  private Vector mName;
  private Vector mNumber;
  
  public SetUp3ThreeScreenListAdapter(Context paramContext, Vector paramVector1, Vector paramVector2)
  {
    this.mContext = paramContext;
    this.mName = paramVector1;
    this.mNumber = paramVector2;
  }
  
  public int getCount()
  {
    if ((this.mName != null) && (this.mName.size() > 0)) {
      return this.mName.size();
    }
    return 0;
  }
  
  public Object getItem(int paramInt)
  {
    if ((this.mName != null) && (this.mName.size() > 0)) {
      return this.mName.get(paramInt);
    }
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return 0L;
  }
  
  public String getNumber(int paramInt)
  {
    if ((this.mNumber != null) && (this.mNumber.size() > 0)) {
      return this.mNumber.get(paramInt).toString();
    }
    return null;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ViewHolder localViewHolder = null;
    if (paramView == null)
    {
      paramView = ((LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.setup3_list_items, null);
      localViewHolder = new ViewHolder();
      localViewHolder.name = ((TextView)paramView.findViewById(R.id.text_name));
      localViewHolder.num = ((TextView)paramView.findViewById(R.id.text_num));
      localViewHolder.arrow = ((ImageView)paramView.findViewById(R.id.arrow));
      paramView.setTag(localViewHolder);
      if (!this.mName.get(paramInt).toString().equalsIgnoreCase("Emergency"))
      {
        // do someething break; //label162;
      }
      localViewHolder.arrow.setVisibility(View.GONE);
    }
    for (;;)
    {
      localViewHolder.name.setText(this.mName.get(paramInt).toString());
      localViewHolder.num.setText(this.mNumber.get(paramInt).toString());
      localViewHolder = (ViewHolder)paramView.getTag();

      localViewHolder.arrow.setVisibility(View.VISIBLE);
      return paramView;


    }

  }
  
  public boolean isEnabled(int paramInt)
  {
    if (this.mName.get(paramInt).toString().equalsIgnoreCase("Emergency")) {
      return false;
    }
    return super.isEnabled(paramInt);
  }
  
  class ViewHolder
  {
    ImageView arrow;
    TextView name;
    TextView num;
    
    ViewHolder() {}
  }
}
