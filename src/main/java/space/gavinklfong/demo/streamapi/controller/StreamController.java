package space.gavinklfong.demo.streamapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.service.StreamService;

import java.util.List;
import java.util.Optional;

@RestController
public class StreamController {

    final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping(path = "/products")
    public List<Product> getProducts() {
        return streamService.getListOfProducts();
    }

    @GetMapping(path = "/orders")
    public List<Order> getOrders() {
        return streamService.getListOfOrders();
    }

    @GetMapping(path = "/discounts")
    public List<Product> applyDiscounts() {
        return streamService.applyDiscount();
    }

    @GetMapping(path = "/productsEx4")
    public List<Product> getProductsEx4() {
        return streamService.getProductsEx4();
    }

    @GetMapping(path = "/cheapestItem")
    public Optional<Product> getCheapestItem() {
        return streamService.getCheapestItem();
    }

}
