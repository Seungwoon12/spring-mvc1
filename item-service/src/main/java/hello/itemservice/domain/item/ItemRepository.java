package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // 들어가보면 @Component 어노테이션이 있기 때문에 ComponentScan의 대상이 된다.
public class ItemRepository {

    // 실무에서는 ConcurrentHashMap 써야함. 동시에 접근하는 멀티쓰레드 환경이라
    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;


    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        // 실제 store에 영향을 주지 않기 위해서 ArrayList로 감싸서 반환
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);

        // 프로젝트 규모가 클때는 Dto 만들어서 하는게 명확함. itemId를 사용안하기 때문에
        // 그리고 혹시라도 updateParam.setId()와 같이 하지 말아야 할 작업이 수행될 수 있기 때문이다.
       findItem.setItemName(updateParam.getItemName());
       findItem.setPrice(updateParam.getPrice());
       findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear(); // store의 HashMap 데이터 다 날리기~
    }


}
