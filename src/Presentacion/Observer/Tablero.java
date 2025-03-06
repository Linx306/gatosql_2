package Presentacion.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static Presentacion.utilis.Utilis.leerEntero;

public class Tablero {
    private List<Observer> chismosos;

    private char[][] tablero;
    private int turnos ;
    private char ganador ;

    private String turno;

    public char[][] getTablero() {
        return tablero;
    }
    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Tablero() {
        this.chismosos = new ArrayList<>();
        this.tablero = new char[3][3];
        Arrays.fill(tablero[0],'_');
        Arrays.fill(tablero[1],'_');
        Arrays.fill(tablero[2],'_');
        this.ganador = ' ';
        this.turnos = 1;

    }
    public void attach(Observer observer){
        chismosos.add(observer);
    }

    public void Deattach(Observer observer){
        chismosos.remove(observer);
    }

    public void notificar(){
        for (Observer observer : chismosos) {
            observer.update(this);
        }
    }
    public void play(){
        Random random = new Random();;
        boolean jugando_cpu = random.nextBoolean();
        int ren=0,col=0;

        imprimirTablero(tablero,turnos);
        do {
            if(jugando_cpu){
                do{
                    ren = random.nextInt(0,3);
                    col = random.nextInt(0,3);
                }while (tablero[ren][col] == 'X' || tablero[ren][col] == '0');
                tablero[ren][col] = '0';
                setTurno("0");//jugando el cpu
                notificar();
            }else {
                do {
                    ren = leerEntero("renglon");
                    col = leerEntero("columna");
                    if (tablero[ren][col] != '_') {
                        System.out.printf("El tablero ya esta ocupado\n");
                        imprimirTablero(tablero,turnos);
                    } else {
                        tablero[ren][col] = 'X';
                        setTurno("X");
                        notificar();
                        break;
                    }

                    //}while (tablero[ren][col] != 'X' && tablero[ren][col] != '0');
                }while (true);
            }
            imprimirTablero(tablero,turnos);


            jugando_cpu = !jugando_cpu;
        }while(turnos <= 9 && ganador == ' ');
        winner();
        if (ganador == ' '){
            System.out.println("nadie gano");
        }else
            System.out.println("Gano: "+ganador);
    }

    private char obtenerGanador(char [][] tablero){
        char ficha= ' ';
        for (int i=0;i<3;i++){
            if(sonIguales( tablero [i][0], tablero[i][1], tablero[i][2])){
                ficha = tablero[i][0];
            }
            if(sonIguales( tablero [0][i], tablero[1][i], tablero[2][i])){
                ficha = tablero[0][i];
            }
        }
        if(sonIguales( tablero [0][0], tablero[1][1], tablero[2][2])){
            ficha = tablero[0][0];
        }
        if(sonIguales( tablero [0][2], tablero[1][1], tablero[2][0])){
            ficha = tablero[0][2];
        }

        return ficha;
    }
    private boolean sonIguales(char a,char b, char c){
        if(a=='_' || b=='_'||c=='_')
            return false;
        return  a == b && b == c;
    }
    public void imprimirTablero(char[][] tablero,int turno){
        System.out.println("Turno ="+turno);
        System.out.printf("   1  2  3 \n");
        for (int i = 0; i < tablero.length; i++) {
            System.out.printf("%d", (i+1));
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.printf("  %c", tablero[i][j]);
            }
            System.out.println("");
        }
    }
    public void winner(){
        if(turnos++ >= 5){
            ganador = obtenerGanador(tablero);
        }
    }
}
