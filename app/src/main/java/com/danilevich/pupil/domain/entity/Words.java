package com.danilevich.pupil.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "WORDS")
public class Words {

    @DatabaseField(columnName = "id", generatedId = true)
    int id;
    @DatabaseField(columnName = "name")
    String name;

    public Words(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
