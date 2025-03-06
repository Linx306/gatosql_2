package Negocio;

import DAO.Dao;
import Presentacion.Observer.Observer;
import Presentacion.Observer.Tablero;

public class LogDb implements Observer {

    private Dao accesodatos;
    private String errMesg;

    public String getErrMesg() {
        return errMesg;
    }

    public void setErrMesg(String errMesg) {
        this.errMesg = errMesg;
    }

    public LogDb(Dao accesodatos) {
        this.accesodatos = accesodatos;
    }

    public LogDb() {
        this(new Dao());
    }

    @Override
    public void update(Tablero tablero){
        try {
            accesodatos.add(tablero.getTurno(), tablero.getTablero());
        } catch (Exception e) {
            setErrMesg(accesodatos.getErrMsg());
        }


    }
    public void add(){

    }
    public void showRun(){
        try {
            accesodatos.showData();
        } catch (Exception e) {
            setErrMesg(accesodatos.getErrMsg());
        }
    }
}
