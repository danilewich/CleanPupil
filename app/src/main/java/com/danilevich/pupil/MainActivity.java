package com.danilevich.pupil;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.danilevich.pupil.presentation.view.fragment.dictionary.DictionaryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private ActionBar toolbar;
    private int curPageId = R.id.navigation_words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.title_words);

        navigation.setSelectedItemId(R.id.navigation_words);
        this.loadFragment(WordsFragment.newInstance());

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (curPageId == item.getItemId()) {
                return false;
            } else {
                curPageId = item.getItemId();
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        toolbar.setTitle(R.string.title_home);
                        loadFragment(DictionaryFragment.newInstance());
                        return true;
                    case R.id.navigation_words:
                        toolbar.setTitle(R.string.title_words);
                        loadFragment(WordsFragment.newInstance());
                        return true;
                    case R.id.navigation_settings:
                        toolbar.setTitle(R.string.title_settings);
                        loadFragment(Settings.newInstance());
                        return true;
                }
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_id, R.anim.fade_out);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }
}
