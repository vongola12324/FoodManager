package creepomb.foodmanager.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;


import java.util.List;
import java.lang.Object;

import creepomb.foodmanager.R;
import creepomb.foodmanager.util.IconManager;
import creepomb.foodmanager.util.StorageLocationItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class StorageLocationFragment extends BaseFragment implements AbsListView.OnItemClickListener {

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    public static StorageLocationFragment newInstance(int sectionNumber) {
        StorageLocationFragment fragment = new StorageLocationFragment(sectionNumber);

        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    public StorageLocationFragment() {}

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StorageLocationFragment(int sectionNumber) {
        super(sectionNumber);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new StorageLocationAdapter(getActivity(), R.layout.storage_location_item, android.R.id.text1, StorageLocationContent.readFormDB());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_storagelocation, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object obj = parent.getAdapter().getItem(position);
        if (obj instanceof StorageLocationItem) {
            long SL_id = ((StorageLocationItem) obj).id;
            Log.i("Food", String.valueOf(SL_id));
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public static class StorageLocationAdapter extends ArrayAdapter<StorageLocationItem>{

        public StorageLocationAdapter(Context context, int resource, int textViewResourceId, List<StorageLocationItem> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position

            StorageLocationItem storageLocation = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.storage_location_item, parent, false);
            }
            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.textView);
            ImageView ivName = (ImageView) convertView.findViewById(R.id.imageView);
            // Populate the data into the template view using the data object
            tvName.setText(storageLocation.name);
            IconManager.setIconSrc(ivName, storageLocation.iconIndex);
            // Return the completed view to render on screen
            return convertView;
        }
    }

}
