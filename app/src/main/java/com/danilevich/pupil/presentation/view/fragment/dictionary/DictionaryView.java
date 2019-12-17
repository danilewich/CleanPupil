package com.danilevich.pupil.presentation.view.fragment.dictionary;

import com.danilevich.pupil.domain.entity.Words;
import com.danilevich.pupil.presentation.view.BaseView;

import java.util.Collection;

public interface DictionaryView extends BaseView {

    void renderDictionaryList(Collection<Words> wordsList);
}
