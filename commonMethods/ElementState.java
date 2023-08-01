package com.ecrm.commonMethods;

import org.openqa.selenium.WebElement;

public class ElementState
{
	public static String getElementProperties(WebElement element)
	{
		String text = element.toString();
		if(text.contains(">") && text.contains("]"))
		{
			text = text.substring(text.indexOf(">")+2, text.length()-1);
		}
		else if (text.contains("By."))
		{
			text = text.substring(text.indexOf("By.")-2, text.length()-1);
		}
		return text;
	}
}
