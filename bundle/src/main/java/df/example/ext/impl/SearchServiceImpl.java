package df.example.ext.impl;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import df.example.ext.api.SearchException;
import df.example.ext.api.SearchResult;
import df.example.ext.api.SearchService;
import df.example.ext.impl.model.HttpResult;
import df.example.ext.impl.model.Item;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 30.06.17.
 */
public class SearchServiceImpl  implements SearchService {
    public List<SearchResult> search(String search) throws SearchException {

        try {
            return FluentIterable.from(searchImpl(search)).transform(new Function<Item, SearchResult>() {
                @Nullable
                public SearchResult apply(@Nullable Item item) {
                    return item;
                }
            }).toList();
        } catch (IOException e) {
            throw new SearchException(e);
        }
    }

    private HttpTransport httpTransport = new NetHttpTransport();
    private HttpRequestFactory requestFactory =
            httpTransport.createRequestFactory(new HttpRequestInitializer() {
                public void initialize(HttpRequest request) {
                    request.setParser(new JsonObjectParser(new JacksonFactory()));
                }
            });


    private List<Item> searchImpl(String search)
            throws IOException {
        String searchUrl = String.format("https://api.github.com/search/repositories?q=%s&sort=stars&order=desc", search);
        GenericUrl url = new GenericUrl(searchUrl.replaceAll(" ", "%20"));
        HttpRequest request = requestFactory.buildGetRequest(url);
        HttpResponse response = request.execute();
        return parseResponse(response);

    }

    private static List<Item> parseResponse(HttpResponse response) throws IOException {
        HttpResult httpResult = response.parseAs(HttpResult.class);
        final List<Item> results = httpResult.getItems();
        if (!results.isEmpty()) {
            return results;
        }
        return new ArrayList<Item>();
    }

}
