import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
class Processo {
    int id;
    int At;
    int Bt;
    int Ct;
    int Tat;
    int Wt;

    public Processo(int id, int At, int Bt) {
        this.id = id;
        this.At = At;
        this.Bt = Bt;
        this.Ct = -1;
        this.Tat = -1;
        this.Wt = -1;
    }
}