package df.example.ext.impl.model;

import com.google.api.client.util.Key;
import com.google.common.base.MoreObjects;
import df.example.ext.api.SearchResult;

public class Item  implements SearchResult {
	
	@Key
	private Integer id;

    @Key("html_url")
	private String url;
	
	@Key
	private String name;
	
	@Key("full_name")
	private String fullName;
	
	public Item() { }

    public Item(Integer id, String url, String name, String fullName) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTitle() {
        return getName();
    }

    public String getDescription() {
        return getFullName();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("url", url)
                .add("name", name)
                .add("fullName", fullName)
                .toString();
    }

}
