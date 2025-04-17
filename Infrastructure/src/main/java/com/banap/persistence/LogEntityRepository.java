package com.banap.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface LogEntityRepository extends JpaRepository<LogEntityJpa, String> {
}
