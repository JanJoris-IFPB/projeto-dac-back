package com.ifpb.dac.projetospring.business.service;

import com.ifpb.dac.projetospring.model.entity.SystemRole;

public interface SystemRoleService {

    public enum AVAILABLE_ROLES { ADMIN, USER }

	public void createDefaultValues();
	
	public SystemRole findByName(String name);
	
	public SystemRole findDefault();
    
}
