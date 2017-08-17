package by.epam.training.helper.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.jasper.el.JspELException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Nikolaev Ilya
 *
 */
public class CopyrightTag extends SimpleTagSupport{
	private static final Logger logger = LogManager.getLogger(CopyrightTag.class);
	public void doTag() throws JspELException{
		
		JspWriter writer = getJspContext().getOut();
		
		try {
			writer.println("Nikolaev Ilya © 2017");
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
}
