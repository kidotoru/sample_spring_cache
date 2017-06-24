/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.treewoods.sample_spring_cache.beans;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author toru
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
public class CacheSampleTest {
    
    @Autowired
    CacheSample cacheSample;
    
    public CacheSampleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class CacheSample.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] args = null;
        cacheSample.execute(args);
    }
    
}
