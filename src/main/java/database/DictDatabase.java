package database;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DictDatabase {
    private Connection connection;
    private String connectionUrl;
    public DictDatabase(String name){
        try{
            boolean newFile = false;
            if(!Files.exists(Path.of(name))){
                newFile = true;
            }
            connectionUrl = "jdbc:sqlite:" + name +".db";
            connection = DriverManager.getConnection(connectionUrl);
            if(newFile){
                create();
            }
            connection.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void create(){
        try {
            connection = DriverManager.getConnection(connectionUrl);
            Statement stmt = null;
            stmt = connection.createStatement();
            String sql = "CREATE TABLE \"dictionaries\" (\n" +
                    "\t\"dict_key\"\tINTEGER,\n" +
                    "\t\"dict_name\"\tTEXT UNIQUE,\n" +
                    "\tPRIMARY KEY(\"dict_key\")\n" +
                    ");CREATE TABLE \"values\" (\n" +
                    "\t\"val_id\"\tINTEGER,\n" +
                    "\t\"word_id\"\tINTEGER,\n" +
                    "\t\"value\"\tTEXT,\n" +
                    "\tPRIMARY KEY(\"val_id\" AUTOINCREMENT)\n" +
                    ")";
            stmt.execute(sql);
            sql = "CREATE TABLE \"key_words\" (\n" +
                    "\t\"keyword_key\"\tINTEGER,\n" +
                    "\t\"word\"\tTEXT NOT NULL UNIQUE,\n" +
                    "\t\"dict\"\tINTEGER NOT NULL,\n" +
                    "\tPRIMARY KEY(\"keyword_key\" AUTOINCREMENT)\n" +
                    ")";
            stmt.execute(sql);
            sql = "CREATE TABLE \"values\" (\n" +
                    "\t\"val_id\"\tINTEGER,\n" +
                    "\t\"word_id\"\tINTEGER,\n" +
                    "\t\"value\"\tTEXT,\n" +
                    "\tPRIMARY KEY(\"val_id\" AUTOINCREMENT)\n" +
                    ")";
            stmt.execute(sql);
            stmt.execute("INSERT into dictionaries (dict_name) values (\"hi\")");
            stmt.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }
    public void insertWord(String dict, String key , List<String> values) throws SQLException {
        List<String> sqls = new ArrayList<>();
        int id = executeUpdate(String.format("INSERT into key_words(word, dict) values (\"%s\", \"%s\")", key, dict));
        for (String val: values
             ) {
            sqls.add(String.format("INSERT into \"values\" (word_id, value) values (\"%d\", \"%s\")", id, val));
        }
        executeQuery(sqls);
    }

    public boolean executeQuery(List<String> sqls) throws SQLException {
        connection = DriverManager.getConnection(connectionUrl);
        Statement stmt = null;
        stmt = connection.createStatement();

        for (String query: sqls
             ) {
            if(!stmt.execute(query)){
                stmt.close();
                connection.close();
                return false;
            }
        }
        stmt.close();
        connection.close();
        return true;
    }
    public int executeUpdate(String query) throws SQLException {
        connection = DriverManager.getConnection(connectionUrl);
        Statement stmt = null;
        stmt = connection.createStatement();


        stmt.close();
        connection.close();
        return stmt.executeUpdate(query);

    }
}
