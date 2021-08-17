package space.gavinklfong.demo.streamapi.service;

import org.springframework.stereotype.Service;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreamService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public StreamService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public List<Product> getListOfProducts() {
        return productRepo.findAll().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Books") && product.getPrice() > 100)
                .collect(Collectors.toList());
    }

    public List<Order> getListOfOrders() {
        return orderRepo.findAll().stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> product.getCategory().equalsIgnoreCase("Baby")))
                .filter(order -> order.getStatus().equalsIgnoreCase("New"))
                .collect(Collectors.toList());
    }
}
