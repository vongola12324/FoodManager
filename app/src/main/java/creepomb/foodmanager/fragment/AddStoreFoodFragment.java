package creepomb.foodmanager.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import creepomb.foodmanager.MainActivity;
import creepomb.foodmanager.R;
import creepomb.foodmanager.util.Category;
import creepomb.foodmanager.util.FoodItem;
import creepomb.foodmanager.util.StorageLocationItem;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddStoreFoodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddStoreFoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddStoreFoodFragment extends BaseFragment implements View.OnClickListener {

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

    public AddStoreFoodFragment() {
    }

    public AddStoreFoodFragment(int sectionNumber) {
        super(sectionNumber);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_store_food, container, false);
        //-----------------------------------------------------------------------------------------------------------------------------------
        Spinner categories_Spinner = (Spinner) view.findViewById(R.id.category_Spinner);
        List<Category> categories = MainActivity.dbCategoryProcess.getAll();
        List<String> categoryNames = new ArrayList<String>();

        for (Category cate : categories) {
            categoryNames.add(cate.getName());
        }
        categories_Spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categoryNames));
        //-----------------------------------------------------------------------------------------------------------------------------------
        Spinner unit_Spinner = (Spinner) view.findViewById(R.id.unit_Spinner);

        List<String> units = new ArrayList<String>();
        units.add("個");
        units.add("隻");
        units.add("根");
        units.add("把");
        units.add("顆");
        units.add("粒");
        units.add("條");
        units.add("尾");
        units.add("瓶");
        units.add("罐");
        units.add("包");


        unit_Spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, units));
        //-----------------------------------------------------------------------------------------------------------------------------------
        Spinner outDated_years_Spinner = (Spinner) view.findViewById(R.id.outDated_Year_Spinner);
        Spinner outDated_months_Spinner = (Spinner) view.findViewById(R.id.outDated_Month_Spinner);
        Spinner outDated_days_Spinner = (Spinner) view.findViewById(R.id.outDated_Day_Spinner);

        List<String> outDatedYears = new ArrayList<String>();
        List<String> outDatedMonths = new ArrayList<String>();
        List<String> outDatedDays = new ArrayList<String>();

        for (int i = 2065; i >= 1970; i--) {
            outDatedYears.add((new Integer(i)).toString());
        }
        for (int i = 1; i <= 12; i++) {
            outDatedMonths.add((new Integer(i)).toString());
        }
        for (int i = 1; i <= 31; i++) {
            outDatedDays.add((new Integer(i)).toString());
        }

        outDated_years_Spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, outDatedYears));
        outDated_months_Spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, outDatedMonths));
        outDated_days_Spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, outDatedDays));
        //-----------------------------------------------------------------------------------------------------------------------------------
        Spinner storageLocation_Spinner = (Spinner) view.findViewById(R.id.storedLoc_Spinner);
        List<String> storageLocation = new ArrayList<>();

        for (StorageLocationItem item : MainActivity.dbStorageLocationItemsProcess.getAll()) {
            storageLocation.add(item.name);
        }

        storageLocation_Spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, storageLocation));
        //-----------------------------------------------------------------------------------------------------------------------------------

        Button buttonOK = (Button) view.findViewById(R.id.newFood_Button);
        buttonOK.setOnClickListener(this);

        Button buttonCancel = (Button) view.findViewById(R.id.clear_Button);
        buttonCancel.setOnClickListener(this);

        //-----------------------------------------------------------------------------------------------------------------------------------
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

    @Override
    public void onClick(View v) {
        int vid = v.getId();
        if (vid == R.id.newFood_Button) {
            EditText name_EditText = (EditText) this.getView().findViewById(R.id.name_EditText);
            EditText amount_EditText = (EditText) this.getView().findViewById(R.id.amount_EditText);
            Spinner unit_Spinner = (Spinner) this.getView().findViewById(R.id.unit_Spinner);
            Spinner categories_Spinner = (Spinner) this.getView().findViewById(R.id.category_Spinner);

            Spinner outDated_years_Spinner = (Spinner) this.getView().findViewById(R.id.outDated_Year_Spinner);
            Spinner outDated_months_Spinner = (Spinner) this.getView().findViewById(R.id.outDated_Month_Spinner);
            Spinner outDated_days_Spinner = (Spinner) this.getView().findViewById(R.id.outDated_Day_Spinner);

            Spinner storageLocation_Spinner = (Spinner) this.getView().findViewById(R.id.storedLoc_Spinner);

            MainActivity.dbFoodItemsProcess.insert(new FoodItem(
                    name_EditText.getText().toString(),
                    Integer.parseInt(amount_EditText.getText().toString()),
                    (String)(unit_Spinner.getSelectedItem()),
                    (int)categories_Spinner.getSelectedItemId(),
                    FoodItem.getDateInMilli(
                            Integer.parseInt((String)(outDated_years_Spinner.getSelectedItem())),
                            Integer.parseInt((String)(outDated_months_Spinner.getSelectedItem())),
                            Integer.parseInt((String)(outDated_days_Spinner.getSelectedItem()))
                    ),
                    (int)(storageLocation_Spinner.getSelectedItemId() + 1)
            ));

            Toast.makeText(v.getContext(), "新增完成", Toast.LENGTH_SHORT).show();
            resetAllTestBox();
        }
        else if (vid == R.id.clear_Button) {
            resetAllTestBox();
        }
    }

    public void resetAllTestBox() {
        EditText name_EditText = (EditText) this.getView().findViewById(R.id.name_EditText);
        EditText amount_EditText = (EditText) this.getView().findViewById(R.id.amount_EditText);
        Spinner unit_Spinner = (Spinner) this.getView().findViewById(R.id.unit_Spinner);
        Spinner categories_Spinner = (Spinner) this.getView().findViewById(R.id.category_Spinner);

        Spinner outDated_years_Spinner = (Spinner) this.getView().findViewById(R.id.outDated_Year_Spinner);
        Spinner outDated_months_Spinner = (Spinner) this.getView().findViewById(R.id.outDated_Month_Spinner);
        Spinner outDated_days_Spinner = (Spinner) this.getView().findViewById(R.id.outDated_Day_Spinner);

        Spinner storageLocation_Spinner = (Spinner) this.getView().findViewById(R.id.storedLoc_Spinner);

        name_EditText.setText("");
        amount_EditText.setText("");
        unit_Spinner.setSelection(0);
        categories_Spinner.setSelection(0);
        outDated_years_Spinner.setSelection(0);
        outDated_months_Spinner.setSelection(0);
        outDated_days_Spinner.setSelection(0);
        storageLocation_Spinner.setSelection(0);

    }

}
