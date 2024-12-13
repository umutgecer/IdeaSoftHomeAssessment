package dataprovider;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import util.ExcelUtils;

public class ExcelDataProvider {

    @BeforeSuite
    public static Object[][] testData(String excelPath, String sheetName) {
        ExcelUtils excel = new ExcelUtils("src/test/resources/SearchListExcel.xlsx", "list");

        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();

        Object data[][] = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                String cellData = excel.getCellDataString(i, j);
                data[i - 1][j] = cellData;
            }
        }
        return data;
    }

    @DataProvider(name = "sendText")
    public Object[][] getData() {
        Object data[][] = testData("src/test/resources/SearchListExcel.xlsx", "list");
        return data;
    }
}