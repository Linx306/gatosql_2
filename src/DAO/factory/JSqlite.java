package DAO.factory;

public class JSqlite implements Conn {

    private static JSqlite instance = new JSqlite();

    @Override
    public void Conectar() {

    }

    @Override
    public void Desconectar() {

    }

    @Override
    public void setErrMsg(String error) {

    }

    @Override
    public String getErrMsg() {
        return "";
    }

    @Override
    public void query(String sql) {

    }

    @Override
    public void add(String estado, String json) {

    }


    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    private JSqlite() {
        System.out.println("Test Mysql ok!");
    }
    public static JSqlite getInstance() {
        return instance;
    }

}
