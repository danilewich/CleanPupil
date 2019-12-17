package com.danilevich.pupil.presentation.presenter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.danilevich.pupil.DBHelper;
import com.danilevich.pupil.domain.entity.Words;
import com.danilevich.pupil.presentation.view.fragment.dictionary.DictionaryView;

import java.util.ArrayList;
import java.util.List;

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
        List<Words> wordsList = new ArrayList<>();

        DBHelper myDBHelper = new DBHelper(this.dictionaryView.context());
        SQLiteDatabase myDB = myDBHelper.getWritableDatabase();

        Cursor c = myDB.rawQuery("SELECT ew.name_eng_word, ew._id_eng_word\n" +
                "  FROM eng_words ew", null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            wordsList.add(new Words(c.getInt(1), c.getString(0)));
            c.moveToNext();
        }
        c.close();
        this.dictionaryView.renderDictionaryList(wordsList);
    }
}
