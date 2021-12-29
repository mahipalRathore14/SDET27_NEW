package practise;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGPractise {
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objarr=new Object[5][4];
		
		objarr[0][0]="mahipal";
		objarr[0][1]="jodhpur";
		objarr[0][2]="mech";
		objarr[0][3]=2016;
		
		objarr[1][0]="sunil";
		objarr[1][1]="hp";
		objarr[1][2]="mech";
		objarr[1][3]=2016;
		
		objarr[2][0]="misshaq";
		objarr[2][1]="delhi";
		objarr[2][2]="mech";
		objarr[2][3]=2017;
		
		objarr[3][0]="jaggi";
		objarr[3][1]="jodhpur";
		objarr[3][2]="ec";
		objarr[3][3]=2016;
		
		objarr[4][0]="manoj";
		objarr[4][1]="jodhpur";
		objarr[4][2]="mech";
		objarr[4][3]=2015;
		return objarr;
	}
	@Test(dataProvider="getData")
	public void readDataFromDataProviderTest(String name,String Place,String Branch,int year)
	{
		getData();
		}

}
