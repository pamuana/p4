//////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Scheduler.java
// Files:            Event Interval IntervalBST IntervalBSTIterator 
//		IntervalBSTnode IntervalConflictException Resource SchedulerDB 
//		SortedListADT
//
// Semester:         CS 367 Fall 2015
//
// Author:           Anupama Bhattacharya
// Email:            abhattachar4@wisc.edu
// CS Login:         anupama
// Lecturer's Name:  Skrenty
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//
// Pair Partner:     josh mcgrath
// Email:            jmcgrath4@wisc.edu
// CS Login:         mcgrath
// Lecturer's Name:  Skrentny
//////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/**
 * Maintains user friendly interface and communicates changes to 
 * the DataBase
 *
 * @author Anupama Bhattacharya
 */
public class Scheduler {

    private static SchedulerDB schedulerDB = new SchedulerDB();//Database
    private static Scanner scanner = null; //Initializes scanner
    /**
     * Starts program by intializing data and opening up the interface
     *
     * @param String name of ResourceFile 
     */
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("Usage: java Scheduler <resourceListFile>");
            System.exit(1);
        }

        boolean didInitialize = initializeFromInputFile(args[0]);

        if (!didInitialize) {
            System.err.println("Failed to initialize the application!");
            System.exit(1);
        }

        System.out.println("Welcome to the UW-Madison Scheduling Client");

        processUserCommands();
    }
    /**
     * Inputs all the data from resourceListFile to the Database
     *
     * @param StringString resourceListFile
     * @return boolean of success
     */
    private static boolean initializeFromInputFile(String resourceListFile) {

		try {
			Scanner scnr = new Scanner(new File(resourceListFile));
			SimpleDateFormat f = new SimpleDateFormat("mm/dd/yyyy,hh:mm");
			String currentResourceName = null;
			while (scnr.hasNextLine()) {
				String temp = scnr.nextLine();
				if (temp.equals("#Resource: ")) {
					String reName = scnr.nextLine();
					schedulerDB.addResource(reName);
					currentResourceName = reName;
				}
				else{
					String name = temp;
					String start = scnr.nextLine();
					String end = scnr.nextLine();
					String org = scnr.nextLine();
					String description = scnr.nextLine();
					long startLong = convertToTime(start);
					long endLong = convertToTime(end) ;
					schedulerDB.addEvent(currentResourceName, startLong, endLong, name, org, description);
				}
			}
			scnr.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    /**
     * Processes what to do with the user's commands
     *
     */
    private static void processUserCommands() {
        scanner = new Scanner(System.in);
        String command = null;
        do {

            System.out.print("\nPlease Enter a command ([H]elp):");
            command = scanner.next();
            switch (command.toLowerCase()) {
                case "v":
                    processDisplayCommand();
                    break;
                case "a":
                    processAddCommand();
                    break;
                case "d":
                    processDeleteCommand();
                    break;
                case "q":
                    System.out.println("Quit");
                    break;
                case "h":
                    System.out.println(
                            "\nThis scheduler has commands that are entered as a single character indicated in [].\n"
                                    + "The main commands are to view, add, delete, or quit.\n"
                                    + "The first three main commands need a secondary command possibly with additional input.\n"
                                    + "A secondary command's additional input is described within <>.\n"
                                    + "Please note that a comma (,) in the add event command represents a need to press\n"
                                    + "the return character during the command. Also note that times must be in the format\n"
                                    + "of mm/dd/yyyy,hh:mm.\n" + "[v]iew\n" + "	[r] = view all resources\n"
                                    + "	[e] = view all events\n" + "	[t] <resource name> = view events in a resource\n"
                                    + "	[o] <organization name> = view events with an organization\n"
                                    + "	[n] <start time> <end time> = view events within a time range\n"
                                    + "	[s] <start time> <end time> <resource name> = view events within in a time range in a resource\n"
                                    + "[a]dd\n" + "	[r] <resource name> = add a resource\n"
                                    + "	[e] <resource name>, = add an event\n"
                                    + "		      <start time> <end time> <event name>, \n"
                                    + "		      <organization name>, \n" + "		      <event description>\n"
                                    + "[d]elete\n" + "	[r] <resource name> = delete a resource\n"
                                    + "	[e] <event start time> <resource name> = delete an event\n" + "[q]uit\n");
                    break;
                default:
                    System.out.println("Unrecognized Command!");
                    break;
            }
        } while (!command.equalsIgnoreCase("q"));
        scanner.close();
    }
    /**
     * Displays the the interface and communicates with user
     *
     */
    private static void processDisplayCommand() {
        String restOfLine = scanner.next();
        Scanner in = new Scanner(restOfLine);
        String subCommand = in.next();
        switch (subCommand.toLowerCase()) {
            // additional input in comments (comma means return)
            case "r":
                printResourceList(schedulerDB.getResources());
                break;
            case "e":
                printEventList(schedulerDB.getAllEvents());
                break;
            case "t": // resource,
                 printEventList(schedulerDB.getEventsInResource(scanner.nextLine().trim()));
                break;
            case "s": // start end resource,
                 printEventList(schedulerDB.getEventsInRangeInResource(
                 convertToTime(scanner.next()), convertToTime(scanner.next()),
                 scanner.nextLine().trim()));
                break;
            case "o": // organization
                printEventList(schedulerDB.getEventsForOrg(scanner.nextLine().trim()));
                break;
            case "n": // start end
                printEventList(schedulerDB.getEventsInRange(convertToTime(scanner.next()), convertToTime(scanner.next())));
                break;
            default:
                System.out.println("Unrecognized Command!");
        }
        in.close();
    }
    /**
     * Processes the command to add a resouce or event
     */
    private static void processAddCommand() {
        String restOfLine = scanner.next();
        Scanner in = new Scanner(restOfLine);
        String subCommand = in.next();
        switch (subCommand.toLowerCase()) {
            case "r": // resource
                if (!schedulerDB.addResource(scanner.nextLine().trim())) {
                    System.out.println("Could not add: no two resources may have the same name.");
                } else {
                    System.out.println("Successfully added resource.");
                }
                break;
            case "e": // resource, start end name, organization, description
                try {
                    if (!schedulerDB.addEvent(scanner.nextLine().trim(), convertToTime(scanner.next()),
                            convertToTime(scanner.next()), scanner.nextLine().trim(), scanner.nextLine().trim(),
                            scanner.nextLine().trim())) {
                        System.out.println("Could not add: resource not found.");
                    } else {
                        System.out.println("Successfully added event.");
                    }
                } catch (IntervalConflictException expt) {
                    System.out.println("Could not add: this event conflicted with an existing event.");
                }
                break;
            default:
                System.out.println("Unrecognized Command!");
        }
        in.close();
    }
    /**
     * Processes the user command to delete a resource or event
     */
    private static void processDeleteCommand() {
        String restOfLine = scanner.next();
        Scanner in = new Scanner(restOfLine);
        String subCommand = in.next();
        switch (subCommand.toLowerCase()) {
            case "r": // resource
                if (!schedulerDB.removeResource(scanner.nextLine().trim())) {
                    System.out.println("Could not delete. Resource not found.");
                } else {
                    System.out.println("Successfully deleted resource.");
                }
                break;
            case "e": // resource, start
                if (!schedulerDB.deleteEvent(convertToTime(scanner.next().trim()), scanner.nextLine().trim())) {
                    System.out.println("Could not delete. Resource not found.");
                } else {
                    System.out.println("Successfully deleted event.");
                }
                break;
            default:
                System.out.println("Unrecognized Command!");
        }
        in.close();
    }
    /**
     * Prints out list of Resources given
     *
     * @param List of Resources
     */
    private static void printResourceList(List<Resource> list) {
        Iterator<Resource> itr = list.iterator();
        if (!itr.hasNext()) {
            System.out.println("No resources to display.");
        }
        while (itr.hasNext()) {
            System.out.println(itr.next().getName());
        }
    }
    /**
     * Prints out list of events given
     *
     * @param List of events
     */
    private static void printEventList(List<Event> list) {
        Iterator<Event> itr = list.iterator();
        if (!itr.hasNext()) {
            System.out.println("No events to display.");
        }
        while (itr.hasNext()) {
            System.out.println("\n" + itr.next().toString());
        }
    }
    /**
     * Converts the date from string form to a long
     *
     * @param String version of date
     * @return long version of date
     */
    private static long convertToTime(String time) {
        long result = 0;
        Format format = new SimpleDateFormat("MM/dd/yyyy,HH:mm");
        try {
            Date date = (Date) format.parseObject(time);
            result = date.getTime() / 1000;
        } catch (Exception e) {
            System.out.println("Dates are not formatted correctly.  Must be \"MM/dd/yyyy,HH:mm\"");
        }
        return result;
    }

}

