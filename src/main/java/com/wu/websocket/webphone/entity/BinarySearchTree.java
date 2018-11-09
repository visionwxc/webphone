package com.wu.websocket.webphone.entity;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *@description 二叉查找树的架构定义
 *@date 2018/10/29
 *@Author: xwu
*/
public class BinarySearchTree<T extends Comparable> implements Tree<T> {

    protected BinaryNode<T> root;//根节点

    public BinarySearchTree(){
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root.right == null && root.left == null;
    }

    /**
     * 计算大小
     * @return
     */
    @Override
    public int size() {
        return size(root);
    }

    /**
     * 递归实现：定义根节点root后，再用subtree实现递归
     * @param subtree
     * @return
     */
    private int size(BinaryNode<T> subtree){
        if (subtree == null)
            return 0;
        else
        {
            //对比汉诺塔:H(n)=H(n-1) + 1 + H(n-1)
            return size(subtree.left) + 1 + size(subtree.right);
        }
    }

    /**
     * 计算深度
     * @return
     */
    @Override
    public int heigth() {
        return height(root);
    }

    /**
     * 递归实现
     * @param subtree
     * @return
     */
    private int height(BinaryNode<T> subtree){
        if(subtree == null)
            return 0;
        int r = height(subtree.right);
        int l = height(subtree.left);

        return l > r ? (l+1) : (r+1);
    }

    @Override
    public String preOrder() {
        String str = preOrder(root);
        if(str.length() > 0)
            str = str.substring(0,str.length() - 1);
        return str;
    }

    private String preOrder(BinaryNode<T> subTree){
        StringBuffer stringBuffer = new StringBuffer();
        if(subTree != null){
            stringBuffer.append(subTree.data).append(",");
            stringBuffer.append(preOrder(subTree.left));
            stringBuffer.append(preOrder(subTree.right));
        }
        return stringBuffer.toString();
    }

    @Override
    public String postOrder() {
        String str = postOrder(root);
        if(str.length() > 0)
            str = str.substring(0,str.length() - 1);
        return str;
    }

    private String postOrder(BinaryNode<T> subTree){
        StringBuffer stringBuffer = new StringBuffer();
        if(subTree != null){
            stringBuffer.append(postOrder(subTree.left));
            stringBuffer.append(postOrder(subTree.right));
            stringBuffer.append(subTree.data).append(",");
        }
        return stringBuffer.toString();
    }

    /**
     * 层序遍历
     * @return
     */
    @Override
    public String levelOrder() {
        //用于存放各个需要遍历的结点，左结点一定优先于右结点
        LinkedBlockingQueue<BinaryNode<T>> queue = new LinkedBlockingQueue<>();

        StringBuffer stringBuffer = new StringBuffer();
        BinaryNode<T> p = this.root;

        while (p != null){
            //记录经过的结点
            stringBuffer.append(p.data);

            //按层次遍历结点，左结点一定在右结点之前
            if(p.left != null){
                queue.add(p.left);
            }
            if(p.right != null){
                queue.add(p.right);
            }

            //访问下一个结点
            p = queue.poll();
        }
        return stringBuffer.toString();
    }

    @Override
    public void insert(T data) {
        if(null == data){
            throw new RuntimeException("data can't comparable be null");
        }
        root = insert(data,root);
    }

    private BinaryNode<T> insert(T data,BinaryNode<T> p){
        if(p == null){
            p = new BinaryNode<T>(data,null,null);
        }

        //比较插入的节点值，判断是向左搜索还是向右搜索
        int comparaeResult = data.compareTo(p.data);

        if(comparaeResult < 0){//左
            p.left = insert(data,p.left);
        }else if(comparaeResult > 0) {//右
            p.right = insert(data,p.right);
        }else {
            ;//重复的值不需要插入
        }
        return p;
    }

    @Override
    public void remove(T data) {
        if(null == data){
            throw new RuntimeException("data can't comparable be null");
        }
        root = remove(data,root);
    }

    /**
     * 三种情况删除
     * 1.删除叶子节点（也就是没有孩子节点）
     * 2.删除拥有一个孩子结点的结点
     * 3.删除两个孩子结点的结点
     * @param data
     * @param p
     * @return
     */
    private BinaryNode<T> remove(T data,BinaryNode<T> p){
        //没有删除的结点
        if(null == p){
            return p;
        }
        int compareResult = data.compareTo(p.data);

        if(compareResult < 0){
            p.left = remove(data,p.left);
        }else if(compareResult > 0){
            p.right = remove(data,p.right);
        }else if(p.left != null && p.right != null){
            p.data = findMin();
            p.right = remove(data,p.right);
        }else {
            p = (p.left!= null) ? p.left : p.right;
        }
        return p;
    }

    @Override
    public T findMax() {
        if(isEmpty())
            return null;
        return findMax(root).data;
    }

    /**
     * 查找最大值结点
     * @param p
     * @return
     */
    private BinaryNode<T> findMax(BinaryNode<T> p){
        if (p==null)//结束条件
            return null;
        else if (p.right==null)
            return p;
        return findMax(p.right);
    }

    @Override
    public T findMin() {
        if(isEmpty())
            return null;
        return findMin(root).data;
    }

    /**
     * 查找最小值结点
     * @param p
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> p){

        if (p==null)//结束条件
            return null;
        else if (p.left==null)//如果没有左结点,那么t就是最小的
            return p;
        return findMin(p.left);
    }

    @Override
    public BinaryNode findNode(T data) {
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {

    }
}
