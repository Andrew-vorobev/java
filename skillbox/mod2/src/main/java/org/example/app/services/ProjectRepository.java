package org.example.app.services;

import org.example.app.exceptions.BookShelfRegexException;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);
    boolean removeItemByTitle(String title) throws BookShelfRegexException;

    boolean removeItemByAuthor(String author) throws BookShelfRegexException;

    boolean removeItemBySize(String size) throws BookShelfRegexException;
}
