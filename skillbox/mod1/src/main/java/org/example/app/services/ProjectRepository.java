package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);

    boolean removeItemByTitle(String title);

    boolean removeItemByAuthor(String author);

    boolean removeItemBySize(String size);
}
