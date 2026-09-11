package com.aee.mokacam.ui.library;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.aee.mokacam.R;
import com.aee.mokacam.download.DownloadManager;
import com.aee.mokacam.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Active download queue. Replacement of
 * com.aee.zone.activity.DownLoadActivity: single task at a time, progress
 * labels and cancel on exit.
 */
public class DownloadActivity extends BaseActivity implements DownloadManager.Listener {

    private ListView list;
    private TextView tvEmpty;
    private final List<DownloadManager.DownloadTask> snapshot = new ArrayList<>();
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        list = find(R.id.list_downloads);
        tvEmpty = find(R.id.tv_download_empty);
        adapter = new TaskAdapter();
        list.setAdapter(adapter);
        find(R.id.iv_download_back).setOnClickListener(v -> finish());
        DownloadManager.get().addListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onDestroy() {
        DownloadManager.get().removeListener(this);
        // Original behaviour: leaving this screen cancels pending tasks.
        DownloadManager.get().cancelAll();
        super.onDestroy();
    }

    private void refresh() {
        snapshot.clear();
        DownloadManager.DownloadTask current = DownloadManager.get().currentTask();
        if (current != null) {
            snapshot.add(current);
        }
        adapter.notifyDataSetChanged();
        tvEmpty.setVisibility(snapshot.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onStateChanged(DownloadManager.DownloadTask task) {
        runOnUiThread(this::refresh);
    }

    private class TaskAdapter extends android.widget.BaseAdapter {

        @Override
        public int getCount() {
            return snapshot.size();
        }

        @Override
        public Object getItem(int position) {
            return snapshot.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, android.view.ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.item_download, parent, false);
            }
            DownloadManager.DownloadTask t = snapshot.get(position);
            TextView name = v.findViewById(R.id.tv_download_name);
            TextView state = v.findViewById(R.id.tv_download_state);
            name.setText(new java.io.File(t.destFile).getName());
            int res;
            switch (t.state) {
                case WAITING:
                    res = R.string.load_waitting;
                    break;
                case RUNNING:
                    res = R.string.load_loading;
                    break;
                case DONE:
                    res = R.string.load_finish;
                    break;
                case CANCELLED:
                    res = R.string.load_cancel;
                    break;
                default:
                    res = R.string.load_fail;
            }
            state.setText(t.state == DownloadManager.State.RUNNING
                    ? getString(res) + " " + t.percent + "%" : getString(res));
            return v;
        }
    }
}
