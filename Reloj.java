import java.awt.Color;
import java.awt.Graphics;
import javax.swing.border.EmptyBorder;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

public class Reloj extends JFrame implements KeyListener{ 
    private int tam =700;
 
    private JPanel contentPane;

    public ArrayList<Integer> circulosx= new ArrayList<Integer>();
    public ArrayList<Integer> circulosy= new ArrayList<Integer>();

    public ArrayList<Integer> radios= new ArrayList<Integer>();
    public ArrayList<Integer> origencx= new ArrayList<Integer>();
    public ArrayList<Integer> origency= new ArrayList<Integer>();

    public ArrayList<Integer> lineasx= new ArrayList<Integer>();
    public ArrayList<Integer> lineasy= new ArrayList<Integer>();

    public ArrayList<Integer> inicialx= new ArrayList<Integer>();
    public ArrayList<Integer>  inicialy= new ArrayList<Integer>();
    public ArrayList<Integer> finalx= new ArrayList<Integer>();
    public ArrayList<Integer>  finaly= new ArrayList<Integer>();
    public double escala;
    public Reloj() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setBounds(0,0,tam,tam);
        addKeyListener( this );
    }
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void paint (Graphics g){
        int x=0;
        int y=0;
        super.paint(g);

        g.setColor(Color.white);
        for(int i=0; i<lineasx.size();i++){
            if((x>=0&&y>=0)&&(x<tam&&y<tam)){
                x= lineasx.get(i);
                y= lineasy.get(i);
                g.fillOval (x,y, 3, 3);
            }
        }

        x=0;
        y=0;
        g.setColor(Color.white);
        for(int i=0; i<circulosx.size();i++){
            if((x>=0&&y>=0)&&(x<tam&&y<tam)){
                x= circulosx.get(i);
                y= circulosy.get(i);
                g.drawRect (x,y,2,2);
            }
        }

    }


    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    void insertaPuntoL(int x, int y)
    {
        lineasx.add(x);
        lineasy.add(y);
    }   

    void insertaPunto(int x, int y,int x1, int y1)
    {
        x = x+x1; //punto en x mas el desplazamiento para el punto origen x
        y = y+y1; //punto en y mas el desplazamiento para el punto origen y
        circulosx.add(x); //agrego la coordenada al arreglo de coordenadas en x
        circulosy.add(y); //agrego la coordenada al arreglo de coordenadas en y
    }
    int contador=0;
    void linea(int xi, int yi, int xf,int yf)
    {
        if (contador<15){
            inicialx.add(xi);
            inicialy.add(yi);
            finalx.add(xf);
            finaly.add(yf);
        }
        contador++;
        int dx = xf - xi;
        int dy = yf - yi;
        int cont = 0;
        int x_i = 1;
        int y_i = 1;

        if (dx < 0) x_i = -1;
        if (dy < 0) y_i = -1;

        insertaPuntoL(xi,yi);
        if (Math.abs(dx) >= Math.abs(dy)){

            int pk = (Math.abs(dy) * 2) - Math.abs(dx);

            while (cont < Math.abs(dx)){

                if (pk < 0){
                    xi = xi + x_i;

                    pk = pk + (Math.abs(dy) * 2);
                }else{
                    xi = xi + x_i;
                    yi = yi + y_i;

                    pk = pk + (Math.abs(dy) * 2) - (Math.abs(dx) * 2);
                }

                insertaPuntoL(xi,yi);
                cont++;
            }
        }else{
            int pk = (Math.abs(dx) * 2) - Math.abs(dy);

            while (cont < Math.abs(dy)){

                if (pk < 0){

                    yi = yi + y_i;
                    pk = pk + (Math.abs(dx) * 2);
                }else{
                    xi = xi + x_i;
                    yi = yi + y_i;

                    pk = pk + (Math.abs(dx) * 2) - (Math.abs(dy) * 2);
                }

                insertaPuntoL(xi,yi);
                cont++;
            }
        }
    }
int contc=0;
    public void circunferencia(int radio, int x1, int y1){
        if(contc<1){
        radios.add(radio); //agrego el radio al arreglo de radios 
        origencx.add(x1);  //agrego el punto x del origen actual al arreglo de origen x
        origency.add(y1);  //agrego el punto y del origen actual al arreglo de origen y
    }
    contc++;

        int radioCuadrado = radio * radio;
        int e,es,ed,contador;
        int x=radio; int y=0;

        ArrayList<Integer> coordenadasX= new ArrayList<Integer>();
        ArrayList<Integer> coordenadasY= new ArrayList<Integer>();

        while (y<x){
            coordenadasX.add(x);
            coordenadasY.add(y);

            e= (x * x)+ (y * y) - radioCuadrado;
            es = e + (2 * y) + 1;
            ed = es - (2* x) + 1;

            if (Math.abs (es)> Math.abs (ed))
            {x= x-1; y = y + 1;}

            else { y = y + 1; }

        }
        //:::::::::::::::::::::
        for(int i=0;i<coordenadasX.size();i++){

            x=coordenadasX.get(i);
            y=coordenadasY.get(i);

            insertaPunto(x,y,x1,y1);
            insertaPunto(y,x,x1,y1);
            insertaPunto((-1 * x),y,x1,y1);
            insertaPunto((-1 * y),x,x1,y1);
            insertaPunto(x,(-1 * y),x1,y1);
            insertaPunto(y,(-1 * x),x1,y1);
            insertaPunto((-1 * x),(-1 * y),x1,y1);
            insertaPunto((-1 * y),(-1 * x),x1,y1);
        }
    }

    public void keyPressed(KeyEvent evento){

        ArrayList<Integer> auxiliarradios= new ArrayList<Integer>();
        ArrayList<Integer> auxiliarorigencx= new ArrayList<Integer>();
        ArrayList<Integer> auxiliarorigency= new ArrayList<Integer>();

        if (evento.getKeyCode() == 37) { 
            for (int i=0; i<circulosx.size(); i++) {
                circulosx.set(i,circulosx.get(i)-1);
            }

            for (int i=0; i<origencx.size(); i++) {
                origencx.set(i,origencx.get(i)-1);
            }

            for (int i=0; i<lineasx.size(); i++) {
                lineasx.set(i,lineasx.get(i)-1);
            }

            for (int i=0; i<inicialx.size(); i++) {
                inicialx.set(i,inicialx.get(i)-1);

                finalx.set(i,finalx.get(i)-1);

            }

            this.repaint();
        }

        if (evento.getKeyCode() == 39) { 
            for (int i=0; i<circulosx.size(); i++) {
                circulosx.set(i,circulosx.get(i)+1);
            }
            for (int i=0; i<origencx.size(); i++) {
                origencx.set(i,origencx.get(i)+1);
            }
            for (int i=0; i<lineasx.size(); i++) {
                lineasx.set(i,lineasx.get(i)+1);
            }

            for (int i=0; i<inicialx.size(); i++) {
                inicialx.set(i,inicialx.get(i)+1);

                finalx.set(i,finalx.get(i)+1);

            }

            this.repaint();
        }

        if (evento.getKeyCode() == 40) { 
            for (int i=0; i<circulosy.size(); i++) {
                circulosy.set(i,circulosy.get(i)+1);
            }

            for (int i=0; i<origency.size(); i++) {
                origency.set(i,origency.get(i)+1);
            }
            for (int i=0; i<lineasy.size(); i++) {
                lineasy.set(i,lineasy.get(i)+1);
            }
            for (int i=0; i<inicialx.size(); i++) {

                inicialy.set(i,inicialy.get(i)+1);

                finaly.set(i,finaly.get(i)+1);
            }

            this.repaint();
        }

        if (evento.getKeyCode() == 38) { 
            for (int i=0; i<circulosy.size(); i++) {
                circulosy.set(i,circulosy.get(i)-1);
            }
            for (int i=0; i<origency.size(); i++) {
                origency.set(i,origency.get(i)-1);
            }
            for (int i=0; i<lineasy.size(); i++) {
                lineasy.set(i,lineasy.get(i)-1);
            }
            for (int i=0; i<inicialx.size(); i++) {

                inicialy.set(i,inicialy.get(i)-1);

                finaly.set(i,finaly.get(i)-1);
            }

            this.repaint();
        }

        if(evento.getKeyCode()== 107){
            
            int tam=radios.size();
            circulosx.clear();
            circulosy.clear();
            for(int i=0; i<tam;i++){
                double r= (double) radios.get(i);
                r= r +(r/10);  //+.1
                radios.set(i,(int)r);
                origencx.set(i,origencx.get(i)+(origencx.get(i)/10));
                origency.set(i,origency.get(i)+(origency.get(i)/10));
                int m= origencx.get(i);
                int n= origency.get(i);
                circunferencia((int)r,m,n);
            }

            tam=inicialx.size();
            lineasx.clear();
            lineasy.clear();
            for(int i=0; i<tam;i++){
                double ix= (double) inicialx.get(i);
                double iy= (double) inicialy.get(i);
                double fx= (double) finalx.get(i);
                double fy= (double) finaly.get(i);

                ix= ix +(ix * 0.1);  
                iy= iy +(iy* 0.1);  
                fx= fx +(fx* 0.1);  
                fy= fy +(fy* 0.1);  

                inicialx.set(i,(int)ix);
                inicialy.set(i,(int)iy);
                finalx.set(i,(int)fx);
                finaly.set(i,(int)fy);

            }
            for(int i=0; i<tam;i++){
                int ix= inicialx.get(i);
                int iy= inicialy.get(i);
                int fx= finalx.get(i);
                int fy= finaly.get(i);
                linea (ix,iy,fx,fy);
            }

            this.repaint();

        }

        if(evento.getKeyCode()==109){

            int tam=radios.size();
            circulosx.clear();
            circulosy.clear();
            for(int i=0; i<tam;i++){
                double r= (double) radios.get(i);
                r= r -(r/10);  //-.1 %
                radios.set(i,(int)r);
                double  origenx= (double) origencx.get(i)-((double)(origencx.get(i))/10);
                double  origeny= (double) origency.get(i)-((double)(origency.get(i))/10);
               
                origencx.set(i,(int)origenx);
                origency.set(i,(int)origeny); 
                int m= origencx.get(i);
                int n= origency.get(i);
                circunferencia((int)r,m,n);
            }

            tam=inicialx.size();
            lineasx.clear();
            lineasy.clear();
            for(int i=0; i<tam;i++){
                double ix= (double) inicialx.get(i);
                double iy= (double) inicialy.get(i);
                double fx= (double) finalx.get(i);
                double fy= (double) finaly.get(i);

                ix= ix -(ix * 0.1);  
                iy= iy -(iy* 0.1);  
                fx= fx -(fx* 0.1);  
                fy= fy -(fy* 0.1);  

                inicialx.set(i,(int)ix);
                inicialy.set(i,(int)iy);
                finalx.set(i,(int)fx);
                finaly.set(i,(int)fy);

            }
            for(int i=0; i<tam;i++){
                int ix= inicialx.get(i);
                int iy= inicialy.get(i);
                int fx= finalx.get(i);
                int fy= finaly.get(i);
                linea (ix,iy,fx,fy);
            }

            this.repaint();

        }

    }

    public void rotarSegundero(){
        double angulo= 0.105; 
        lineasx.clear();
        lineasy.clear();
        double xi=0;
        double yi=0;
        double xr= (double)origencx.get(0);
        double yr= (double)origency.get(0);
        double x= (double)finalx.get(0);
        double y= (double)finaly.get(0);
        xi= (xr + (x-xr)* Math.cos(angulo))- ((y-yr) * Math.sin(angulo));
        yi= (yr + (y-yr)* Math.cos(angulo))+ ((x-xr) * Math.sin(angulo));

        finalx.set(0,(int)xi);
        finaly.set(0,(int)yi);

        int tam=finalx.size(); 
        for(int i=0; i<tam;i++){
            int ix= inicialx.get(i);
            int iy= inicialy.get(i);
            int fx= finalx.get(i);
            int fy= finaly.get(i);
            linea (ix,iy,fx,fy);
        }

        repaint();

    }

    public void rotarMinutero(){
        double angulo=0.105; 
        lineasx.clear();
        lineasy.clear();
        double xi=0;
        double yi=0;
        double xr= (double)origencx.get(0);
        double yr= (double)origency.get(0);
        double x= (double)finalx.get(1);
        double y= (double)finaly.get(1);
        xi= (xr + (x-xr)* Math.cos(angulo))- ((y-yr) * Math.sin(angulo));
        yi= (yr + (y-yr)* Math.cos(angulo))+ ((x-xr) * Math.sin(angulo));

        finalx.set(1,(int)xi);
        finaly.set(1,(int)yi);

        int tam=finalx.size(); 
        for(int i=0; i<tam;i++){
            int ix= inicialx.get(i);
            int iy= inicialy.get(i);
            int fx= finalx.get(i);
            int fy= finaly.get(i);
            linea (ix,iy,fx,fy);
        }

        repaint();

    }

    public void rotarHorario(){
        double angulo=.51; 
        lineasx.clear();
        lineasy.clear();
        double xi=0;
        double yi=0;
        double xr= (double)origencx.get(0);
        double yr= (double)origency.get(0);
        double x= (double)finalx.get(2);
        double y= (double)finaly.get(2);
        xi= (xr + (x-xr)* Math.cos(angulo))- ((y-yr) * Math.sin(angulo));
        yi= (yr + (y-yr)* Math.cos(angulo))+ ((x-xr) * Math.sin(angulo));

        finalx.set(2,(int)xi);
        finaly.set(2,(int)yi);

        int tam=finalx.size(); 
        for(int i=0; i<tam;i++){
            int ix= inicialx.get(i);
            int iy= inicialy.get(i);
            int fx= finalx.get(i);
            int fy= finaly.get(i);
            linea (ix,iy,fx,fy);
        }

        repaint();

    }


    public void keyReleased(KeyEvent evento){}

    public void keyTyped(KeyEvent evento){}

}//fin de clase 