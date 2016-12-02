package com.allen.common.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 两级的listview的 adapter
 * @author Allen
 *
 */
public abstract class ContacterExpandAdapter<T> extends BaseExpandableListAdapter {

	private List<List<T>> groups = null;
	
	public ContacterExpandAdapter() {
		this.groups = new ArrayList<List<T>>();
	}

	public void setContacter(List<List<T>> groups) {
		this.groups = groups;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groups.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public abstract View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent);
	/*{
		ImageLoader imgLoader = ImageLoader.getInstance(context);
		AsyncBitmapLoader asyncLoader = new AsyncBitmapLoader();
		
		final UserInfo user = groups.get(groupPosition).getUsers().get(childPosition);
		ChoildHolder childHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.contact_child, null);
			childHolder = new ChoildHolder();
			childHolder.sign = (TextView) convertView.findViewById(R.id.contact_sign);
			childHolder.username = (TextView) convertView.findViewById(R.id.contact_name);
			childHolder.image = (ImageView) convertView.findViewById(R.id.contact_img);
			childHolder.newMsgCount = (TextView) convertView.findViewById(R.id.contact_new_msg_count);
			childHolder.netType = (TextView) convertView.findViewById(R.id.contact_type);
			convertView.setTag(childHolder);
		} else {
			childHolder = (ChoildHolder) convertView.getTag();
		}
		
		user.setGroupName(groups.get(groupPosition).getName());
		childHolder.username.setTag(user);
		childHolder.username.setText(user.getName());
		childHolder.netType.setText("3G");
		//未读消息
		int unreadMsgCount = ma.getUnreadMsgItem(user.getUId())+user.getDbUnreadMsgCount();
		if(unreadMsgCount==0){
			childHolder.newMsgCount.setVisibility(View.INVISIBLE);
			childHolder.newMsgCount.setText("");
		}else if(unreadMsgCount>=99){
			childHolder.newMsgCount.setVisibility(View.VISIBLE);
			childHolder.newMsgCount.setText(99+"+");			
		}else{
			childHolder.newMsgCount.setVisibility(View.VISIBLE);
			childHolder.newMsgCount.setText(unreadMsgCount+"");
		}*/
		
		/*if(childHolder != null) {
			// 头像不为空  1在线 0离线
			if(!user.getFacePath().equals("") && null != user.getFacePath()) {
				imgLoader.addTask(CommParams.ROOT_URL + user.getFacePath(),
						childHolder.image,user); 
			}else{
				Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.wts_icon2);
				// 头像为空 显示默认图片
				if(user.getState() == 0) {
					childHolder.image.setImageBitmap(ImageUtil.grey(bmp));
				}else{
					childHolder.image.setImageBitmap(bmp);
				}
			}
		}*/
		
		/*if (childHolder != null) {

			Bitmap bmp = asyncLoader.loadBitmap(childHolder.image, CommParams.ROOT_URL+user.getFacePath(), 100, 100, new ImageCallBack() {

				@Override
				public void imageLoad(ImageView imageView, Bitmap bitmap) {
					if (bitmap == null) {
						bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wts_icon2);
					}
					if(user.getState() == 0) {
						imageView.setImageBitmap(ImageUtil.grey(bitmap));
					}else{
						imageView.setImageBitmap(bitmap);
					}
				}
			});

			if (bmp != null) {
				if(user.getState() == 0) {
					childHolder.image.setImageBitmap(ImageUtil.grey(bmp));
				}else{
					childHolder.image.setImageBitmap(bmp);
				}
			}
		}
		
		String state = UserStatus.getName(user.getState());  // 1在线 0离线
		
		childHolder.sign.setText("["+state+"] "+ user.getSign());*/
		/*childHolder.mood.setTag(groupPosition);
		childHolder.image.setTag(childPosition);
		childHolder.mood.setText(user.getStatus() == null ? "" : user
				.getStatus());
		childHolder.username.setText(user.getName() + "---"
				+ (user.isAvailable() ? "����" : "����"));
		if (user.isAvailable()) {
			childHolder.username.setTextColor(Color.BLACK);
			childHolder.mood.setTextColor(Color.BLACK);
		} else {
			childHolder.username.setTextColor(Color.GRAY);
			childHolder.mood.setTextColor(Color.GRAY);
		}*/
			/*final int groupIndex = groupPosition;
			final int childIndex = childPosition;
		convertView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 ma.removeUnreadMsgItem(user.getUId());
				 groups.get(groupIndex).getUsers().get(childIndex).setDbUnreadMsgCount(0);
				 ContacterExpandAdapter.this.notifyDataSetChanged();
				Intent intent = new Intent(context, ChatMainActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("user", user);
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		return convertView;
	}*/

	@Override
	public int getChildrenCount(int groupPosition) {
		// 如果某个组里面没有用户则返回
		if(groups.get(groupPosition).size() == 0) {
			return 0;
		}
		return groups.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public abstract View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent);
	/*{

		GroupHolder groupHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.contact_group, null);
			groupHolder = new GroupHolder();
			groupHolder.groupname = (TextView) convertView
					.findViewById(R.id.groupName);
			groupHolder.onlineState = (TextView) convertView
					.findViewById(R.id.onlineState);
			convertView.setTag(groupHolder);
		} else {
			groupHolder = (GroupHolder) convertView.getTag();
		}

		groupHolder.groupname.setText(groups.get(groupPosition).getName());
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		buffer.append(groups.get(groupPosition).getOnLineCount());
		buffer.append("/");
		buffer.append(groups.get(groupPosition).getCount());
		buffer.append("]");
		groupHolder.onlineState.setText(buffer.toString());
		groups.get(groupPosition).setOnLineCount(0);
		// convertView.setOnLongClickListener(mainCcontacterOnLongClick);
		groupHolder.groupname.setTag(groups.get(groupPosition).getName());
		
		return convertView;
	}*/

	class GroupHolder {
		TextView groupname;
		TextView onlineState;
	}

	class ChoildHolder {
		TextView username;
		TextView state;
		TextView sign;
		TextView netType;
		ImageView image;
		TextView newMsgCount;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}