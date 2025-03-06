package DAO.factory;

public class MysqlConnectionFactory implements ConnFactorty{
    @Override
    public Conn getConnection() {
        return JMysql.getInstance();
    }
}
