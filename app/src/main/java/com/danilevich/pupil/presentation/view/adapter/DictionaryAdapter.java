package com.danilevich.pupil.presentation.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danilevich.pupil.R;
import com.danilevich.pupil.domain.entity.Words;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder>
        implements Filterable {

    private List<Words> wordsList;
    private List<Words> wordsListFiltered;

    @Inject
    DictionaryAdapter() {
        wordsList = Collections.emptyList();
    }

    public void setWordsList(Collection<Words> wordsList) {
        this.validateWordsList(wordsList);
        this.wordsList = (List<Words>) wordsList;
        this.notifyDataSetChanged(); // ???
    }

    private void validateWordsList(Collection<Words> wordsList) {
        if (wordsList == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_dictionary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Words words = this.wordsList.get(position);
        holder.twWordName.setText(words.getName());
    }

    @Override
    public int getItemCount() {
        return (wordsList.isEmpty()) ? 0 : wordsList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString .isEmpty()) {
                    wordsListFiltered = wordsList;
                } else {
                    List<Words> filteredList = new ArrayList<>();
                    for (Words row : wordsList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    wordsListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = wordsListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                wordsListFiltered = (ArrayList<Words>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tw_word_name)
        TextView twWordName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
