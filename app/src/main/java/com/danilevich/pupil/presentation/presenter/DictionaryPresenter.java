package com.danilevich.pupil.presentation.presenter;

import android.widget.Toast;

import com.danilevich.pupil.presentation.view.fragment.dictionary.DictionaryView;

import javax.inject.Inject;

public class DictionaryPresenter {

    private DictionaryView dictionaryView;

    @Inject
    public DictionaryPresenter() {
    }

    public void setView(DictionaryView view) {
        this.dictionaryView = view;
    }

    public void init() {
        Toast.makeText(dictionaryView.context(), "inti", Toast.LENGTH_SHORT).show();
    }
}
