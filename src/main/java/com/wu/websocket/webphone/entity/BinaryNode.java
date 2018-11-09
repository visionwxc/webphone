package com.wu.websocket.webphone.entity;

import java.io.Serializable;

public class BinaryNode<T extends Comparable> implements Serializable {
    private static final long serialVersionUID = -6477238039299912313L;

    public BinaryNode<T> left;//左节点

    public BinaryNode<T> right;//右节点

    public T data;

    public BinaryNode(T data,BinaryNode left,BinaryNode right){
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T data){
        this(data,null,null);
    }

    /**
     * 判断是否为叶子节点
     * @return
     */
    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }

}
