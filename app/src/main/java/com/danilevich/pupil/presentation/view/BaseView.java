package com.danilevich.pupil.presentation.view;

import android.content.Context;

public interface BaseView {

    Context context();

    void showError(String message);
}
