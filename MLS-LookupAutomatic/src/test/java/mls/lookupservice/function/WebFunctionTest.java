package mls.lookupservice.function;

import mls.lookupservice.model.CellDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lingga on 23/03/17.
 */

@Test(threadPoolSize = 4, invocationCount = 10)
public class WebFunctionTest
{
    private final Logger log = LoggerFactory.getLogger(WebFunctionTest.class);

    @Test
    public void testCheckCellDB() throws Exception
    {
        log.info("test check CellDB");

        final CellDB cellDB = new CellDB();
        cellDB.setMcc(202);
        cellDB.setNet(1);
        cellDB.setArea(33);
        cellDB.setCell(7652);

        final WebFunction webFunction = new WebFunction();
        assertThat(webFunction.checkCellDB(cellDB)).isEqualTo(null);
    }

    @Test
    public void testCheckCellDBErrorMCC() throws Exception
    {
        log.info("test check CellDB error MCC");

        final CellDB cellDB = new CellDB();
        cellDB.setNet(1);
        cellDB.setArea(33);
        cellDB.setCell(7652);

        final WebFunction webFunction = new WebFunction();
        assertThat(webFunction.checkCellDB(cellDB)).isEqualTo(WebFunction.INVALID_MCC);
    }

    @Test
    public void testCheckCellDBErrorNET() throws Exception
    {
        log.info("test check CellDB error NET");

        final CellDB cellDB = new CellDB();
        cellDB.setMcc(202);
        cellDB.setArea(33);
        cellDB.setCell(7652);

        final WebFunction webFunction = new WebFunction();
        assertThat(webFunction.checkCellDB(cellDB)).isEqualTo(WebFunction.INVALID_NET);
    }

    @Test
    public void testCheckCellDBErrorArea() throws Exception
    {
        log.info("test check CellDB error AREA");

        final CellDB cellDB = new CellDB();
        cellDB.setMcc(202);
        cellDB.setNet(1);
        cellDB.setCell(7652);

        final WebFunction webFunction = new WebFunction();
        assertThat(webFunction.checkCellDB(cellDB)).isEqualTo(WebFunction.INVALID_AREA);
    }

    @Test
    public void testCheckCellDBErrorCell() throws Exception
    {
        log.info("test check CellDB error CELL");

        final CellDB cellDB = new CellDB();
        cellDB.setMcc(202);
        cellDB.setNet(1);
        cellDB.setArea(33);

        final WebFunction webFunction = new WebFunction();
        assertThat(webFunction.checkCellDB(cellDB)).isEqualTo(WebFunction.INVALID_CELL);
    }
}