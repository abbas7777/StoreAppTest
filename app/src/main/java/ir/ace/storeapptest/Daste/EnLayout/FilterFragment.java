package ir.ace.storeapptest.Daste.EnLayout;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ir.ace.storeapptest.Models.ValuesItem;
import ir.ace.storeapptest.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FilterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilterFragment newInstance(String param1, String param2) {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    List<ValuesItem> valuesItemList;
    RecyclerView rvValuesItem;
    ImageView imvBack;
    RvFilterValuesAdapter rvFilterValuesAdapter;
    OnValuesList onValuesList;
    int postionMain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_filter, container, false);
        valuesItemList = getArguments().getParcelableArrayList("List");
        postionMain = getArguments().getInt("p");
        setUpViews();

        setData();
        return view;
    }


    private void setUpViews() {
        imvBack = view.findViewById(R.id.imv_FilterFragment_back);
        rvValuesItem = view.findViewById(R.id.rv_FilterFragment_values);

        rvValuesItem.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(FilterFragment.this).commit();

            }
        });
    }

    private void setData() {
        rvFilterValuesAdapter = new RvFilterValuesAdapter(getActivity(), valuesItemList, new RvFilterValuesAdapter.OnFilterValueItemClick() {
            @Override
            public void onClick(String title, int p) {
                ValuesItem valuesItem = new ValuesItem();
                valuesItem.setTitle(title);
                valuesItem.setCheck(true);
                valuesItemList.set(p, valuesItem);
                onValuesList.onClick(valuesItemList,postionMain);

            }
        });


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rvValuesItem.setAdapter(rvFilterValuesAdapter);

            }
        });
    }

    public interface OnValuesList {
        void onClick(List<ValuesItem> valuesItemList,int p);
    }

    public void setCustomEventListener(OnValuesList eventListener) {
        this.onValuesList = eventListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        onValuesList = (OnValuesList) context;
    }
}