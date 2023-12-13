package com.example.nossoappe.database;

import android.provider.BaseColumns;

public class MoradorTable {
    public static class MoradorEntry implements BaseColumns {
        public static final String TABLE_NAME = "moradores";
        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_PORCENTAGEM = "porcentagem";
    }
}
