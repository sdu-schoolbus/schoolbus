package com.sdu.online.schoolbus;

import java.util.List;
import com.sdu.online.schoolbus.model.BusInfo;
import com.sdu.online.schoolbus.model.Place;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BusCellAdapter extends BaseAdapter {

	private List<BusInfo> busInfo;
	private LayoutInflater mInflater;
	private static final String TAG = BusCellAdapter.class.getSimpleName();
	
	public BusCellAdapter(Context context,List<BusInfo> busInfo){
		this.busInfo = busInfo;
		mInflater = LayoutInflater.from(context);
	}
	
	public int getCount() {
		return busInfo.size();
	}

	public Object getItem(int position) {
		return busInfo.get(position);
	}

	public long getItemId(int position) {
		return busInfo.get(position).getId();
	}
	private void findView(ViewHolder holder ,View convertView){
		holder.tv1 = (TextView) convertView.findViewById(R.id.start_text);
		holder.tv2 = (TextView) convertView.findViewById(R.id.time_text);
		holder.tv3 = (TextView) convertView.findViewById(R.id.remark_text);
		holder.layout = (LinearLayout)convertView.findViewById(R.id.detail_layout);
		holder.detailStartTime = (TextView)convertView.findViewById(R.id.detail_layout_from_time);
		holder.detailStartPlace = (TextView)convertView.findViewById(R.id.detail_layout_from_place);
		holder.detailBetweenPlace= (TextView)convertView.findViewById(R.id.detail_layout_between_place);
		holder.detailEndPlace= (TextView)convertView.findViewById(R.id.detail_layout_to_place);
		holder.detailRemark = (TextView)convertView.findViewById(R.id.detail_layout_remark);
	}
	public View getView(int position, View convertView, ViewGroup viewParent) {
			final ViewHolder holder;
			if(convertView==null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.bus_list_cell, null);
			findView(holder, convertView);		
			convertView.setTag(holder);
			}else{
				holder=(ViewHolder)convertView.getTag();
			}
			holder.tv2.setText(busInfo.get(position).getFullFrom());
			holder.tv1.setText(busInfo.get(position).getStartTime());
			holder.detailStartPlace.setText(busInfo.get(position).getFullFrom());
			holder.detailEndPlace.setText(busInfo.get(position).getFullTo());
			holder.detailStartTime.setText(busInfo.get(position).getStartTime());
			if(busInfo.get(position).getRemark() == null)
				holder.detailRemark.setText("无");
			else holder.detailRemark.setText(busInfo.get(position).getRemark());
			List<Place> betweenBuses = busInfo.get(position).getBusBetween();
			StringBuilder builder = new StringBuilder();
			if (betweenBuses != null)
				for(Place place :betweenBuses){
					if (place != null){
						builder.append(place);
						builder.append("\n");
					}
				}
			holder.detailBetweenPlace.setText(builder);
			
			final BusInfo bus = busInfo.get(position);
			holder.tv3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Log.d(TAG, bus.toString());
					if(holder.layout.getVisibility()!=View.VISIBLE)	holder.layout.setVisibility(View.VISIBLE);
					else holder.layout.setVisibility(View.GONE);
				}
			});
			return convertView;
		}
	
	class ViewHolder{
		public TextView tv1;
		public TextView tv2;
		public TextView tv3;
		public TextView detailStartTime;
		public TextView detailStartPlace;
		public TextView detailBetweenPlace;
		public TextView detailEndPlace;
		public TextView detailRemark;
		public LinearLayout layout;
	}

}
