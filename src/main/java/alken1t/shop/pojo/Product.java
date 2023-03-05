package alken1t.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class Product {
    private final String category;

    private final String name;

    private final int price;
}