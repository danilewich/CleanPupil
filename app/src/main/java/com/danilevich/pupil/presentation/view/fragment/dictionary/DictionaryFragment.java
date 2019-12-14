package com.danilevich.pupil.presentation.view.dictionary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.danilevich.pupil.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DictionaryFragment extends Fragment {

    private Unbinder unbinder;

    @Inject
    private DictionaryPresenter dictionaryPresenter;

    public DictionaryFragment() {
        // Required empty public constructor
    }

    public static DictionaryFragment newInstance() {
        return new DictionaryFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);
        unbinder = ButterKnife.bind(this, view);

        dictionaryPresenter.init();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
