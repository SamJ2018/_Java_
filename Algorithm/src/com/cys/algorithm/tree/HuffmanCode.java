package com.cys.algorithm.tree;

import java.io.*;
import java.util.*;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-08-8:15 AM
 **/
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        byte[] bytes = huffmanZip(contentBytes);
        //System.out.println(Arrays.toString(bytes) + "," + bytes.length);
        byte[] sourceBytes = decode(huffmanCodes, bytes);
        //System.out.println("原来的字符串" + new String(sourceBytes));

        //测试压缩文件
//        String srcFile = "C:\\Users\\missb\\Desktop\\_Java_\\wechat.jpg";
        String zipFile = "D:\\dst.zip";
        String dstFile = "D:\\dst2.jpg";
        //unZipFile(zipFile, dstFile);
    }

    /**
     * @param bytes 接受字节数组
     * @return 返回list 存放每一个node{数据，权值}
     */
    public static List<Node> getNodes(byte[] bytes) {
        //1、创建ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        //遍历bytes，统计每一个byte出现的次数->map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) counts.put(b, 1);
            else counts.put(b, ++count);
        }
        //把每一个键值对转成一个Node对象，并且加入nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过list，创建霍夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);//从小到大
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //data为null
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树，加入到nodes
            nodes.add(parent);
        }
        return nodes.get(0);//最终结点
    }

    public static void preOrder(Node root) {
        if (root != null)
            root.preOrder();
        else
            System.out.println("霍夫曼树为空，不能遍历");
    }

    /**
     * 生成霍夫曼树对应的霍夫曼编码
     * 1、将霍夫曼编码表放在Map<Byte,String> a=100 e=1110 i=101...
     * 2、在生成霍夫曼编码表时，需要拼接路径。定义一个StringBuilder 存储某个叶子结点的路径
     */
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 得到传入的node结点的所有叶子结点的霍夫曼编码，并放入集合中
     *
     * @param node          传入结点
     * @param code          路径：左0 右1
     * @param stringBuilder 拼串
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前node是叶子还是非叶子节点
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);//向左递归
                getCodes(node.right, "1", stringBuilder2);//向右递归
            } else {
                //叶子结点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //为了方便，重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) return null;
        //处理root
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 编写一个方法，将字符串对应的byte[]数组，通过生成的霍夫曼编码表，返回一个霍夫曼编码压缩后的byte[]
     *
     * @param bytes
     * @param huffmanCodes 8位1byte
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1、利用huffmancodes将bytes转成霍夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        int len = (stringBuilder.length() + 7) / 8;
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {  //每8位对应一个byte，步长+8
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else
                strByte = stringBuilder.substring(i, i + 8);
            //将strByte转成一个byte，放入huffmanCodesBytes
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanCodeBytes;
    }

    /**
     * 封装调用
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 经过霍夫曼编码压缩后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //1、创建节点list
        List<Node> nodes = getNodes(bytes);
        //2、创建霍夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //3、对应的霍夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //4、根据生成的霍夫曼编码 压缩得到霍夫曼编码字节数组
        return zip(bytes, huffmanCodes);
    }

    /**
     * 将byte转成一个二进制字符串
     *
     * @param b
     * @param flag 标志是否需要补高位，如果true  则需要补高位
     * @return 返回b对应的二进制的字符串（是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;
        //如果是整数，还要补高位
        if (flag) {
            temp |= 256;
        }

        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 完成对压缩数据的解码
     *
     * @param huffmanCodes 霍夫曼编码map
     * @param huffmanBytes 霍夫曼编码得到的字节数组
     * @return 返回的是原来字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1、得到huffmanBytes对应的二进制的字符串，形式1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //2、将bytes数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
//        System.out.println("霍夫曼字节数组对应的二进制字符串=" + stringBuilder.toString());
        //把字符串按照指定的霍夫曼编码进行解码
        //反向查询 100-> ?
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
//        System.out.println("map:" + map);
        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            //
            int count = 1;
            Byte b;
            while (true) {
                String key = stringBuilder.substring(i, i + count);//i不动，让count移动，指定匹配到一个字符
                if ((b = map.get(key)) == null)
                    count++;
                else
                    break;
            }
            list.add(b);
            i += count;
        }
        //for结束，list存放所有的字符
        //把list中的数据放入byte[]，并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 文件压缩
     *
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //获取到文件对应的霍夫曼编码表
            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的objectOutputStream
            oos = new ObjectOutputStream(os);
            //以对象流的方式写入霍夫曼编码，为了以后恢复源文件时使用
            oos.writeObject(huffmanBytes);
            //一定要把霍夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //编写一个方法，完成对压缩文件的解压
    public static void unZipFile(String zipFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取霍夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes数组写入到目标文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Node implements Comparable<Node> {

    Byte data;  //存放数据（字符） 本身， 比如'a'==> 97  ' '=>32
    int weight; //权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();
    }
}
