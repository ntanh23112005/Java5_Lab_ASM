package poly.java5.service;

import poly.java5.model.Item;
import poly.java5.utils.DB;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final Map<Integer, Item> cart = new HashMap<>();

    @Override
    public Item add(Integer id) {
        Item item = DB.items.get(id);
        if (item != null) {
            cart.put(id, new Item(id, item.getName(), item.getPrice(), cart.getOrDefault(id, item).getQty() + 1));
            System.out.println(id);
        }
        return cart.get(id);
    }

    @Override
    public void remove(Integer id) {
        cart.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = cart.get(id);
        if (item != null) {
            item.setQty(qty);
        }
        return item;
    }

    @Override
    public void clear() {
        cart.clear();
    }

    @Override
    public Collection<Item> getItems() {
    	System.out.println("Cart size: " + cart.size());
        return cart.values();
    }

    @Override
    public int getCount() {
        return cart.values().stream().mapToInt(Item::getQty).sum();
    }

    @Override
    public double getAmount() {
        return cart.values().stream().mapToDouble(item -> item.getPrice() * item.getQty()).sum();
    }
}
