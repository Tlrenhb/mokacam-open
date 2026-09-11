package com.aee.mokacam.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Drop-in replacement for the tonicartos StickyGridHeadersGridView used by
 * the original library screen. Wraps a BaseAdapter that also implements the
 * original header contract (headerId(position) + headerView(position, ...)),
 * renders date header rows as full-span cells and the rest as grid cells.
 */
public class SectionHeadersGridView extends RecyclerView {

    /** Mirrors the obfuscated tonicartos interface com.tonicartos...t. */
    public interface HeaderAdapter {
        long headerId(int position);

        View headerView(int position, View convertView, ViewGroup parent);
    }

    public SectionHeadersGridView(Context context) {
        super(context);
    }

    public SectionHeadersGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SectionHeadersGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter instanceof BaseAdapter) {
            BaseAdapter base = (BaseAdapter) adapter;
            GridLayoutManager lm = new GridLayoutManager(getContext(), 4);
            lm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return wrapper != null && wrapper.isHeader(position) ? 4 : 1;
                }
            });
            setLayoutManager(lm);
            wrapper = new Wrapper(base);
            super.setAdapter(wrapper);
        } else {
            super.setAdapter(adapter);
        }
    }

    private Wrapper wrapper;

    private static final class Wrapper extends RecyclerView.Adapter<VH> {

        private final BaseAdapter src;
        private final SectionHeadersGridView.HeaderAdapter headers;
        /** Flat layout: header marker positions encoded as -(srcIndex + 1). */
        private final ArrayList<Integer> flat = new ArrayList<>();

        Wrapper(BaseAdapter src) {
            this.src = src;
            this.headers = src instanceof SectionHeadersGridView.HeaderAdapter
                    ? (SectionHeadersGridView.HeaderAdapter) src : null;
            src.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    rebuild();
                    notifyDataSetChanged();
                }
            });
            rebuild();
        }

        boolean isHeader(int flatPos) {
            return flatPos >= 0 && flatPos < flat.size() && flat.get(flatPos) < 0;
        }

        private void rebuild() {
            flat.clear();
            long lastId = Long.MIN_VALUE;
            int n = src.getCount();
            for (int i = 0; i < n; i++) {
                long id = headers != null ? headers.headerId(i) : Long.MIN_VALUE;
                if (headers != null && id != lastId) {
                    flat.add(-1 - i);
                    lastId = id;
                }
                flat.add(i);
            }
        }

        private int sourceIndex(int position) {
            int encoded = flat.get(position);
            return encoded < 0 ? -1 - encoded : encoded;
        }

        @Override
        public int getItemCount() {
            return flat.size();
        }

        @Override
        public int getItemViewType(int position) {
            return isHeader(position) ? 1 : 2;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            FrameLayout container = new FrameLayout(parent.getContext());
            container.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            return new VH(container);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            FrameLayout container = (FrameLayout) holder.itemView;
            container.removeAllViews();
            View v;
            if (isHeader(position)) {
                v = headers.headerView(sourceIndex(position), null, container);
            } else {
                v = src.getView(sourceIndex(position), null, container);
            }
            if (v.getParent() instanceof ViewGroup) {
                ((ViewGroup) v.getParent()).removeView(v);
            }
            container.addView(v, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT));
        }

        @Override
        public void onViewRecycled(@NonNull VH holder) {
            FrameLayout container = (FrameLayout) holder.itemView;
            if (container.getChildCount() > 0) {
                container.removeAllViews();
            }
        }
    }

    static class VH extends RecyclerView.ViewHolder {
        VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
