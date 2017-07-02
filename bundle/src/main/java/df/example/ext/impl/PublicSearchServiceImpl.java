package df.example.ext.impl;

import df.example.ext.api.SearchException;
import df.example.ext.api.SearchResult;
import df.example.ext.api.SearchService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import java.util.List;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 30.06.17.
 */
@Service
@Component(metatype = false)
public class PublicSearchServiceImpl implements SearchService {

    private SearchService impl = new SearchServiceImpl();

    public List<SearchResult> search(String search) throws SearchException {
        return impl.search(search);
    }
}
