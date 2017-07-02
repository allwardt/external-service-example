package df.example.ext.api;

import java.util.List;

public interface SearchService {

	List<SearchResult> search(String search) throws SearchException;
	
}
