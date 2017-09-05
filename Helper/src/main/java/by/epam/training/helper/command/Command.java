package by.epam.training.helper.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.training.helper.command.exception.CommandException;
/**
 * Main interface 
 * @author Nikolaev Ilya
 * Interface of Command pattern which used to delegate action from controller to separated amount of classes,
 */
public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException;
}
