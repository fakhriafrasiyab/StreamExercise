package space.gavinklfong.demo.streamapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.service.StreamService;

import java.util.List;

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
}
