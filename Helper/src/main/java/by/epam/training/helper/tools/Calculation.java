package by.epam.training.helper.tools;

public class Calculation {

	public static int pageCounting(int amontItems, int itemOnPage) {
		if(amontItems != 0 && amontItems % itemOnPage == 0 ){
			return (amontItems / itemOnPage);
		}else{
			return (amontItems / itemOnPage + 1);
		}
	}
}
