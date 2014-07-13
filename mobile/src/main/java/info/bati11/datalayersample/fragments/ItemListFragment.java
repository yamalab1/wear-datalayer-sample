package info.bati11.datalayersample.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import info.bati11.datalayersample.DataItemSampleActivity;
import info.bati11.datalayersample.MessageSampleActivity;

public class ItemListFragment extends ListFragment {

    ArrayAdapter<String> adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<String> listItems = new ArrayList<String>();
        listItems.add("DataItem");
        listItems.add("Message");
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String item = adapter.getItem(position);
        if ("DataItem".equals(item)) {
            Intent intent = new Intent(getActivity(), DataItemSampleActivity.class);
            startActivity(intent);
        } else if ("Message".equals(item)) {
            Intent intent = new Intent(getActivity(), MessageSampleActivity.class);
            startActivity(intent);
        }
    }
}
