package df.example.ext.impl.model;

import com.google.api.client.util.Key;

import java.util.List;

public class HttpResult {

    @Key("total_count")
    private int totalCount;

    @Key("items")
    private List<Item> items;

    public int getTotalCount() {
        return totalCount;
    }

    public List<Item> getItems() {
        return items;
    }


}
