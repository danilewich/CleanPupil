package com.danilevich.pupil.presentation.view.fragment.dictionary;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.danilevich.pupil.R;
import com.danilevich.pupil.domain.entity.Words;
import com.danilevich.pupil.presentation.di.DaggerPupilComponent;
import com.danilevich.pupil.presentation.di.PupilComponent;
import com.danilevich.pupil.presentation.presenter.DictionaryPresenter;
import com.danilevich.pupil.presentation.view.adapter.DictionaryAdapter;
import com.danilevich.pupil.presentation.view.adapter.DictionaryLayoutManager;
import com.danilevich.pupil.presentation.view.adapter.MyDividerItemDecoration;
import com.danilevich.pupil.presentation.view.fragment.BaseFragment;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DictionaryFragment extends BaseFragment implements DictionaryView {

    private Unbinder unbinder;

    @Inject
    DictionaryPresenter dictionaryPresenter;
    @Inject
    DictionaryAdapter dictionaryAdapter;

    @BindView(R.id.rv_dictionary)
    RecyclerView rvDictionary;

    public DictionaryFragment() {
        // Required empty public constructor
    }

    public static DictionaryFragment newInstance() {
        return new DictionaryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        setupRecyclerView();

        return view;
    }

    private void setupRecyclerView() {
        rvDictionary.setLayoutManager(new DictionaryLayoutManager(context()));
        rvDictionary.setAdapter(dictionaryAdapter);
        rvDictionary.addItemDecoration(new MyDividerItemDecoration(context(), DividerItemDecoration.VERTICAL, 36));
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

    @Override
    public void renderDictionaryList(Collection<Words> wordsList) {
        if (wordsList != null) {
            this.dictionaryAdapter.setWordsList(wordsList);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_main, menu);

        SearchManager searchManager = (SearchManager) context().getSystemService(Context.SEARCH_SERVICE);
        MenuItem item = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dictionaryAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dictionaryAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
