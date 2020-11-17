package org.projet_iwa.location.api.repository;

import org.projet_iwa.location.api.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> { }