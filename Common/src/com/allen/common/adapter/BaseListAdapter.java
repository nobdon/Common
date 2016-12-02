package com.allen.common.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * 只有一级的listview列表adapter 
 * @author Allen
 * @param <T>
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
	protected List<T> items = null;

	public BaseListAdapter() {
		this.items = new ArrayList<T>();
	}

	public void addItems(T[] items) {
		if (items == null) {
			return;
		}
		int count = items.length;
		for (int i = 0; i < count; i++) {
			if (items[i] != null) {
				this.items.add(items[i]);
			}
		}
	}

	public void addItems(List<T> items) {
		if (items == null) {
			return;
		}
		int count = items.size();
		for (int i = 0; i < count; i++) {
			if (items.get(i) != null) {
				this.items.add(items.get(i));
			}
		}
	}

	public void setItems(T[] items) {
		this.items.clear();
		addItems(items);
	}

	public void setItems(List<T> items) {
		this.items.clear();
		if (items == null) {
			return;
		}
		int count = items.size();
		for (int i = 0; i < count; i++) {
			if (items.get(i) != null) {
				this.items.add(items.get(i));
			}
		}
	}

	@Override
	public int getCount() {
		return this.items.size();
	}

	@Override
	public T getItem(int position) {
		return this.items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);
}
