package DAO.factory;

import java.sql.*;

public class JMysql implements Conn{

    private static JMysql instance = new JMysql();
    private String errMsg;
    private Connection connection;

    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void Conectar() {
        try{
            //Tenemos que registrar si la clase existe
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Conectar si la clase existe
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8989/tiktaktoe","root","root"
            );
            boolean valid = connection.isValid(5000);
            System.out.println(valid?"mysql Ok!":"mysql Error");

        } catch(java.sql.SQLException sql){
            setErrMsg(sql.getMessage());
        } catch (ClassNotFoundException e) {
            setErrMsg(e.getMessage());
        }

    }

    @Override
    public void Desconectar() {
        try{
            if(!connection.equals(null)){
                if (!connection.isClosed()) {
                    connection.close();
                }
            }
        }catch (SQLException err){

        }
    }

    @Override
    public void setErrMsg(String error) {
    errMsg = error;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public void query(String sql) {
        sql="select id,marcador,tablero from tablero where id=?";
        try (
                PreparedStatement smtp = connection.prepareStatement(sql);

                ){
            if(id !=-1) {
                smtp.setInt(1, getId());
                ResultSet rs = smtp.executeQuery();

                while (rs.next()) {
                    int _id = rs.getInt("id");
                    String marcador = rs.getString("marcador");
                    String json = rs.getString("tablero");
                    System.out.printf("id = %d Marcador %s Tablero %s",id,marcador,json);
                }
            }
        } catch (SQLException e) {
            setErrMsg(e.getMessage());
        }

    }

    @Override
    public void add(String estado, String json) {
        int id = 0;
        String sql = "insert into tablero (fecha,marcador,tablero) values(now(),?,?)";
        try (
                PreparedStatement smtp = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
                ){
            smtp.setString(1, estado);
            smtp.setString(2, json);
            smtp.executeUpdate();

            ResultSet rs = smtp.getGeneratedKeys();
            if(rs.next()){
                setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            setErrMsg(e.getMessage());
        }
    }


    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    private JMysql() {
        this.id = -1;
        System.out.println("Test Mysql ok!");
    }
    public static JMysql getInstance() {
        return instance;
    }
}
