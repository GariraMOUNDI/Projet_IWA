package org.projet_iwa.alert.location.api.repository;

import org.projet_iwa.alert.location.api.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}