package com.example.aplicacionsemestral.esquemas;

import android.provider.BaseColumns;

public class User {
    public static abstract class Esquema implements BaseColumns{
        public static final String TABLA_NAME = "usuario";

        public static final String ID = "id_usuario";

        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String CONTRASEÑA = "contraseña";
        public static final String EMAIL = "email";
        public static final String GENERO = "genero";

        public static final String[] ALLCOLUMNAS = {ID,NOMBRE,APELLIDO,CONTRASEÑA,EMAIL,GENERO};

        public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            NOMBRE+" TEXT,"+APELLIDO+" TEXT,"+CONTRASEÑA+" TEXT,"+EMAIL+" TEXT,"+GENERO+" TEXT)";

        public static final String BORRAR_TABLA_USUARIO = "DROP TABLE IF EXISTS "+TABLA_NAME;
    }
}
