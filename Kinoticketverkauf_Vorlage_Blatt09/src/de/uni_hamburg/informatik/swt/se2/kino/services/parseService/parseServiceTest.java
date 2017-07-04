package de.uni_hamburg.informatik.swt.se2.kino.services.parseService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class parseServiceTest
{
    
    @Test
    public void testIntParse()
    {
        assertTrue(ParseService.parseToInt("13")==13);
        assertTrue(ParseService.parseToInt("132323")==132323);
        assertTrue(ParseService.parseToInt("132.323")==132323);
        assertTrue(ParseService.parseToInt("-13")==-13);
        assertTrue(ParseService.parseToInt("a13")==Integer.MIN_VALUE);
    }
    
   @Test
   public void testDoubleParse()
   {
       assertTrue(ParseService.parseToDouble("1,3")==1.3);
       assertTrue(ParseService.parseToDouble("12121,3")==12121.3);
       assertTrue(ParseService.parseToDouble("1")==1.0);
       assertTrue(ParseService.parseToDouble("12.121,3")==12121.3);
       assertTrue(ParseService.parseToDouble("-1,3")==-1.3);
       assertTrue(ParseService.parseToDouble("a1,3")==Double.MIN_VALUE);       
   }

}
