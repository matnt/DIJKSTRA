/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Mat Nguyen
 */
public class DIJKSTRA {
    class Edge {
        int x;
        int w;

        public Edge(int x, int w) {
            this.x = x;
            this.w = w;
        }

        public int getX() {
            return x;
        }

        public int getW() {
            return w;
        }
    }
    
    public static final int MAX = 1000;
    int n; // so dinh
    int m; // so canh

    Map<Integer, Set<Edge>> A;
    Set<Integer> S;
    int[] d;
    int[] pred;
    int W = 0;
    int s;
    int t;
    Stack<Integer> path;
    
    public DIJKSTRA(){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DIJKSTRA dij = new DIJKSTRA();
        dij.input();
        dij.solve();
        dij.printResult();
    }
    
    public void input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        A = new HashMap<Integer, Set<Edge>>(); // tap cac canh ke cua cay T
        S = new HashSet<Integer>(); // tap dinh ngoai cay
        d = new int[n + 1]; // khoang cach tu 1 dinh ben ngai cay toi cay
        pred = new int[n + 1];
        path = new Stack<Integer>();
        for(int v = 1; v <= n; v++)
            A.put(v,new HashSet<Edge>());
        
        for(int i = 1; i <= m; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
           
            Edge e = new Edge(v, w);
            A.get(u).add(e);
            
            Edge ed = new Edge(u,w);
            A.get(v).add(ed);
        }
        s = in.nextInt(); // start
        t = in.nextInt(); // end
        
    }
    public void solve(){

        for(int v = 1; v <= n; v++){
            if(v != s)
                S.add(v);
        }
        for(int v = 1; v <= n; v++){
            if(v != s){
                d[v] = MAX;
            }
        }
        for(Edge edge: A.get(s)){
            int v = edge.getX();
            int w = edge.getW();
            d[v] = w; // khoang cach tu v toi T la w
            pred[v] = s; // dau mut cua v tai T la s
        }
        
        while(S.size() > 0){ // khi cac dinh chua dc them het vao cay
            int v = selecmin(); // lua chon canh nho nhat v
            W += d[v]; // tang trong so cua cay them d[v]
            S.remove(v); // loai bo v khoi tap dinh S o ngoai cay T
            for(Edge e: A.get(v)){ // lay ra cac canh ke cua cay T tai v
                int u = e.getX(); // dinh u
                if(S.contains(u)){
                    int w = e.getW();
                    if(d[u] > d[v] + w){
                        d[u] = w + d[v];
                        pred[u] = v;
                    }
                }
            }
        }
        
    }
    public int selecmin(){
        int minD = MAX;
        int sel_v = -1;
        for(int v: S){
            if(d[v] < minD){
                sel_v = v;
                minD = d[v];
            }
        }
        return sel_v;
    }
    
    public void printResult(){
        System.out.println(d[t] +"");
        int x = t;
        while(x != s){
            path.push(x);
            x = pred[x]; // tim dinh ke voi x trong cay T da lua chon
        }
        System.out.print(s + "");
        while(!path.isEmpty()){
            int a = path.lastElement();
            path.pop();
            System.out.print("-->" + a);
        }
        System.out.println("");
    }
    
    
}
