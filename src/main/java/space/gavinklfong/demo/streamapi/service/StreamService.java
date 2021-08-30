package space.gavinklfong.demo.streamapi.service;

import org.springframework.stereotype.Service;
import space.gavinklfong.demo.streamapi.models.Customer;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
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

    public List<Product> applyDiscount() {
        return productRepo.findAll().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Toys"))
                .map(product -> {
                    product.setPrice(product.getPrice() * 0.9);
                    return product;
                })
                .collect(Collectors.toList());
    }

    public List<Product> getProductsEx4() {
        return orderRepo.findAll().stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
                .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
                .flatMap(order -> order.getProducts().stream()).collect(Collectors.toList());
    }

    public Optional<Product> getCheapestItem() {
        return productRepo.findAll().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("Books"))
                .min(Comparator.comparing(Product::getPrice));
    }

    public List<Order> getLast3Order() {
        return orderRepo.findAll().stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3).collect(Collectors.toList());
    }

    public List<Product> getOrdersEx7() {
        return orderRepo.findAll().stream()
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                .peek(order -> System.out.println(order))
                .flatMap(order -> order.getProducts().stream())
                .distinct().collect(Collectors.toList());
    }

    public Double sumOrders() {
        return orderRepo.findAll().stream()
                .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
                .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) < 0)
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .peek(product -> System.out.println(product))
                .sum();
    }

    public Double averageOrders() {
        return orderRepo.findAll().stream()
                .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
                .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) < 0)
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .average().getAsDouble();
    }

    public void statisticFigures() {
        DoubleSummaryStatistics summaryStatistics = productRepo.findAll().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase("books"))
                .mapToDouble(productRepo -> productRepo.getPrice())
                .summaryStatistics();

        System.out.println(summaryStatistics.getMax());
        System.out.println(summaryStatistics.getCount());
        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getAverage());
        System.out.println(summaryStatistics.getSum());
    }

    public Map<Long, Integer> ex11() {
        return orderRepo.findAll().stream()
                .collect(
                        Collectors.toMap(order -> order.getId(),
                                order -> order.getProducts().size())
                );
    }

    public Map<Customer, List<Order>> ex12() {
        return orderRepo.findAll().stream()
                .collect(Collectors.groupingBy(Order::getCustomer));
    }

    public Map<Order, Double> ex13() {
        return orderRepo.findAll().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        order -> order.getProducts().stream()
                                .mapToDouble(product -> product.getPrice()).sum()
                ));
    }

    public Map<String, List<String>> ex14() {
        return productRepo.findAll().stream().
                collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.mapping(product -> product.getName(), Collectors.toList())));
    }

    public Map<String, Optional<Product>> ex15() {
        return productRepo.findAll().stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.maxBy(Comparator.comparing(Product::getPrice))));
    }
}
