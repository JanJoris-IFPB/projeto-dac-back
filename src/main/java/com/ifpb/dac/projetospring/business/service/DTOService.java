package com.ifpb.dac.projetospring.business.service;

import java.util.List;

public interface DTOService<M, D> {

    public D toDTO(M model);

    public List<D> toDTOList(Iterable<M> modelList);

    public M toModel(D dto);
    
}
