package com.danilevich.pupil.presentation.di;

import com.danilevich.pupil.presentation.view.fragment.dictionary.DictionaryFragment;

import dagger.Component;

@Component
public interface PupilComponent {

    void inject(DictionaryFragment dictionaryFragment);
}
