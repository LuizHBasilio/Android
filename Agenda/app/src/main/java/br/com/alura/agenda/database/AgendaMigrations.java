package br.com.alura.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE contato ADD COLUMN sobrenome TEXT");
        }
    };
    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Criar nova tabela com as informações desejadas
            database.execSQL("CREATE TABLE IF NOT EXISTS `contato_novo` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefone` TEXT, " +
                    "`email` TEXT)");
            // Copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO contato_novo (id, nome, telefone, email) " +
                    "SELECT id, nome, telefone, email FROM contato");
            // Remove tabela antiga
            database.execSQL("DROP TABLE contato");
            // Renomear a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE contato_novo RENAME TO contato");
        }
    };
    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE contato ADD COLUMN momentoDeCadastro INTEGER");
        }
    };
    static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4};
}
