package com.danilevich.pupil.presentation.view.fragment.dictionary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.danilevich.pupil.R;
import com.danilevich.pupil.presentation.di.DaggerPupilComponent;
import com.danilevich.pupil.presentation.di.PupilComponent;
import com.danilevich.pupil.presentation.presenter.DictionaryPresenter;
import com.danilevich.pupil.presentation.view.adapter.DictionaryAdapter;
import com.danilevich.pupil.presentation.view.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DictionaryFragment extends BaseFragment implements DictionaryView {

    private Unbinder unbinder;

    @Inject
    DictionaryPresenter dictionaryPresenter;
    @Inject
    DictionaryAdapter dictionaryAdapter;

    public DictionaryFragment() {
        // Required empty public constructor
    }

    public static DictionaryFragment newInstance() {
        return new DictionaryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializationDi();
    }

    private void initializationDi() {
        PupilComponent component = DaggerPupilComponent.builder().build();
        component.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dictionaryPresenter.setView(this);

        dictionaryPresenter.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dictionary, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void showError(String message) {

    }
}
