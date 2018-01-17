package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
