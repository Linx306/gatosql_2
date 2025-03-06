package DAO.factory;

public interface Conn {
    void Conectar();
    void Desconectar();
    void setErrMsg(String error);
    String getErrMsg();

    void query(String sql);
    void add(String estado, String json);
    void update();
    void delete();

}
