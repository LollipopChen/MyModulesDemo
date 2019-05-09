package com.example.mymodulesdemo.ui.main.me.collection.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.libbase.BR;
import com.example.libbase.base.BaseFragment;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.FragmentNetAddressBinding;

public class NetAddressFragment extends BaseFragment<FragmentNetAddressBinding,NetAddressViewModel> {

    private static NetAddressFragment instance = null;

    public static synchronized NetAddressFragment getInstance() {
        if (instance == null) {
            instance = new NetAddressFragment();
        }
        return instance;
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_net_address;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
