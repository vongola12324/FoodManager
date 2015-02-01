package creepomb.foodmanager.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import junit.framework.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import creepomb.foodmanager.R;

import creepomb.foodmanager.util.FoodItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class FoodListFragment extends BaseFragment implements AbsListView.OnItemClickListener {

    public static String titleName = "";

    private AbsListView mListView;

    private ListAdapter mAdapter;

    protected List<FoodItem> items;

    public static FoodListFragment newInstance(int sectionNumber, List<FoodItem> items) {
        FoodListFragment fragment = new FoodListFragment(sectionNumber);

        fragment.items = items;

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    public FoodListFragment() {

    }

    public FoodListFragment(int sectionNumber) {
        super(sectionNumber);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new FoodItemAdapter(getActivity(),
                R.layout.food_item, android.R.id.text1, items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foodlist, container, false);

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
    
    public static class FoodItemAdapter extends ArrayAdapter<FoodItem>{

        public FoodItemAdapter(Context context, int resource, int textViewResourceId, List<FoodItem> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            FoodItem fooditem = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_item, parent, false);
            }

            TextView tv_name = (TextView) convertView.findViewById(R.id.item_name);
            TextView tv_count = (TextView) convertView.findViewById(R.id.item_count);
            TextView tv_time = (TextView) convertView.findViewById(R.id.item_time);

            tv_name.setText(fooditem.getName());
            tv_count.setText(fooditem.getAmount() + fooditem.getUnit());
            tv_time.setText(FoodItem.getDisplay(fooditem.getOutDated()));

            return convertView;
        }
    }

}
