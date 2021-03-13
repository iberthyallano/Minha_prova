package pt.apt.minhaprova.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class LivroDBOpener (context : Context) : SQLiteOpenHelper(context, LivroContrato.DATABASE_NAME, null, LivroContrato.DATA_BASE_VERSION){
    val TAG = "sql"
    val SQL_CREATE_TABLE =
            "CREATE TABLE ${LivroContrato.LivroEntry.TABLE_NAME}" +
                    "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                    " ${LivroContrato.LivroEntry.TITULO_LIVRO} TEXT," +
                    " ${LivroContrato.LivroEntry.AUTOR} TEXT," +
                    " ${LivroContrato.LivroEntry.ANO} INTEGER," +
                    " ${LivroContrato.LivroEntry.NOTA} REAL" +
                    ")"
    val SQL_DROP_TABLE =
            "DROP TABLE ${LivroContrato.LivroEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL(SQL_DROP_TABLE)
            db.execSQL(SQL_CREATE_TABLE)
        }
    }
    /*
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    */
    fun insert (livro:Livro){
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(LivroContrato.LivroEntry.TITULO_LIVRO, livro.titulo)
            values.put(LivroContrato.LivroEntry.AUTOR, livro.autor)
            values.put(LivroContrato.LivroEntry.ANO, livro.ano)
            values.put(LivroContrato.LivroEntry.NOTA, livro.nota)

            banco.insert(LivroContrato.LivroEntry.TABLE_NAME, null, values)

        }finally {
            banco.close()
        }
    }

    fun findById(id:Int): Livro{
        var banco: SQLiteDatabase = readableDatabase
        try{

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${id}")
            val cursor: Cursor = banco.query(LivroContrato.LivroEntry.TABLE_NAME, null, selection, whereArgs, null, null, null, null)

            cursor.moveToFirst()

            var livro = Livro()

            livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            livro.titulo = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.TITULO_LIVRO))
            livro.autor = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
            livro.ano = cursor.getInt(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))
            livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))

            return livro

        }finally {
            banco.close()
        }
    }
    fun findAll(): ArrayList<Livro>{
        var banco: SQLiteDatabase = readableDatabase
        try{

            val cursor: Cursor = banco.query(LivroContrato.LivroEntry.TABLE_NAME, null, null, null, null, null, null, null)

            var listaLivros = ArrayList<Livro>()

            while( cursor.moveToNext()){
                var livro = Livro()

                livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                livro.titulo = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.TITULO_LIVRO))
                livro.autor = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
                livro.ano = cursor.getInt(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))
                livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))

                listaLivros.add(livro)
            }

            return listaLivros

        }finally {
            banco.close()
        }
    }
}