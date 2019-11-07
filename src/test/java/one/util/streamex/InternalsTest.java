/*
 * Copyright 2015, 2019 StreamEx contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package one.util.streamex;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static one.util.streamex.Internals.ArrayCollection;
import static one.util.streamex.Internals.PartialCollector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for non-public APIs in StreamExInternals
 * 
 * @author Tagir Valeev
 */
public class InternalsTest {
    @Test
    public void testArrayCollection() {
        Collection<Object> collection = new ArrayCollection(new Object[] { "1", "2" });
        List<Object> list = new LinkedList<>(collection);
        assertEquals("1", list.get(0));
        assertEquals("2", list.get(1));
        List<Object> list2 = new ArrayList<>(collection);
        assertEquals("1", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals(list2, list);
        Set<Object> set = new HashSet<>(collection);
        assertTrue(set.contains("1"));
        assertTrue(set.contains("2"));
        assertEquals(2, set.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPartialCollector() {
        PartialCollector.intSum().accumulator();
    }
}
