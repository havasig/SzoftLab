package Main;

import static Main.Direction.*;

public class Test {
       public static void main(String[] args) {
           Menu menu = new Menu();
           while (true)
               menu.displayMain();

       }

    public static void MoveToField(){
        Field prev_field = new Field();
        Field next_field = new Field();
        prev_field.SetNeighbor(Right, next_field);
        Worker worker = new Worker(prev_field);

        worker.Control(Right);
    }

    public static void MoveToSwitch(){
        Field w_field = new Field();
        Switch switcher = new Switch();
        w_field.SetNeighbor(Right, switcher);
        Worker worker = new Worker(w_field);
        //Hole?? úgyse nyitja ki

        worker.Control(Right);
    }

    public static void MoveToDestination(){
        Field w_field = new Field();
        Destination dest = new Destination();
        w_field.SetNeighbor(Right, dest);
        Worker worker = new Worker(w_field);

        worker.Control(Right);
    }

    public static void MoveToClosedHole(){
        Field w_field = new Field();
        Hole hole = new Hole();
        w_field.SetNeighbor(Right, hole);
        Worker worker = new Worker(w_field);
        hole.SetClosed();

        worker.Control(Right);
    }

    public static void MoveToOpenedHole(){
        Field w_field = new Field();
        Hole hole = new Hole();
        w_field.SetNeighbor(Right, hole);
        Worker worker = new Worker(w_field);
        hole.SetOpen();

        worker.Control(Right);
    }

    public static void MoveToColumn() {
        Field w_field = new Field();
        Column column = new Column();
        w_field.SetNeighbor(Right, column);
        Worker worker = new Worker(w_field);

        worker.Control(Right);
    }

    public static void CollideWithWorkerToColumn(){
        Field w1_field = new Field();
        Field w2_field = new Field();
        Column column = new Column();
        w1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, column);
        Worker worker1 = new Worker(w1_field);
        Worker worker2 = new Worker(w2_field);

        worker1.Control(Right);
    }

    public static void CollideWithWorkerToField(){
        Field w1_field = new Field();
        Field w2_field = new Field();
        Field free_field = new Field();
        w1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, free_field);
        Worker worker1 = new Worker(w1_field);
        Worker worker2 = new Worker(w2_field);

        worker1.Control(Right);
    }

    public static void CollideWithBoxToSwitch(){
        Field w_field = new Field();
        Field b_field = new Field();
        Switch switcher = new Switch();
        Hole hole = new Hole();
        switcher.SetHole(hole);
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, switcher);
        Worker worker = new Worker(w_field);
        Box box = new Box(b_field);

        worker.Control(Right);
    }

    public static void CollideWithBoxToWorkerToColumn(){
        Field w1_field = new Field();
        Field b1_field = new Field();
        Field w2_field = new Field();
        Column column = new Column();
        w1_field.SetNeighbor(Right, b1_field);
        b1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, column);
        Worker worker1 = new Worker(w1_field);
        Box box1 = new Box(b1_field);
        Worker worker2 = new Worker(w2_field);

        worker1.Control(Right);
    }

    public static void CollideWithBoxToWorkerToWorkerToColumn(){
        Field w1_field = new Field();
        Field b1_field = new Field();
        Field w2_field = new Field();
        Field w3_field = new Field();
        Column column = new Column();
        w1_field.SetNeighbor(Right, b1_field);
        b1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, w3_field);
        w3_field.SetNeighbor(Right, column);
        Worker worker1 = new Worker(w1_field);
        Box box1 = new Box(b1_field);
        Worker worker2 = new Worker(w2_field);
        Worker worker3 = new Worker(w3_field);

        worker1.Control(Right);
    }

    public static void CollideWithBoxToField(){
        Field w_field = new Field();
        Field b_field = new Field();
        Field free_field = new Field();
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, free_field);
        Worker worker = new Worker(w_field);
        Box box1 = new Box(b_field);

        worker.Control(Right);
    }

    public static void CollideWithBoxToClosedHole(){
        Field w_field = new Field();
        Field b_field = new Field();
        Hole hole = new Hole();
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, hole);
        Worker worker = new Worker(w_field);
        Box box1 = new Box(b_field);
        hole.SetClosed();

        worker.Control(Right);
    }

    public static void CollideWithBoxToOpenedHole(){
        Field w_field = new Field();
        Field b_field = new Field();
        Hole hole = new Hole();
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, hole);
        Worker worker = new Worker(w_field);
        Box box1 = new Box(b_field);
        hole.SetOpen();

        worker.Control(Right);
    }

    public static void CollideWithBoxToColumn(){
        Field w_field = new Field();
        Field b_field = new Field();
        Column column = new Column();
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, column);
        Worker worker = new Worker(w_field);
        Box box1 = new Box(b_field);

        worker.Control(Right);
    }

    public static void CollideWithBoxToDestination(){
        Field w_field = new Field();
        Field b_field = new Field();
        Destination dest = new Destination();
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, dest);
        Worker worker = new Worker(w_field);
        Box box1 = new Box(b_field);
        //Game??

        worker.Control(Right);
    }

    public static void CollideWithBoxOffFromSwitch(){
        Field w_field = new Field();
        Field b_field = new Field();
        Switch switcher = new Switch();
        Field random_field = new Field();
        w_field.SetNeighbor(Right, switcher);
        b_field.SetNeighbor(Right, random_field);
        Hole hole = new Hole();
        switcher.SetHole(hole);
        Worker worker = new Worker(w_field);
        Box box1 = new Box(switcher);

        worker.Control(Right);
    }

}
