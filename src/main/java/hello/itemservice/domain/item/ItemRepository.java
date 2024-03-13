package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //컴포넌트 스캔의 대상
public class ItemRepository {

    //아이템 저장소니까 키는 Long type이 된다.
    private static final Map<Long, Item> store = new HashMap<>(); //static (여러개가 동시에 스토어에 접근하게 되면 해시맵을 쓰면 안된다)
    private static long sequence = 0L; //static

    public Item save(Item item) { //저장하는 기능
        item.setId(++sequence); //값 증가
        store.put(item.getId(), item); //아이템 하나 넣고
        return item; //아이템 받아둠
    }

    //하나 조회
    public Item findById(Long id) {
        return store.get(id);
    }

    //전체 조회
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    //업데이트
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    //test용 -> 스토어 해시맵 데이터가 다 날라간다.
    public void clearStore(){
        store.clear();
    }

}
