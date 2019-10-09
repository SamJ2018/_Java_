package com.cys.algorithm.tree.bst;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-08-12:00 PM
 **/
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0};
        BST bst = new BST();
        for (int i = 0; i < arr.length; i++) {
            bst.add(new Node(arr[i]));
        }
        //中序遍历二叉排序树
        System.out.println("-------");
        bst.infixOrder();

        bst.delNode(2);
        bst.delNode(9);
        bst.delNode(5);
        bst.delNode(7);
        bst.delNode(12);
        bst.delNode(1);
        bst.delNode(3);
        bst.delNode(10);
        bst.delNode(0);
        bst.infixOrder();
    }
}

//创建二叉排序树
class BST {
    private Node root;

    //添加结点的方法
    public void add(Node node) {
        if (root == null)
            root = node;
        else
            root.add(node);
    }

    //中序遍历
    public void infixOrder() {
        if (root != null)
            root.infixOrder();
        else
            System.out.println("二叉排序树为空，不能遍历");
    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null)
            return null;
        else
            return root.search(value);
    }

    //查找父结点
    public Node searchParent(int value) {
        if (root == null)
            return null;
        else
            return root.searchParent(value);
    }

    /**
     * @param node 传入的结点，当作二叉排序树的根结点
     * @return 返回的以node为根结点的二叉排序树的最小结点的值，并删除
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环查找左结点，就会找到最小值
        while (target.left != null)
            target = target.left;
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1、先找到要删除的结点，targetNode
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果targetNode没有父结点，这颗排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //去找到targetNode的父结点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父结点的左子节点还是右子结点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //删除有两颗子树的结点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {
                //删除只有一颗子树的结点
                //如果要删除的结点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            //左子结点
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {  //要删除的结点有右子结点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {  //如果targetNode是parent的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}

//创建Node结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加结点的方法（递归方式、满足二叉排序树）
    public void add(Node node) {
        if (node == null)
            return;

        //判断传入的结点的值，和当前子树的根结点的值的关系
        if (node.value < this.value) {
            if (this.left == null)
                this.left = node;
            else
                this.left.add(node); //递归地向左子树添加
        } else {
            //添加结点的值大于当前结点的值
            if (this.right == null)
                this.right = node;
            else
                this.right.add(node);
        }
    }

    //中序遍历二叉树
    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.infixOrder();
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            //找到句式该结点
            return this;
        } else if (value < this.value) {
            //如果左子结点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { //如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除结点的父节点
    public Node searchParent(int value) {
        //如果当前结点就是要删除的结点的父节点，就返回
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value))
            return this;
        else
            //如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
            if (value < this.value && this.left != null)
                return this.left.searchParent(value);
            else if (value >= this.value && this.right != null)
                return this.right.searchParent(value);
            else
                return null; //没有找到父结点
    }
}
