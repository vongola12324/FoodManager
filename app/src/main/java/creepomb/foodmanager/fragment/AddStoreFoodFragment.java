package creepomb.foodmanager.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import creepomb.foodmanager.R;

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
        /*
        Bundle args = new Bundle();

        fragment.setArguments(args);
        */
        return fragment;
    }

    public AddStoreFoodFragment(int sectionNumber) {
        super(sectionNumber);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {

        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_store_food, container, false);
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
