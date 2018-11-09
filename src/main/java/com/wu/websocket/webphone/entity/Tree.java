package com.wu.websocket.webphone.entity;


/**
 *@description 二叉树的抽象数据类型接口
 *@date 2018/10/29
 *@Author: xwu
*/
public interface Tree<T extends Comparable>{

    /**
     * 判空
     * @return
     */
    boolean isEmpty();

    /**
     * 二叉树的节点个数
     * @return
     */
    int size();

    /**
     * 二叉树的高度
     * @return
     */
    int heigth();

    /**
     * 先根次序遍历
     * @return
     */
    String preOrder();

    /**
     * 后跟次序遍历
     * @return
     */
    String postOrder();

    /**
     * 层次遍历
     * @return
     */
    String levelOrder();

    /**
     * 将数据插入
     * @param data
     */
    void insert(T data);

    /**
     * 移除数据
     * @param data
     */
    void remove(T data);

    /**
     * 找到最大值
     * @return
     */
    T findMax();

    /**
     * 找到最小值
     * @return
     */
    T findMin();

    /**
     * 根据值找到节点
     * @param data
     * @return
     */
    BinaryNode findNode(T data);

    /**
     * 是否包含某个值
     * @param data
     * @return
     * @throws Exception
     */
    boolean contains(T data) throws Exception;

    /**
     * 清空
     */
    void clear();
}
