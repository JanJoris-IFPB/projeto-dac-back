package com.ifpb.dac.projetospring.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.business.service.SystemRoleService;
import com.ifpb.dac.projetospring.model.entity.SystemRole;
import com.ifpb.dac.projetospring.model.repository.SystemRoleRepository;

@Service
public class SystemRoleServiceImpl implements SystemRoleService {

	@Autowired
	private SystemRoleRepository repository;

	@Override
	public void createDefaultValues() {
		List<SystemRole> roles = new ArrayList<>();

		for (AVAILABLE_ROLES availableRole : AVAILABLE_ROLES.values()) {
			SystemRole role = findByName(availableRole.name());

			if (role == null) {
				role = new SystemRole();
				role.setName(availableRole.name());
				roles.add(role);
			}
		}

		repository.saveAll(roles);
	}

	@Override
	public SystemRole findByName(String name) {
		if (name == null) {
			throw new IllegalStateException("Name cannot be null");
		}

		return repository.findByName(name).orElse(null);
	}

	@Override
	public SystemRole findDefault() {
		return findByName(AVAILABLE_ROLES.USER.name());
	}

}
