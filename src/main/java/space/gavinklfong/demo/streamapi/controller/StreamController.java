package space.gavinklfong.demo.streamapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.gavinklfong.demo.streamapi.models.Customer;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.service.StreamService;

import java.util.List;
import java.util.Map;
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

    @GetMapping(path = "/last3Item")
    public List<Order> getLast3Order() {
        return streamService.getLast3Order();
    }

    @GetMapping(path = "/getOrdersEx7")
    public List<Product> getOrdersEx7() {
        return streamService.getOrdersEx7();
    }

    @GetMapping(path = "/sum")
    public Double sumOrders() {
        return streamService.sumOrders();
    }

    @GetMapping(path = "/avg")
    public Double avgOrders() {
        return streamService.averageOrders();
    }

    @GetMapping(path = "/statistics")
    public void statisticFigures() {
        streamService.statisticFigures();
    }

    @GetMapping(path = "/ex11")
    public Map<Long, Integer> ex11() {
        return streamService.ex11();
    }

    @GetMapping(path = "/ex12")
    public Map<Customer, List<Order>> ex12() {
        return streamService.ex12();
    }

    @GetMapping(path = "/ex13")
    public Map<Order, Double> ex13() {
        return streamService.ex13();
    }

    @GetMapping(path = "/ex14")
    public Map<String, List<String>> ex14() {
        return streamService.ex14();
    }

    @GetMapping(path = "/ex15")
    public Map<String, Optional<Product>> ex15() {
        return streamService.ex15();
    }
}
