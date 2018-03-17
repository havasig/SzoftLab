import Main.*;

public class Test {
    public static void Menu(){

    }

    public static void main(String[] args) {
        Menu();
    }

    public void MoveToField(){
        Field prev_field = new Field();
        Field next_field = new Field();
        prev_field.SetNeighbor(Right, next_field);
        Worker worker = new Worker(prev_field);

        worker.Control(Right);
    }

    public void MoveToSwitch(){
        Field w_field = new Field();
        Switch switch = new Switch();
        w_field.SetNeighbor(Right, switch);
        Worker worker = new Worker(w_field);
        //Hole?? úgyse nyitja ki

        worker.Control(Right);
    }

    public void MoveToDestination(){
        Field w_field = new Field();
        Destination dest = new Destination();
        w_field.SetNeighbor(Right, dest);
        Worker worker = new Worker(w_field);

        worker.Control(Right);
    }

    public void MoveToClosedHole(){
        Field w_field = new Field();
        Hole hole = new Hole();
        w_field.SetNeighbor(Right, hole);
        Worker worker = new Worker(w_field);
        hole.SetClosed();

        worker.Control(Right);
    }

    public void MoveToOpenedHole(){
        Field w_field = new Field();
        Hole hole = new Hole();
        w_field.SetNeighbor(Right, hole);
        Worker worker = new Worker(w_field);
        hole.SetOpen();

        worker.Control(Right);
    }

    public void MoveToColumn() {
        Field w_field = new Field();
        Column column = new Column();
        w_field.SetNeighbor(Right, column);
        Worker worker = new Worker(w_field);

        worker.Control(Right);
    }

    public void CollideWithWorkerToColumn(){
        Field w1_field = new Field();
        Field w2_field = new Field();
        Column column = new Column();
        w1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, column);
        Worker worker1 = new Worker(w1_field);
        Worker worker2 = new Worker(w2_field);

        worker1.Control(Right);
    }

    public void CollideWithWorkerToField(){
        Field w1_field = new Field();
        Field w2_field = new Field();
        Field free_field = new Field();
        w1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, free_field);
        Worker worker1 = new Worker(w1_field);
        Worker worker2 = new Worker(w2_field);

        worker1.Control(Right);
    }

    public void CollideWithBoxToSwitch(){
        Field w_field = new Field();
        Field b_field = new Field();
        Switch switch = new Switch();
        Hole hole = new Hole();
        switch.SetHole(hole);
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, switch);
        Worker worker = new Worker(w_field);
        Box box = new Box(b_field);

        worker.Control(Right);
    }

    public void CollideWithBoxToWorkerToColumn(){
        Field w1_field = new Field();
        Field b1_field = new Field();
        Field w2_field = new Field();
        Column column = new Column();
        w1_field.SetNeighbor(Right, b1_field);
        b1_field.SetNeighbor(Right, w2_field);
        w2_field.SetNeighbor(Right, column);
        Worker worker1 = new Worker(w1_field);
        Box box1 = new Box(b_field);
        Worker worker2 = new Worker(w2_field);

        worker1.Control(Right);
    }

    public void CollideWithBoxToWorkerToWorkerToColumn(){
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
        Box box1 = new Box(b_field);
        Worker worker2 = new Worker(w2_field);
        Worker worker3 = new Worker(w3_field);

        worker1.Control(Right);
    }

    public void CollideWithBoxToField(){
        Field w_field = new Field();
        Field b_field = new Field();
        Field free_field = new Field();
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, free_field);
        Worker worker = new Worker(w_field);
        Box box1 = new Box(b_field);

        worker.Control(Right);
    }

    public void CollideWithBoxToClosedHole(){
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

    public void CollideWithBoxToOpenedHole(){
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

    public void CollideWithBoxToColumn(){
        Field w_field = new Field();
        Field b_field = new Field();
        Column column = new Column();
        w_field.SetNeighbor(Right, b_field);
        b_field.SetNeighbor(Right, column);
        Worker worker = new Worker(w_field);
        Box box1 = new Box(b_field);

        worker.Control(Right);
    }

    public void CollideWithBoxToDestination(){
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

    public void CollideWithBoxOffFromSwitch(){
        Field w_field = new Field();
        Switch switch = new Switch();
        Field random_field = new Field();
        w_field.SetNeighbor(Right, switch);
        b_field.SetNeighbor(Right, random_field);
        Hole hole = new Hole();
        switch.SetHole(hole);
        Worker worker = new Worker(w_field);
        Box box1 = new Box(switch);

        worker.Control(Right);
    }

}
