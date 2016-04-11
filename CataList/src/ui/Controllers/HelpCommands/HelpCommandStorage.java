//@@author A0112204E
package ui.Controllers.HelpCommands;

import java.util.ArrayList;

public class HelpCommandStorage {

	/**
	 * This class stores all the help commands
	 * It generates the help list but does not display any of them
	 * It returns the entire help command list to its caller
	 * 
	 */
	
	private static final String NULL_REGEX = "";
	private static final String KEYBOARD_HEADING = "COMMANDS";
	private static final String HOTKEYS_HEADING = "HOTKEYS";

	private static final String HELP_ADD_DETAILS = "add task to list";
	private static final String HELP_CALENDAR_DETAILS = "display time and calendar";
	private static final String HELP_CLEAR_DETAILS = "clear list";
	private static final String HELP_DELETE_DETAILS = "delete task from list";
	private static final String HELP_DISPLAY_DETAILS = "display incomplete tasks\n"
			+ "display complete tasks\n"
			+ "display all tasks";
	private static final String HELP_EDIT_DETAILS = "edit task in list";
	private static final String HELP_MARK_DETAILS = "mark task as completed";
	private static final String HELP_REDO_DETAILS = "redo more recent action";
	private static final String HELP_SAVEAS_DETAILS = "change save location";
	private static final String HELP_SEARCH_DETAILS = "search tasks with keywords";
	private static final String HELP_TUTORIAL_DETAILS = "display step-by-step tutorial";
	private static final String HELP_UNDO_DETAILS = "undo previous action";
	private static final String HELP_UNMARK_DETAILS = "mark task as incomplete";

	private static final String HELP_AUTOCOMPLETE_DETAILS = "complete your command";
	private static final String HELP_PREVIOUS_COMMAND_DETAILS = "get previous command";
	private static final String HELP_QUIT_DETAILS = "quit application";
	private static final String HELP_RECENT_COMMAND_DETAILS = "get more recent command";
	private static final String HELP_TOGGLE_COLORS_DETAILS = "toggle background colors";
	private static final String HELP_TOGGLE_INTERFACE_DETAILS = "toggle interface selection";
	private static final String HELP_TOGGLE_TABS_DETAILS = "toggle filter tabs";

	private static final String HELP_ADD_FORMAT = "add [task]\n"
			+ "add [task] [time] [date]\n"
			+ "add [task] [start time] [end time]\n"
			+ "[start date] [end date]\n";
	private static final String HELP_CLEAR_FORMAT = "clear";
	private static final String HELP_DELETE_FORMAT = "delete [task number]";
	private static final String HELP_EDIT_FORMAT = "edit [task]\n"
			+ "edit [time]\n"
			+ "edit [date]\n";
	private static final String HELP_MARK_FORMAT = "mark [task number]";
	private static final String HELP_UNMARK_FORMAT = "unmark [task number]";
	private static final String HELP_SEARCH_FORMAT = "search [key words]";
	private static final String HELP_SAVEAS_FORMAT = "saveas\n"
			+ "save [file path]";
	private static final String HELP_DISPLAY_FORMAT = "display 0\n"
			+ "display 1\n"
			+ "display 2";
	private static final String HELP_UNDO_FORMAT = "undo";
	private static final String HELP_REDO_FORMAT = "redo";
	private static final String HELP_CALENDAR_FORMAT = "calendar";
	private static final String HELP_TUTORIAL_FORMAT = "tutorial";

	private static final String HELP_QUIT_KEYS = "ESC";
	private static final String HELP_TOGGLE_COLORS_KEYS = "ALT + RIGHT\n"
			+ "ALT + LEFT";
	private static final String HELP_PREVIOUS_COMMAND_KEYS = "UP";
	private static final String HELP_RECENT_COMMAND_KEYS = "DOWN";
	private static final String HELP_AUTOCOMPLETE_KEYS = "SPACE";
	private static final String HELP_TOGGLE_INTERFACE_KEYS = "TAB";
	private static final String HELP_TOGGLE_TABS_KEYS = "F1";

	private static final int HELP_LIST_SIZE = 23;
	private static final int INITIALIZE_INDEX = -1;

	private static ArrayList<ArrayList<String>> helpList = new ArrayList<>();

	/**
	 * Generates the helpList
	 * @return ArrayList<ArrayList<String>> This is the entire list of help command and format
	 */
	public static ArrayList<ArrayList<String>> getHelpList() {
		initHelpList();
		return helpList;
	}

	private static void initHelpList() {
		for(int i = 0; i < HELP_LIST_SIZE; i++) {
			helpList.add(new ArrayList<String>());
		}

		generateHelpList();
	}

	private static void generateHelpList() {
		int index = INITIALIZE_INDEX;
		index = loadHelpCommands(index);
		index = loadHelpHotkeys(index);
	}

	private static int loadHelpCommands(int index) {
		helpList.get(++index).add(KEYBOARD_HEADING);
		helpList.get(index).add(NULL_REGEX);
		helpList.get(++index).add(HELP_ADD_DETAILS);
		helpList.get(index).add(HELP_ADD_FORMAT);
		helpList.get(++index).add(HELP_CALENDAR_DETAILS);
		helpList.get(index).add(HELP_CALENDAR_FORMAT);
		helpList.get(++index).add(HELP_CLEAR_DETAILS);
		helpList.get(index).add(HELP_CLEAR_FORMAT);
		helpList.get(++index).add(HELP_DELETE_DETAILS);
		helpList.get(index).add(HELP_DELETE_FORMAT);
		helpList.get(++index).add(HELP_DISPLAY_DETAILS);
		helpList.get(index).add(HELP_DISPLAY_FORMAT);
		helpList.get(++index).add(HELP_EDIT_DETAILS);
		helpList.get(index).add(HELP_EDIT_FORMAT);
		helpList.get(++index).add(HELP_MARK_DETAILS);
		helpList.get(index).add(HELP_MARK_FORMAT);
		helpList.get(++index).add(HELP_REDO_DETAILS);
		helpList.get(index).add(HELP_REDO_FORMAT);
		helpList.get(++index).add(HELP_SAVEAS_DETAILS);
		helpList.get(index).add(HELP_SAVEAS_FORMAT);
		helpList.get(++index).add(HELP_SEARCH_DETAILS);
		helpList.get(index).add(HELP_SEARCH_FORMAT);
		helpList.get(++index).add(HELP_TUTORIAL_DETAILS);
		helpList.get(index).add(HELP_TUTORIAL_FORMAT);
		helpList.get(++index).add(HELP_UNDO_DETAILS);
		helpList.get(index).add(HELP_UNDO_FORMAT);
		helpList.get(++index).add(HELP_UNMARK_DETAILS);
		helpList.get(index).add(HELP_UNMARK_FORMAT);
		return index;
	}
	
	private static int loadHelpHotkeys(int index) {
		helpList.get(++index).add(NULL_REGEX);
		helpList.get(index).add(NULL_REGEX);
		helpList.get(++index).add(HOTKEYS_HEADING);
		helpList.get(index).add(NULL_REGEX);
		helpList.get(++index).add(HELP_AUTOCOMPLETE_DETAILS);
		helpList.get(index).add(HELP_AUTOCOMPLETE_KEYS);
		helpList.get(++index).add(HELP_PREVIOUS_COMMAND_DETAILS);
		helpList.get(index).add(HELP_PREVIOUS_COMMAND_KEYS);
		helpList.get(++index).add(HELP_QUIT_DETAILS);
		helpList.get(index).add(HELP_QUIT_KEYS);
		helpList.get(++index).add(HELP_RECENT_COMMAND_DETAILS);
		helpList.get(index).add(HELP_RECENT_COMMAND_KEYS);
		helpList.get(++index).add(HELP_TOGGLE_COLORS_DETAILS);
		helpList.get(index).add(HELP_TOGGLE_COLORS_KEYS);
		helpList.get(++index).add(HELP_TOGGLE_INTERFACE_DETAILS);
		helpList.get(index).add(HELP_TOGGLE_INTERFACE_KEYS);
		helpList.get(++index).add(HELP_TOGGLE_TABS_DETAILS);
		helpList.get(index).add(HELP_TOGGLE_TABS_KEYS);
		return index;
	}
}
