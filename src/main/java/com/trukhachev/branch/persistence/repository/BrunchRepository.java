package com.trukhachev.branch.persistence.repository;

import com.trukhachev.branch.persistence.entity.BranchEntity;
import org.springframework.data.repository.CrudRepository;

public interface BrunchRepository extends CrudRepository<BranchEntity, Long> {
}
