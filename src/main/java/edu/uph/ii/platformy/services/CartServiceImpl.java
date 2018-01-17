package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.models.Cart;
import edu.uph.ii.platformy.repositories.CartRepository;
import edu.uph.ii.platformy.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }
}
