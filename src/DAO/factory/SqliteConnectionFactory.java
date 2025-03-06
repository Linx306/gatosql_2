package DAO.factory;

public class SqliteConnectionFactory implements ConnFactorty{
    @Override
    public Conn getConnection() {
        return JSqlite.getInstance();
    }
}
