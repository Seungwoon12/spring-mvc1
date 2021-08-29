package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Data
public class Item {

    private Long id;
    private String itemName;
    // Integer로 하는 이유는. 값이 없을 수도 있기 때문. 즉, 가격 or 수량이 null 일 수도 있기 때문
    private Integer price;
    private Integer quantity;

    public Item() {

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
