import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by joshmcgrath on 11/17/15.
 */
public class IntervalBSTTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private IntervalBST<Event> bst;
    public static Event defaultEvent;
    public static Event secondaryEvent;

    @Before
    public void setUp() throws Exception {
        bst = new IntervalBST<>();
        defaultEvent = new Event(1906,1283921,"hsadjkaks", "aisdjkashfka", "sahjdasjk", "sakdjfsakjflask" );
        secondaryEvent = new Event(100000, 1290481209,"hsadjkaks", "aisdjkashfka", "sahjdasjk", "sakdjfsakjflask"  );
    }

    @Test
    public void testInsert() throws Exception {
        //check for basic functionality
        bst.insert(defaultEvent);
    }
    @Test
    public void testInsertDup() throws Exception{
        exception.expect(IllegalArgumentException.class);
        bst.insert(defaultEvent);
        bst.insert(defaultEvent);
    }

    @Test
    public void testDelete() throws Exception {
        bst.insert(defaultEvent);
        //basic functionality check
        Assert.assertEquals(true, bst.delete(defaultEvent));
        Assert.assertEquals(false, bst.delete(secondaryEvent));
    }

    @Test
    public void testLookup() throws Exception {
        bst.insert(defaultEvent);
        Assert.assertEquals(true, defaultEvent.equals(bst.lookup(defaultEvent)));
        Assert.assertEquals(null ,bst.lookup(secondaryEvent));
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(0, bst.size());
        bst.insert(defaultEvent);
        Assert.assertEquals(1, bst.size());
        bst.delete(defaultEvent);
        Assert.assertEquals(0, bst.size());
    }

    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertEquals(true, bst.isEmpty());
        bst.insert(defaultEvent);
        Assert.assertEquals(false, bst.isEmpty());
    }

}