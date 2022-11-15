package com.ifpb.dac.projetospring.business.service;

import java.util.Optional;

public interface EntityService<E, I> {

    public E save(E entity);

    public Optional<E> findById(I id);

    public Iterable<E> findAll();

    public long count();

    public void delete(E entity);
    
}
