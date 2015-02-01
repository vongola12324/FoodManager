package creepomb.foodmanager.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import creepomb.foodmanager.MainActivity;
import creepomb.foodmanager.R;
import creepomb.foodmanager.util.Category;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddStoreFoodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddStoreFoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddStoreFoodFragment extends BaseFragment {

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddStoreFoodFragment.
     */
    public static AddStoreFoodFragment newInstance(int sectionNumber) {
        AddStoreFoodFragment fragment = new AddStoreFoodFragment(sectionNumber);

        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    public AddStoreFoodFragment() {}

    public AddStoreFoodFragment(int sectionNumber) {
        super(sectionNumber);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_store_food, container, false);

        Spinner categories_Spinner = (Spinner) view.findViewById(R.id.category_Spinner);
        List<Category> categories = MainActivity.dbCategoryProcess.getAll();
        List<String> categoryNames = new ArrayList<String>();

        for (Category cate: categories) {
            categoryNames.add(cate.getName());
        }

        categories_Spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categoryNames));

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
