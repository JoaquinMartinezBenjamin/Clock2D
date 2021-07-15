import java.util.Calendar;
public class Main extends Thread{     
    public static void main(String[] args) {
        Reloj frame = new Reloj();
        frame.setVisible(true);
        frame.circunferencia (130,350,300);
       
        frame.linea (350,300,350,200); //segundos
        frame.linea (350,300,350,210); //minutos
        frame.linea (350,300,350,240); //horas
        frame.linea (350,174,350,184);  //12
        frame.linea (412,190,402,203);  //1
        frame.linea (458,237,448,244);  

        frame.linea (476,300,466,300);  //3

        frame.linea (458,363,448,356); 
        frame.linea (412,408,402,395); 

        frame.linea (350,426,350,416);  //6

        frame.linea (288,408,298,395); 
        frame.linea (242,363,252,356); 

        frame.linea (224,300,234,300); //9

        frame.linea (288,190,298,203);  
        frame.linea (242,237,252,244); 

        frame.repaint(); 
        System.out.close();
        Calendar fecha = Calendar.getInstance();

        int hora = fecha.get(Calendar.HOUR_OF_DAY);
    
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        for(int i=0; i<hora; i++){   
            frame.rotarHorario();
        }
        for(int i=0; i<minuto; i++){   
            frame.rotarMinutero();
        }
        for(int i=0; i<segundo; i++){   
            frame.rotarSegundero();
        }
        for(int i=segundo;;i++){
            // System.out.println(i);
            if (i%60==0&&i>0){frame.rotarMinutero();}
            if (i%3600==0&&i>0){frame.rotarHorario();}
            try {
                Thread.sleep(998);
            } catch (InterruptedException ex) {
            }
            frame.rotarSegundero();
        }

    }    
}