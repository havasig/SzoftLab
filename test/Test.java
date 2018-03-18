import Main.*;
import static Main.Direction.*;

public class Test {
    public static Logger logger;

    public static void main(String[] args) {
        Menu menu = new Menu();
        while (true)
            menu.displayMain();
    }

    public static void MoveToField(){
        Field prev_field = new Field("prev_field");
        Field next_field = new Field("next_field");
        prev_field.SetNeighbor(Right, next_field);
        Worker worker = new Worker(prev_field, "worker");

        worker.Control(Right);
    }

    public static void MoveToSwitch(){
        Field w_field = new Field("w_field");
        Switch switcher = new Switch("switcher");
        Hole hole = new Hole("hole");
        w_field.SetNeighbor(Right, switcher);
        switcher.SetHole(hole);
        Worker worker = new Worker(w_field, "worker");

        worker.Control(Right);
    }

    public static void MoveToDestination(){
        Field w_field = new Field("w_field");
        Destination dest = new Destination("destination");
        w_field.SetNeighbor(Right, dest);
        Worker worker = new Worker(w_field, "worker");

        worker.Control(Right);
    }

    public static void MoveToClosedHole(){
        Field w_field = new Field("w_field");
        Hole hole = new Hole("hole");
        w_field.SetNeighbor(Right, hole);
        Worker worker = new Worker(w_field, "worker");
        hole.SetClosed();

        worker.Control(Right);
    }

    public static void MoveToOpenedHole(){
        Field w_field = new Field("w_field");
        Hole hole = new Hole("hole");
        w_field.SetNeighbor(Right, hole);
        Worker worker = new Worker(w_field, "worker");
        hole.SetOpen();

        worker.Control(Right);
    }

    public static void MoveToColumn() {
        Field w_field = new Field("w_field");
        Column column = new Column("column");
        w_field.SetNeighbor(Right, column);
        Worker worker = new Worker(w_field, "worker");

        worker.Control(Right);
    }

    public static void CollideWithWorkerToColumn(){
        Field w1_field = new Field("w1_field");
        Field w2_field = new Field("w2_field");
        Column column = new Column("column");
        w1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, column);
        Worker worker1 = new Worker(w1_field, "worker1");
        Worker worker2 = new Worker(w2_field, "worker2");

        worker1.Control(Right);
    }

    public static void CollideWithWorkerToField(){
        Field w1_field = new Field("w1_field");
        Field w2_field = new Field("w2_field");
        Field free_field = new Field("free_field");
        w1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, free_field);
        Worker worker1 = new Worker(w1_field, "worker1");
        Worker worker2 = new Worker(w2_field, "worker2");

        worker1.Control(Right);
    }

    public static void CollideWithBoxToSwitch(){
        Field w_field = new Field("w_field");
        Field b_field = new Field("b_field");
        Switch switcher = new Switch("switcher");
        Hole hole = new Hole("hole");
        switcher.SetHole(hole);
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, switcher);
        Worker worker = new Worker(w_field, "worker");
        Box box = new Box(b_field, "box");

        worker.Control(Right);
    }

    public static void CollideWithBoxToWorkerToColumn(){
        Field w1_field = new Field("w1_field");
        Field b1_field = new Field("b1_field");
        Field w2_field = new Field("w2_field");
        Column column = new Column("column");
        w1_field.SetNeighbor(Right, b1_field);
        b1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, column);
        Worker worker1 = new Worker(w1_field, "worker1");
        Box box1 = new Box(b1_field, "box1");
        Worker worker2 = new Worker(w2_field, "worker2");

        worker1.Control(Right);
    }

    public static void CollideWithBoxToWorkerToWorkerToColumn(){
        Field w1_field = new Field("w1_field");
        Field b1_field = new Field("b1_field");
        Field w2_field = new Field("w2_field");
        Field w3_field = new Field("w3_field");
        Column column = new Column("column");
        w1_field.SetNeighbor(Right, b1_field);
        b1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, w3_field);
        w3_field.SetNeighbor(Right, column);
        Worker worker1 = new Worker(w1_field, "worker1");
        Box box1 = new Box(b1_field, "box1");
        Worker worker2 = new Worker(w2_field, "worker2");
        Worker worker3 = new Worker(w3_field, "worker3");

        worker1.Control(Right);
    }

    public static void CollideWithBoxToField(){
        Field w_field = new Field("w_field");
        Field b_field = new Field("b_field");
        Field free_field = new Field("free_field");
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, free_field);
        Worker worker = new Worker(w_field, "worker");
        Box box1 = new Box(b_field, "box1");

        worker.Control(Right);
    }

    public static void CollideWithBoxToClosedHole(){
        Field w_field = new Field("w_field");
        Field b_field = new Field("b_field");
        Hole hole = new Hole("hole");
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, hole);
        Worker worker = new Worker(w_field, "worker");
        Box box1 = new Box(b_field, "box1");
        hole.SetClosed();

        worker.Control(Right);
    }

    public static void CollideWithBoxToOpenedHole(){
        Field w_field = new Field("w_field");
        Field b_field = new Field("b_field");
        Hole hole = new Hole("hole");
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, hole);
        Worker worker = new Worker(w_field, "worker");
        Box box1 = new Box(b_field, "box1");
        hole.SetOpen();

        worker.Control(Right);
    }

    public static void CollideWithBoxToColumn(){
        Field w_field = new Field("w_field");
        Field b_field = new Field("b_field");
        Column column = new Column("column");
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, column);
        Worker worker = new Worker(w_field, "worker");
        Box box1 = new Box(b_field, "box1");

        worker.Control(Right);
    }

    public static void CollideWithBoxToDestination(){
        Field w_field = new Field("w_field");
        Field b_field = new Field("b_field");
        Destination dest = new Destination("destination");
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, dest);
        Worker worker = new Worker(w_field, "worker");
        Box box1 = new Box(b_field, "box1");
        //Game??

        worker.Control(Right);
    }

    public static void CollideWithBoxOffFromSwitch(){
        Field w_field = new Field("w_field");
        Field b_field = new Field("b_field");
        Switch switcher = new Switch("switcher");
        Field random_field = new Field("random_field");
        w_field.SetNeighbor(Right, switcher);
        b_field.SetNeighbor(Right, random_field);
        Hole hole = new Hole("hole");
        switcher.SetHole(hole);
        Worker worker = new Worker(w_field, "worker");
        Box box1 = new Box(switcher, "box1");

        worker.Control(Right);
    }
}
