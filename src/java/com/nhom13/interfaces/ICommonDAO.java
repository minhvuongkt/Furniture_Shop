 package com.nhom13.interfaces;

import java.util.List;

public interface ICommonDAO<T> {
    List<T> list();
    List<T> list(int page, int pageSize, String searchValue);
    int count(String searchValue);
    T get(int id);
    boolean add(T item);
    boolean update(T item);
    boolean delete(int id);
}

