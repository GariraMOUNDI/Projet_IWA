package org.projet_iwa.alert.api.repository;

import org.projet_iwa.alert.api.model.Alert;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
}
