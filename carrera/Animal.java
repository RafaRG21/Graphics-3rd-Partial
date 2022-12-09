package carrera;

public class Animal extends Thread{
    String type;
    float speed;
    int time=0;
    int place=0;

    Animal(String type,float speed){
        this.type = type;
        this.speed = speed;
    }

    @Override
    public void run() {
        float distance=0;
        while(distance<Main.lengthRace){
            System.out.println(type+" Distancia recorrida: "+distance+"m");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            distance += speed;
            time++;

        }
         this.place =  ++Main.place;
        System.out.println();
        System.out.println(type+" obtuvo el "+this.place+"° lugar!!!!");
        System.out.println();
    }
}