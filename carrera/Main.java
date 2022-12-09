package carrera;

//Ruiz Gudiño Jose Rafael 20110374
public class Main {
    static final int lengthRace = 800;
    static volatile int place = 0;
    public static void main(String[] args){
        Animal dog = new Animal("Perro",8.49f);//Un perro corre en promedio 8.49 m/s
        Animal cat = new Animal("Gato",13.33f); //Un gato corre en promedio 13.33 m/s
        Animal rabbit = new Animal("Conejo",11.11f); //Un conejo corre en promedio 11.11 m/s
        dog.start();
        cat.start();
        rabbit.start();

        try {
            dog.join();
            cat.join();
            rabbit.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cat.type+": "+cat.place+"° lugar. Tiempo transcurrido: "+cat.time+"s");
        System.out.println(rabbit.type+": "+rabbit.place+"° lugar. Tiempo transcurrido: "+rabbit.time+"s");
        System.out.println(dog.type+": "+dog.place+"° lugar. Tiempo transcurrido: "+dog.time+"s");
    }
}