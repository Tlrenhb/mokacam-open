package com.aee.mokacam.ui.info;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.aee.mokacam.R;
import com.aee.mokacam.ui.BaseActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Support / FAQ. Replacement of com.aee.zone.activity.SupportActivity and
 * its ExpandableListAdapter helpers (aw etc.): the same question/answer
 * content for the camera and the Condor/Sparrow drones plus the vendor hot
 * lines.
 */
public class SupportActivity extends BaseActivity {

    private ExpandableListView list;
    private final List<String> groups = new ArrayList<>();
    private final List<List<String>> children = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        list = find(R.id.expand_support);
        find(R.id.iv_support_back).setOnClickListener(v -> finish());

        buildFaq();
        list.setAdapter(new FaqAdapter());
        list.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> true);

        TextView telCn = find(R.id.tv_china_tel);
        TextView telUsa = find(R.id.tv_usa_tel);
        TextView telEu = find(R.id.tv_eu_tel);
        telCn.setText(getString(R.string.support_contact) + "  " + getString(R.string.china_tel));
        telUsa.setText(getString(R.string.usa_tel));
        telEu.setText(getString(R.string.eu_tel));
        telCn.setOnClickListener(v -> dial(getString(R.string.china_tel)));
        telUsa.setOnClickListener(v -> dial(getString(R.string.usa_tel)));
        telEu.setOnClickListener(v -> dial(getString(R.string.eu_tel)));

        TextView register = find(R.id.tv_register);
        register.setOnClickListener(v ->
                startActivity(new android.content.Intent(this, RegisterActivity.class)));
    }

    private void dial(String phone) {
        String number = phone.replaceAll("[^0-9+]", "");
        try {
            startActivity(new android.content.Intent(
                    android.content.Intent.ACTION_DIAL,
                    android.net.Uri.parse("tel:" + number)));
        } catch (Exception ignored) {
        }
    }

    private void buildFaq() {
        Map<String, List<String>> faq = new LinkedHashMap<>();
        List<String> camera = new ArrayList<>();
        camera.add(getString(R.string.camera_question1) + "\n" + getString(R.string.camera_answer1));
        camera.add(getString(R.string.camera_question2) + "\n" + getString(R.string.camera_answer5));
        camera.add(getString(R.string.camera_question3) + "\n" + getString(R.string.camera_answer5));
        camera.add(getString(R.string.camera_question4) + "\n" + getString(R.string.camera_answer5));
        camera.add(getString(R.string.camera_question5) + "\n" + getString(R.string.camera_answer5));
        faq.put(getString(R.string.choosemode) + " - Mokacam", camera);

        List<String> sparrow = new ArrayList<>();
        sparrow.add(getString(R.string.sparrow_question1) + "\n" + getString(R.string.sparrow_answer1));
        sparrow.add(getString(R.string.sparrow_question2) + "\n" + getString(R.string.sparrow_answer2));
        sparrow.add(getString(R.string.sparrow_question3) + "\n" + getString(R.string.sparrow_answer3));
        sparrow.add(getString(R.string.sparrow_question4) + "\n" + getString(R.string.sparrow_answer4));
        sparrow.add(getString(R.string.sparrow_question5) + "\n" + getString(R.string.sparrow_answer5));
        faq.put(getString(R.string.choosemode) + " - Sparrow", sparrow);

        List<String> condor = new ArrayList<>();
        condor.add(getString(R.string.condor_question1) + "\n" + getString(R.string.condor_answer1));
        condor.add(getString(R.string.condor_question2) + "\n" + getString(R.string.condor_answer2));
        condor.add(getString(R.string.condor_question3) + "\n" + getString(R.string.condor_answer3));
        condor.add(getString(R.string.condor_question4) + "\n" + getString(R.string.condor_answer4));
        condor.add(getString(R.string.condor_question5) + "\n" + getString(R.string.condor_answer5));
        faq.put(getString(R.string.choosemode) + " - Condor", condor);

        for (Map.Entry<String, List<String>> e : faq.entrySet()) {
            groups.add(e.getKey());
            children.add(e.getValue());
        }
    }

    private class FaqAdapter extends android.widget.BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return groups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                                 ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.item_faq_group, parent, false);
            }
            TextView tv = v.findViewById(R.id.tv_parent);
            tv.setText(groups.get(groupPosition));
            return v;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.item_faq_child, parent, false);
            }
            TextView tv = v.findViewById(R.id.tv_child);
            tv.setText(children.get(groupPosition).get(childPosition));
            return v;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
