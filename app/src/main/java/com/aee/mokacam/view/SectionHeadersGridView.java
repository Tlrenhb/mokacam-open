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

    public void setGridAdapter(BaseAdapter base) {
        GridLayoutManager lm = new GridLayoutManager(getContext(), 4);
        gridLayoutManager = lm;
        final Wrapper w = new Wrapper(base, SectionHeadersGridView.this);
        lm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return w.isHeader(position) ? 4 : 1;
            }
        });
        setLayoutManager(lm);
        wrapper = w;
        super.setAdapter(w);
    }

    private Wrapper wrapper;
    private GridLayoutManager gridLayoutManager;
    private android.widget.AdapterView.OnItemClickListener itemClickListener;
    private android.widget.AdapterView.OnItemLongClickListener itemLongClickListener;
    private android.widget.AbsListView.OnScrollListener scrollListener;

    /** GridView-compat: number of columns. */
    public void setNumColumns(int numColumns) {
        if (gridLayoutManager != null) {
            gridLayoutManager.setSpanCount(numColumns);
        }
        requestLayout();
    }

    /** GridView-compat: accepted for source compatibility; cell width is fluid. */
    public void setColumnWidth(int px) {
    }

    /** GridView-compat: scroll to a source-adapter position. */
    public void setSelection(int position) {
        if (wrapper != null) {
            int flat = wrapper.flatPositionOf(position);
            if (flat >= 0) {
                scrollToPosition(flat);
            }
        }
    }

    /** GridView-compat item click bridge. */
    public void setOnItemClickListener(android.widget.AdapterView.OnItemClickListener l) {
        itemClickListener = l;
    }

    /** GridView-compat long click bridge. */
    public void setOnItemLongClickListener(android.widget.AdapterView.OnItemLongClickListener l) {
        itemLongClickListener = l;
    }

    /** GridView-compat scroll listener bridge (AbsListView style). */
    public void setOnScrollListener(final android.widget.AbsListView.OnScrollListener l) {
        scrollListener = l;
        super.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                int state;
                switch (newState) {
                    case SCROLL_STATE_SETTLING:
                        state = android.widget.AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;
                        break;
                    case SCROLL_STATE_DRAGGING:
                        state = android.widget.AbsListView.OnScrollListener.SCROLL_STATE_FLING;
                        break;
                    default:
                        state = android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;
                }
                l.onScrollStateChanged(null, state);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                l.onScroll(null, 0, 0, 0);
            }
        });
    }

    private static final class Wrapper extends RecyclerView.Adapter<VH> {

        private final BaseAdapter src;
        private final SectionHeadersGridView.HeaderAdapter headers;
        /** Flat layout: header marker positions encoded as -(srcIndex + 1). */
        private final ArrayList<Integer> flat = new ArrayList<>();

        Wrapper(BaseAdapter src, SectionHeadersGridView grid) {
            this.grid = grid;
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

        int flatPositionOf(int srcIndex) {
            for (int i = 0; i < flat.size(); i++) {
                if (flat.get(i) == srcIndex) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            FrameLayout container = (FrameLayout) holder.itemView;
            container.removeAllViews();
            View v;
            final int srcIndex = sourceIndex(position);
            final boolean header = isHeader(position);
            if (header) {
                v = headers.headerView(srcIndex, null, container);
            } else {
                v = src.getView(srcIndex, null, container);
            }
            container.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (header) {
                        return;
                    }
                    if (grid.itemClickListener != null) {
                        grid.itemClickListener.onItemClick(null, view, srcIndex, srcIndex);
                    }
                }
            });
            container.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (header) {
                        return false;
                    }
                    return grid.itemLongClickListener != null
                            && grid.itemLongClickListener
                            .onItemLongClick(null, view, srcIndex, srcIndex);
                }
            });
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
