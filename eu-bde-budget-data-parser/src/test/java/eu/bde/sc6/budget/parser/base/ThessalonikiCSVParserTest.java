package eu.bde.sc6.budget.parser.base;

import eu.bde.sc6.budget.parser.api.BudgetDataParser;
import eu.bde.sc6.budget.parser.api.TransformationException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openrdf.model.Statement;
import org.openrdf.rio.RDFHandlerException;

/**
 *
 * @author turnguard
 */
//public class ThessalonikiCSVParserTest extends VirtuosoCapability {
public class ThessalonikiCSVParserTest {

                    
    public ThessalonikiCSVParserTest() throws MalformedURLException {
        super();
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

    //@Test
    public void testAllIncomeCSVsForDataErrors() throws IOException{
        
        File rootDirectory = new File(ThessalonikiCSVParserTest.class.getClass().getResource("/thessaloniki/csv/incomes/").getFile());
        Path path = Paths.get(rootDirectory.toURI());
        
        BudgetDataParser parser = new eu.bde.sc6.budget.parser.base.thessaloniki.CSVIncomesParser();
        
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() { 
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
            {
                try {
                    List<Statement> states = parser.transform(file.toString(), Files.readAllBytes(file));
                    System.out.println(file.toString() + " " + states.size());
                    //uploadToVirtuoso(states);
                } catch (TransformationException | RuntimeException ex) {
                    System.out.println("PROBLEMATIC FILE: " + file.toAbsolutePath());                    
                //} catch (RDFHandlerException ex) {
                //    Logger.getLogger(ThessalonikiCSVParserTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
    
    
    //@Test
    public void testAllExpensesCSVsForDataErrors() throws IOException{
        
        File rootDirectory = new File(ThessalonikiCSVParserTest.class.getClass().getResource("/thessaloniki/csv/expenses/").getFile());
        Path path = Paths.get(rootDirectory.toURI());
        
        BudgetDataParser parser = new eu.bde.sc6.budget.parser.base.thessaloniki.CSVExpensesParser();
        
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() { 
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
            {
                try {
                    List<Statement> states = parser.transform(file.toString(), Files.readAllBytes(file));
                    System.out.println(file.toString() + " " + states.size());
                    //uploadToVirtuoso(states);                    
                } catch (TransformationException | RuntimeException ex) {
                    System.out.println("PROBLEMATIC FILE: " + file.toAbsolutePath());                    
                //} catch (RDFHandlerException ex) {
                //    Logger.getLogger(ThessalonikiCSVParserTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
