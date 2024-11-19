package com.example.nutrisend.platform.iam.application.internal.queryservices;


import com.example.nutrisend.platform.iam.domain.model.entities.Role;
import com.example.nutrisend.platform.iam.domain.model.queries.GetAllRolesQuery;
import com.example.nutrisend.platform.iam.domain.model.queries.GetRoleByNameQuery;
import com.example.nutrisend.platform.iam.domain.services.RoleQueryService;
import com.example.nutrisend.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository){this.roleRepository = roleRepository;}

    @Override
    public List<Role> handle(GetAllRolesQuery query){return roleRepository.findAll();}

    public Optional<Role> handle(GetRoleByNameQuery query){return roleRepository.findByName(query.name());}

}
