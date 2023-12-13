package com.example.nossoappe.database;

import android.provider.BaseColumns;

public class GastoTable {

    public static class GastoEntry implements BaseColumns {
        public static final String TABLE_NAME = "gasto";
        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_VALOR = "valor";
        public static final String COLUMN_PAGO = "pago";
        public static final String COLUMN_ID_MORADOR_PAGOU = "id_morador_pagou";

    }
}

