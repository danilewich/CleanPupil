package com.danilevich.pupil.presentation.view.fragment;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.danilevich.pupil.presentation.di.HasComponent;

public abstract class BaseFragment extends Fragment {

    protected void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
