package com.example.livedataandviewmodeldemo.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.livedataandviewmodeldemo.R;
import com.example.livedataandviewmodeldemo.base.BaseFragment;
import com.example.livedataandviewmodeldemo.viewmodel.StudentViewModel;

public class FragmentOne extends BaseFragment<StudentViewModel> {
    TextView tv1;
    StudentViewModel studentViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fm1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        tv1 = view.findViewById(R.id.tv1);
        studentViewModel = ViewModelProviders.of(getActivity()).get(StudentViewModel.class);
//        tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                viewModel.getData();
//            }
//        });

        viewModel.gettMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                tv1.setText(String.valueOf(System.currentTimeMillis())+s);
            }
        });
    }

}
