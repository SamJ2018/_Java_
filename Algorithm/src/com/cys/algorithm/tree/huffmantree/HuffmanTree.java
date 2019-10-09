package com.cys.algorithm.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-07-11:29 PM
 **/
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        }else{
            System.out.println("是空树，无法遍历");
        }
    }

    /**
     * 创建霍夫曼树
     * @param arr 要创建霍夫曼树的数组
     * @return 返回最后的结果
     */
    public static Node createHuffmanTree(int[] arr) {

        //1、为了从操作方便，遍历arr，将arr的每个元素构建成一个Node
        //将node放入ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //排序从小到大
            Collections.sort(nodes); //因为实现了comparable，所以支持
            //System.out.println("nodes=" + nodes);

            //取出根结点权值最小的两颗二叉树
            //1、取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            //2、取出第二小的结点（二叉树）
            Node rightNode = nodes.get(1);
            //3、构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4、从list中删除处理过的rightNode和leftNode
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5、将parent加入到nodes
            nodes.add(parent);
        }
        //返回霍夫曼树的root结点
        return nodes.get(0);
    }
}

//让Node排序，实现comparable接口
class Node implements Comparable<Node> {
    int value; //结点权值
    Node left; //指向左子结点
    Node right; //指向右子结点

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{value=" + value + '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.value - o.value;
    }
}
