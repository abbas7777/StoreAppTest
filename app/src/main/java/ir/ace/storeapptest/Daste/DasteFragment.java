package ir.ace.storeapptest.Daste;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Daste.EnLayout.EnLayoutActivity;
import ir.ace.storeapptest.Models.CatsItem;
import ir.ace.storeapptest.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DasteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DasteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DasteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DasteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DasteFragment newInstance(String param1, String param2) {
        DasteFragment fragment = new DasteFragment();
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
    RecyclerView rvDaste;
    DatseViewModel datseViewModel=new DatseViewModel();
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_daste, container, false);
        }
        setUpViews();
        getDaste();
        return view;
    }

    private void getDaste() {
        datseViewModel.getCats().observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CatsItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<CatsItem> catsItemList) {

                        rvDaste.setAdapter(new RvDasteAdapter(getActivity(), catsItemList, new RvDasteAdapter.OnCLickDasteItem() {
                            @Override
                            public void onClick(String title) {
                                Intent i=new Intent(getActivity(), EnLayoutActivity.class);
                                i.putExtra("title",title);
                                getActivity().startActivity(i);
                            }
                        }));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("ACE", "onError: "+e );
                    }
                });
    }

    private void setUpViews() {
        rvDaste=view.findViewById(R.id.rv_DasteFragment_cats);
        rvDaste.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public void onDetach() {
        super.onDetach();
        compositeDisposable.dispose();
    }
}