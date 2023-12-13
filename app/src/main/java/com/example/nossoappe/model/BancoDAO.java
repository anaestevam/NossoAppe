package com.example.nossoappe.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import com.example.nossoappe.database.*;

public class BancoDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "nossoappe.db";
    private static final int DATABASE_VERSION = 2;
    public BancoDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MORADOR_ENTRIES);
        db.execSQL(SQL_CREATE_GASTO_ENTRIES);
    }
    private static final String SQL_CREATE_MORADOR_ENTRIES =
            "CREATE TABLE " + MoradorTable.MoradorEntry.TABLE_NAME + " (" +
                    MoradorTable.MoradorEntry._ID + " INTEGER PRIMARY KEY," +
                    MoradorTable.MoradorEntry.COLUMN_NOME + " TEXT," +
                    MoradorTable.MoradorEntry.COLUMN_PORCENTAGEM + " REAL)";
    private static final String SQL_CREATE_GASTO_ENTRIES =
            "CREATE TABLE " + GastoTable.GastoEntry.TABLE_NAME + " (" +
                    GastoTable.GastoEntry._ID + " INTEGER PRIMARY KEY," +
                    GastoTable.GastoEntry.COLUMN_NOME + " TEXT," +
                    GastoTable.GastoEntry.COLUMN_VALOR + " REAL," +
                    GastoTable.GastoEntry.COLUMN_PAGO + " INTEGER," +
                    GastoTable.GastoEntry.COLUMN_ID_MORADOR_PAGOU + " INTEGER," +
                    "FOREIGN KEY(" + GastoTable.GastoEntry.COLUMN_ID_MORADOR_PAGOU + ") REFERENCES " +
                    MoradorTable.MoradorEntry.TABLE_NAME + "(" + MoradorTable.MoradorEntry._ID + "))";
    private static final String SQL_DELETE_MORADOR_ENTRIES =
            "DROP TABLE IF EXISTS " + MoradorTable.MoradorEntry.TABLE_NAME;
    private static final String SQL_DELETE_GASTO_ENTRIES =
            "DROP TABLE IF EXISTS " + GastoTable.GastoEntry.TABLE_NAME;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MORADOR_ENTRIES);
        db.execSQL(SQL_DELETE_GASTO_ENTRIES);
        onCreate(db);
    }

 /* INICIO TUDO SOBRE O MORADOR */
    public long salvarMorador(Morador morador) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MoradorTable.MoradorEntry.COLUMN_NOME, morador.getNome());
        values.put(MoradorTable.MoradorEntry.COLUMN_PORCENTAGEM, morador.getPorcentagem());
        long idMoradorInserido = db.insert(MoradorTable.MoradorEntry.TABLE_NAME, null, values);
        if (idMoradorInserido != -1) {
            morador.setId(idMoradorInserido);
        }
        db.close();

        return idMoradorInserido;
    }
    public void atualizarMorador(Morador morador) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MoradorTable.MoradorEntry.COLUMN_PORCENTAGEM, morador.getPorcentagem());
        String selection = MoradorTable.MoradorEntry.COLUMN_NOME + " = ?";
        String[] selectionArgs = {morador.getNome()};
        db.update(
                MoradorTable.MoradorEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        db.close();
    }
    public boolean moradorExiste(String nome) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                MoradorTable.MoradorEntry.TABLE_NAME,
                null,
                MoradorTable.MoradorEntry.COLUMN_NOME + " = ?",
                new String[]{nome},
                null,
                null,
                null
        );
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return existe;
    }
    public void deletarMorador(String nomeMorador) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                MoradorTable.MoradorEntry.TABLE_NAME,
                MoradorTable.MoradorEntry.COLUMN_NOME + " = ?",
                new String[]{nomeMorador}
        );
        db.close();
    }
    public List<Morador> getListaMoradores() {
        List<Morador> listaMoradores = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                MoradorTable.MoradorEntry._ID,
                MoradorTable.MoradorEntry.COLUMN_NOME,
                MoradorTable.MoradorEntry.COLUMN_PORCENTAGEM,
        };
        Cursor cursor = db.query(
                MoradorTable.MoradorEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Morador morador = new Morador();
                morador.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MoradorTable.MoradorEntry._ID)));
                morador.setNome(cursor.getString(cursor.getColumnIndexOrThrow(MoradorTable.MoradorEntry.COLUMN_NOME)));
                morador.setPorcentagem(cursor.getInt(cursor.getColumnIndexOrThrow(MoradorTable.MoradorEntry.COLUMN_PORCENTAGEM)));
                listaMoradores.add(morador);

            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return listaMoradores;
    }
    public long getIdMoradorPorNome(String nomeMorador) {
        SQLiteDatabase db = this.getReadableDatabase();
        long idMorador = -1;
        String[] projection = {MoradorTable.MoradorEntry._ID};
        String selection = MoradorTable.MoradorEntry.COLUMN_NOME + " = ?";
        String[] selectionArgs = {nomeMorador};
        Cursor cursor = db.query(
                MoradorTable.MoradorEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            idMorador = cursor.getLong(cursor.getColumnIndexOrThrow(MoradorTable.MoradorEntry._ID));
            cursor.close();
        }

        db.close();

        return idMorador;
    }

    /* FIM TUDO SOBRE O MORADOR */

    // Modifique a função salvarGasto na sua classe BancoDAO
    public void salvarGasto(Gasto gasto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GastoTable.GastoEntry.COLUMN_NOME, gasto.getNome());
        values.put(GastoTable.GastoEntry.COLUMN_VALOR, gasto.getValor());
        values.put(GastoTable.GastoEntry.COLUMN_PAGO, gasto.isPago() ? 1 : 0);
        values.put(GastoTable.GastoEntry.COLUMN_ID_MORADOR_PAGOU, gasto.getIdMorador());

        db.insert(GastoTable.GastoEntry.TABLE_NAME, null, values);
        db.close();
    }
    public void atualizarGasto(Gasto gasto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GastoTable.GastoEntry.COLUMN_VALOR, gasto.getValor());
        values.put(GastoTable.GastoEntry.COLUMN_PAGO, gasto.isPago() ? 1 : 0);
        values.put(GastoTable.GastoEntry.COLUMN_ID_MORADOR_PAGOU, gasto.getIdMorador());
        String selection = MoradorTable.MoradorEntry.COLUMN_NOME + " = ?";
        String[] selectionArgs = {gasto.getNome()};
        db.update(
                MoradorTable.MoradorEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        db.close();
    }
    public List<Gasto> getListaGastos() {
        List<Gasto> listaGastos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT g.*, g." + GastoTable.GastoEntry.COLUMN_NOME +
                " FROM " + GastoTable.GastoEntry.TABLE_NAME + " g" +
                " INNER JOIN " + MoradorTable.MoradorEntry.TABLE_NAME + " m" +
                " ON g." + GastoTable.GastoEntry.COLUMN_ID_MORADOR_PAGOU + " = m." + MoradorTable.MoradorEntry._ID;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Gasto gasto = new Gasto();
                gasto.setId(cursor.getLong(cursor.getColumnIndexOrThrow(GastoTable.GastoEntry._ID)));
                gasto.setNome(cursor.getString(cursor.getColumnIndexOrThrow(GastoTable.GastoEntry.COLUMN_NOME)));
                gasto.setValor(cursor.getDouble(cursor.getColumnIndexOrThrow(GastoTable.GastoEntry.COLUMN_VALOR)));
                gasto.setPago(cursor.getInt(cursor.getColumnIndexOrThrow(GastoTable.GastoEntry.COLUMN_PAGO)) == 1);

                // Obtém o ID do morador da coluna correspondente na tabela de gastos
                long idMoradorPagou = cursor.getLong(cursor.getColumnIndexOrThrow(GastoTable.GastoEntry.COLUMN_ID_MORADOR_PAGOU));
                gasto.setIdMorador(idMoradorPagou);

                listaGastos.add(gasto);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return listaGastos;
    }

    public Morador getMoradorPorId(long idMorador) {
        SQLiteDatabase db = this.getReadableDatabase();
        Morador morador = null;

        String[] projection = {
                MoradorTable.MoradorEntry._ID,
                MoradorTable.MoradorEntry.COLUMN_NOME,
                MoradorTable.MoradorEntry.COLUMN_PORCENTAGEM,
        };

        String selection = MoradorTable.MoradorEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(idMorador)};

        Cursor cursor = db.query(
                MoradorTable.MoradorEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            morador = new Morador();
            morador.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MoradorTable.MoradorEntry._ID)));
            morador.setNome(cursor.getString(cursor.getColumnIndexOrThrow(MoradorTable.MoradorEntry.COLUMN_NOME)));
            morador.setPorcentagem(cursor.getInt(cursor.getColumnIndexOrThrow(MoradorTable.MoradorEntry.COLUMN_PORCENTAGEM)));
            cursor.close();
        }

        db.close();

        return morador;
    }
    public boolean gastoExiste(String nome) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                GastoTable.GastoEntry.TABLE_NAME,
                null,
                GastoTable.GastoEntry.COLUMN_NOME + " = ?",
                new String[]{nome},
                null,
                null,
                null
        );
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return existe;
    }
    public void deletarGasto(String nomeGasto) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                GastoTable.GastoEntry.TABLE_NAME,
                GastoTable.GastoEntry.COLUMN_NOME + " = ?",
                new String[]{nomeGasto}
        );
        db.close();
    }
    public void atualizarPagamentoGasto(Gasto gasto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("pago", gasto.isPago() ? 1 : 0); // 1 para true, 0 para false

        db.update("gasto", values, "nome = ?", new String[]{gasto.getNome()});
        db.close();
    }

}

