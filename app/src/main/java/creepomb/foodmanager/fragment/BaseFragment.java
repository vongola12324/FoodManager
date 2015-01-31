package creepomb.foodmanager.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import creepomb.foodmanager.MainActivity;
import creepomb.foodmanager.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BaseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    protected int sectionNumber;

    protected MainActivity activity;

    //private OnFragmentInteractionListener mListener;

    public static BaseFragment newInstance(int sectionNumber) {
        BaseFragment fragment = new BaseFragment(sectionNumber);

        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    public BaseFragment() {}

    public BaseFragment(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    public void onButtonPressed(Uri uri) {
       /* if (mListener != null) {
            mListener.onFragmentInteraction(this, uri);
        }*/
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
        ((MainActivity) activity).onSectionAttached(sectionNumber);
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
}

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

//    public interface OnFragmentInteractionListener {
//        public void onFragmentInteraction(BaseFragment fragment, Uri uri);
//    }

}
