package com.mehrdadmaeen.reservationsystem.repositories;

import com.mehrdadmaeen.reservationsystem.model.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRepository extends JpaRepository<TableEntity,Long> {
    <T> Optional<T> findByTableName(String tableName, Class<T> classz);
}
