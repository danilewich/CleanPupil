package com.danilevich.pupil.domain.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "WORDS")
public class Words {

    @DatabaseField(columnName = "id", generatedId = true)
    int id;
    @DatabaseField(columnName = "name")
    String name;

}
