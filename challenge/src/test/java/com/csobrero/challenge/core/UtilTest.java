package com.csobrero.challenge.core;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import com.csobrero.challenge.bean.Flight;
import com.csobrero.challenge.util.Utils;

public class UtilTest {

	@Test
	public void testJaxbMarshall() {
		Flight flight = new Flight("AA123","American Airlines");
		ByteArrayOutputStream baos = Utils.JaxbMarshall(flight);
		Assert.assertNotNull(baos);
	}
	
	@Test
	public void encodeBase64() throws Exception{
		String test = "test";
        
        byte res1[] = Utils.encodeBase64(test.getBytes());
        System.out.println(test + " base64 -> " + Arrays.toString(res1));
        System.out.println(new String(res1));
        byte res2[] = Utils.decodeBase64(res1);
        System.out.println("");
        System.out.println( Arrays.toString(res1) + " string --> " 
          + new String(res2));
        Assert.assertEquals(test, new String(res2));
	}

}
