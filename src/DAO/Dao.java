package DAO;

import DAO.factory.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mysql.cj.MysqlConnection;

public class Dao {
    private ConnFactorty connFactory; //objeto Patter demo
    private Conn conexion;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private String errMsg;


    private ConnFactorty getConnFactorty(Database db) {
        switch (db){
            case JMysql -> {
                return new MysqlConnectionFactory();
            }
            case JSqlite -> {
                return new SqliteConnectionFactory();
            }
            default-> {
                return new FirebaseConnection();
            }
        }
    }//end ConnFactory
    private void start(){
        try {
            conexion = connFactory.getConnection();
            conexion.Conectar();
           // conexion.Desconectar();
        }catch(Exception e){
            setErrMsg(conexion.getErrMsg());
        }
    }
    private void close(){
        try {
            conexion.Desconectar();
        } catch (Exception e) {
            setErrMsg(conexion.getErrMsg());
        }
    }

    public void add(String estado,char[][]tablero){
        try {
            estado = "Turno"+estado;
            Gson gson = new Gson();
            JsonArray json = gson.toJsonTree(tablero).getAsJsonArray();
            conexion.add(estado,gson.toJson(json).toString());
        } catch (Exception e) {
            setErrMsg(e.getMessage());
        }
    }

    public void showData(){
        try {
            conexion.query("");
        } catch (Exception e) {
            setErrMsg(conexion.getErrMsg());
        }
    }

    public Dao (Database db){
        connFactory = getConnFactorty(db);
        start();
        
    }
    public Dao(){
        this(Database.JMysql);
    }

}
