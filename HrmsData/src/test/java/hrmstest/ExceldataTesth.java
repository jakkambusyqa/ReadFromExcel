package hrmstest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExceldataTesth {

	@DataProvider(name = "hrmsdatatest")

	public Object[] hrmsdatatest() {

		//System.out.println("check");

		File file = new File("./target/praveen python batch august.xlsx");

		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(stream);
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getPhysicalNumberOfRows();
	//	System.out.println("rows" + rows);
		// int cells = sheet.getRow(1).getLastCellNum();
		List<Employee> empList = new ArrayList();

		for (int i = 0; i < rows - 1; i++) {
			//System.out.println(i + "for loop");
			Employee e = new Employee();
			DataFormatter df = new DataFormatter();

			e.setEmail(df.formatCellValue(sheet.getRow(i + 1).getCell(1)));
			e.setPassword(df.formatCellValue(sheet.getRow(i + 1).getCell(3)));
			e.setRole(df.formatCellValue(sheet.getRow(i + 1).getCell(2)));
			String nameWithEmpId = df.formatCellValue(sheet.getRow(i + 1).getCell(0)) + " ("
					+ df.formatCellValue(sheet.getRow(i + 1).getCell(4)) + ")";
			e.setEmployeeName(nameWithEmpId);
			empList.add(e);

		}
		//System.out.println(empList);

		return empList.toArray();
	}

}
