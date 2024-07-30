package org.tutu.springframework.beans.factory;

/**
 * 标记类接口，实现该接口可以被Spring容器感知
 * TODO 能感知代表着什么？
 * TODO 看了下具体的实现，单纯实现了 Aware 接口是不行的，还需要在加载的时候进行if判断
 */
public interface Aware {
}
