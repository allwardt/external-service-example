package df.example.ext.impl.model;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dominik Foerderreuther <df@adobe.com> on 02.07.17.
 */
public class HttpResultTest {


    @Test
    public void getTotalCount_shouldReturnValue() throws Exception {
        // given
        HttpResult underTest = new HttpResult();
        Whitebox.setInternalState(underTest, "totalCount", 10);

        // when
        int totalCount = underTest.getTotalCount();

        assertEquals(totalCount, 10);
    }

    @Test
    public void getItems_shouldReturnList() throws Exception {
        // given
        List<Item> list = Lists.newArrayList();
        HttpResult underTest = new HttpResult();
        Whitebox.setInternalState(underTest, "items", list);

        // when
        List<Item> items = underTest.getItems();

        assertEquals(items, list);
    }

}