package repository;

import entities.Entity;

import java.util.Collection;

public interface Repository<ID, E extends Entity<ID>> {
    E findOne(ID id);
    Collection<E> findAll();
    void add(E entity);
    void delete(ID id);
    void update(E entity);
}