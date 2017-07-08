package com.hoanganhtuan95ptit.demo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoang Anh Tuan on 3/10/2017.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> list = new ArrayList<>();
    protected Activity activity;
    protected LayoutInflater inflater;
    public OnChangedData onChangedData;

    public BaseAdapter(Activity activity) {
        this.activity = activity;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(T t) {
        synchronized (this) {
            list.add(t);
            sort();
            int position = list.indexOf(t);
            notifyItemInserted(position);
            if (onChangedData != null) onChangedData.addItem(t, position);
        }
    }

    public void clear() {
        synchronized (this) {
            list.clear();
            notifyDataSetChanged();
        }
    }

    public void addPosition(T t, int position) {
        synchronized (this) {
            list.add(position, t);
            sort();
            position = list.indexOf(t);
            notifyItemInserted(position);
            if (onChangedData != null) onChangedData.addItem(t, position);
        }
    }

    public void removerPosition(int position) {
        synchronized (this) {
            T t = list.get(position);
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeRemoved(position, list.size());
            if (onChangedData != null) onChangedData.removerItem(t, position);
        }
    }

    public void updatePosition(T t, int position) {
        synchronized (this) {
            list.set(position, t);
            notifyItemChanged(position);
            notifyItemRangeChanged(position, list.size());
            if (onChangedData != null) onChangedData.updateItem(t, position);
        }
    }

    public void setDatas(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> getDatas() {
        return (ArrayList<T>) list;
    }

    public void addOnChangedData(OnChangedData onChangedData) {
        if (this.onChangedData != null) return;
        this.onChangedData = onChangedData;
    }

    public void sort() {
    }

    public interface OnChangedData<T> {
        void addItem(T t, int position);

        void updateItem(T t, int position);

        void removerItem(T t, int position);
    }
}
