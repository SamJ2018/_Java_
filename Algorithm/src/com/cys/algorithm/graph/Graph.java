package com.cys.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-09-9:39 AM
 **/
public class Graph {
    /**
        存储顶点集合
     *
     */
    private ArrayList<String> vertexList;
    //存储图对应的邻接矩阵
    private int[][] edges;
    //表示边的数目
    private int numOfEdges;
    private boolean[] isVisited;

    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //dfs
    //1、得到第一个邻接结点的下标w
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) //存在邻接点
                return j;
        }
        return -1;
    }

    //2、根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) { //v2前一个邻接结点
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0)  //找到下一个邻接结点
                return j;
        }
        return -1;
    }

    //3、dfs
    private void dfs(boolean[] isVisited, int i) {
        //访问该结点，输出
        System.out.print(getValueByIndex(i));
        if (i != getNumOfVertex()) System.out.print("->");
        //设置已经访问过
        isVisited[i] = true;
        int w = getFirstNeighbor(i);//获得第一个邻接点
        while (w != -1) {
            //有邻接结点
            if (!isVisited[w])
                dfs(isVisited, w);
            //如果已经访问过
            w = getNextNeighbor(i, w);
        }
    }

    //4、遍历所有节点，并进行dfs
    public void dfs() {
        //回溯 dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //bfs
    private void bfs(boolean[] isVisited, int i) {
        int u; //表示队列的头结点对应的下标
        int w; //邻接结点w
        //访问当前结点
        LinkedList<Integer> queue = new LinkedList<>();//队列,记录结点访问顺序
        System.out.print(getValueByIndex(i));
        if (i != getNumOfVertex() - 1) System.out.print("->");
        isVisited[i] = true; //已访问，并
        queue.addLast(i);  //加入队列

        while (!queue.isEmpty()) {
            u = queue.removeFirst();//取队列头结点下标
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) { //没访问过
                    System.out.print(getValueByIndex(w));
                    if (w != getNumOfVertex() - 1) System.out.print("->");
                    isVisited[w] = true;//已访问，并且
                    queue.addLast(w); //入队
                }
                w = getNextNeighbor(u, w);//获取u的下一个邻接点，继续bfs
            }
        }
    }

    //遍历所有结点，都进行bfs
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i])
                bfs(isVisited, i);
        }
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标，第几个顶点？  “A”-“B”   “A”->0  “B”->1
     * @param v2     第二个顶点对应的下标
     * @param weight 权值，0/1  关联与否？
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
       常用的方法
       1、返回结点个数
    */
    private int getNumOfVertex() {
        return vertexList.size();
    }

    //2、得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     *   3、返回结点i(下标)对应的数据  0->"A"  1->"B"  2->"C"
     *
     */
    public String getValueByIndex(int i) {

        return vertexList.get(i);
    }

    //4、返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //5、显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    public static void main(String[] args) {
        int n = 8;
        String vertex[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加
        for (String vertexValue : vertex) {
            graph.insertVertex(vertexValue);
        }
        //添加边 A-B  A-C  B-C  B-D  B-E
        //A-B
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        //显示
        graph.dfs();
    }
}


